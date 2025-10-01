package vesper.pw.entity.VampireBat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.AvoidSun;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.EscapeSun;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomFlyingTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.*;
import net.tslat.smartbrainlib.api.core.navigation.SmoothFlyingPathNavigation;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animatable.processing.AnimationTest;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;
import vesper.pw.entity.PaleAxolotl.PaleAxolotl;
import vesper.pw.item.PaleWorldItems;

import java.util.List;


public class VampireBat extends HostileEntity implements Flutterer, SmartBrainOwner<VampireBat>, GeoEntity {
    private static final TrackedData<Byte> VAMPIRE_BAT_FLAGS = DataTracker.registerData(VampireBat.class, TrackedDataHandlerRegistry.BYTE);
    protected boolean isFlying = false;
    protected final BirdNavigation flyingNav;
    protected final EntityNavigation walkNav;
    protected final FlightMoveControl flightControl;
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation FLYING_ANIM = RawAnimation.begin().thenLoop("vb.fly");


    public VampireBat(EntityType<? extends VampireBat> entityType, World world) {
        super(entityType, world);
        this.flightControl = new FlightMoveControl(this, 10, true);
        this.flyingNav = new BirdNavigation(this, world);
        this.walkNav = this.navigation;
        this.walkNav.setCanSwim(true);


        this.experiencePoints = 5;
        if (!world.isClient()) {
            this.setRoosting(false);
        }
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        final SmoothFlyingPathNavigation nav = new SmoothFlyingPathNavigation(this, world);
        nav.setCanSwim(true);
        return nav;
    }

    @Override
    public MoveControl getMoveControl() {
        this.moveControl = this.flightControl;

        return this.moveControl;
    }

    @Override
    protected void initGoals() {
    }


    @Override
    protected Brain.Profile<?> createBrainProfile() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    public List<? extends ExtendedSensor<? extends VampireBat>> getSensors() {
        return List.of(
                new NearbyPlayersSensor<>(),
                new NearbyLivingEntitySensor<VampireBat>()
                        .setPredicate((target, entity) ->
                                        target instanceof PlayerEntity ||
                                        target instanceof PigEntity ||
                                        target instanceof SheepEntity ||
                                        target instanceof PaleAxolotl)
        );
    }

    @Override
    public BrainActivityGroup<? extends VampireBat> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new AvoidSun(),
                new EscapeSun<>().cooldownFor(entity -> 20),
                new LookAtTarget<>(),
                new MoveToWalkTarget<>()
        );
    }

    @Override
    public BrainActivityGroup<? extends VampireBat> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<VampireBat>(
                        new SetPlayerLookTarget<>(),
                        new SetRandomLookTarget<>()
                ),
                new OneRandomBehaviour<>(
                        new SetRandomFlyingTarget<VampireBat>().verticalWeight(entity -> -(entity.getRandom().nextInt(10) == 0 ? 1 : 0)).setRadius(4, 4).startCondition(VampireBat::isInAir),
                        new Idle<>().runFor(entity -> entity.getRandom().nextBetween(30,60)),
                        new TargetOrRetaliate<>().useMemory(MemoryModuleType.NEAREST_ATTACKABLE).cooldownForBetween(1200, 2400)
                )
        );
    }

    @Override
    public BrainActivityGroup<? extends VampireBat> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(),
                new FirstApplicableBehaviour<>(
                        new AnimatableMeleeAttack<>(0).whenStarting(entity -> setAttacking(true)).whenStarting(entity -> setAttacking(false)),
                        new SetWalkTargetToAttackTarget<>()
                )
        );
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        this.tickHandSwing();

        if (this.getEntityWorld().isClient()) {
            this.isFlying = true;
        }

        if (isFlyingVehicle() && this.isFlying){
            setVelocity(getVelocity().subtract(0, getAttributeValue(EntityAttributes.GRAVITY), 0));
        }

        assert MinecraftClient.getInstance().world != null;
        if (this.getEntityPos().getY() < MinecraftClient.getInstance().world.getHeight() + 1){
            this.setVelocity(getVelocity().x, getVelocity().y + 0.0002f, getVelocity().z);
        }
    }

    @Override
    protected boolean isAffectedByDaylight() {
        return true;
    }

    @Override
    protected void mobTick(ServerWorld world) {
        tickBrain(this);

        boolean wasFlying = isInAir();

        this.isFlying = true;

        if (!wasFlying){
            getNavigation();
            getMoveControl();
        }
        setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();
    }

    public void setRoosting(boolean roosting) {
        if (this.dataTracker == null) return;
        byte b = (Byte)this.dataTracker.get(VAMPIRE_BAT_FLAGS);
        if (roosting) {
            this.dataTracker.set(VAMPIRE_BAT_FLAGS, (byte)(b | 1));
        } else {
            this.dataTracker.set(VAMPIRE_BAT_FLAGS, (byte)(b & -2));
        }

    }

    public boolean isRoosting() {
        return ((Byte)this.dataTracker.get(VAMPIRE_BAT_FLAGS) & 1) != 0;
    }

    public static DefaultAttributeContainer.Builder createHostileAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, (double)35.0F)
                .add(EntityAttributes.FLYING_SPEED, (double)2F)
                .add(EntityAttributes.ATTACK_DAMAGE, (double)3.0F);
    }

    public static boolean canSpawn(EntityType<VampireBat> vampireBatEntityType, ServerWorldAccess serverWorldAccess, SpawnReason spawnReason, BlockPos blockPos, Random random) {
        return serverWorldAccess.getLightLevel(blockPos) <= 7 || SpawnReason.isAnySpawner(spawnReason) && serverWorldAccess.getDifficulty() != Difficulty.PEACEFUL;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VAMPIRE_BAT_FLAGS, (byte) 0);
    }

    @Override
    public boolean isInAir() {
       return true;
    }

    @Override
    public boolean handleFallDamage(double fallDistance, float damagePerDistance, DamageSource damageSource) {
        return false;
    }

    @Override
    public boolean tryAttack(ServerWorld world, Entity target) {
        if (super.tryAttack(world, target)){
            onAttacking(target);

            return true;
        }

        return false;
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        if (super.damage(world, source, (float) this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE))){
            onDamaged(source);

            return true;
        }

        return false;
    }

    @Override
    protected void applyDamage(ServerWorld world, DamageSource source, float amount) {
        super.applyDamage(world, source, (float) this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE));
    }

    @Override
    public boolean isClimbing() {
        return false;
    }

    @Override
    protected void travelFlying(Vec3d movementInput, float speed) {
        super.travelFlying(movementInput, 0.2f);
    }

    @Override
    public void travel(Vec3d movementInput) {
        travelFlying(movementInput, 0.2f);
    }

    @Override
    public @Nullable EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Nullable
    public SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BAT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BAT_DEATH;
    }

    @Override
    protected boolean shouldDropLoot(ServerWorld world) {
        return true;
    }

    @Override
    public @Nullable ItemStack getPickBlockStack() {
        return new ItemStack(PaleWorldItems.VAMPIRE_BAT_SPAWN_EGG);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<VampireBat>("flying", 5, this::flyingAnimationController));
    }

    protected <E extends VampireBat> PlayState flyingAnimationController(final AnimationTest<E> animationTest){
        return animationTest.setAndContinue(FLYING_ANIM);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
}