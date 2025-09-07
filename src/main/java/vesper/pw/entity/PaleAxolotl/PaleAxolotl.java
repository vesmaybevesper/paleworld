package vesper.pw.entity.PaleAxolotl;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.AmphibiousSwimNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.AxolotlSpecificSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animatable.processing.AnimationTest;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;
import vesper.pw.entity.VampireBat.VampireBat;
import vesper.pw.item.PaleWorldItems;

import java.util.List;

public class PaleAxolotl extends AxolotlEntity implements Bucketable, GeoEntity, SmartBrainOwner<PaleAxolotl> {
    public final AnimationState idleAnimation = new AnimationState();
    private int idleAnimationTimeout = 0;
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("pa.walk");
    protected static final RawAnimation SWIM_ANIM = RawAnimation.begin().thenLoop("pa.swim");
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("pa.idle");
    protected static final RawAnimation PLAY_DEAD_ANIM = RawAnimation.begin().thenLoop("pa.play_dead");
    private static final TrackedData<Boolean> FROM_BUCKET;

    public PaleAxolotl(EntityType<? extends AxolotlEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 0);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(FROM_BUCKET, false);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return PaleAxolotl.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 14.0F)
                .add(EntityAttributes.ATTACK_DAMAGE, 2.0F)
                .add(EntityAttributes.ATTACK_SPEED, 2.0F)
                .add(EntityAttributes.MOVEMENT_SPEED, (double) 0.5F)
                .add(EntityAttributes.STEP_HEIGHT, .75F)
                .add(EntityAttributes.JUMP_STRENGTH, 2F)
                .add(EntityAttributes.TEMPT_RANGE, 5F);
    }


    @Override
    public void travel(Vec3d movementInput) {
        super.travel(movementInput);
    }


    @Override
    public boolean isFromBucket() {
        return (Boolean)this.dataTracker.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean fromBucket) {
        this.dataTracker.set(FROM_BUCKET, fromBucket);
    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        super.copyDataToStack(stack);
    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        super.copyDataFromNbt(nbt);
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(PaleWorldItems.PALE_AXOLOTL_BUCKET);
    }

    @Override
    public SoundEvent getBucketFillSound() {
        return SoundEvents.ITEM_BUCKET_FILL_AXOLOTL;
    }

    @Override
    public boolean cannotDespawn() {
        return super.cannotDespawn() || this.isFromBucket();
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new SwimAroundGoal(this, 1.0F, 20));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 2.0D, false));
        this.goalSelector.add(1, new AttackGoal(this));
        this.goalSelector.add(2, new WanderAroundGoal(this, 1.0F, 15));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0F, 10));
        this.goalSelector.add(0, new LookAroundGoal(this));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, AxolotlEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, GlowSquidEntity.class, true));
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, FishEntity.class, true));
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, SalmonEntity.class, true));
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, TropicalFishEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, SquidEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, TadpoleEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PaleAxolotl.class, true));
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, VampireBat.class, true));
        this.targetSelector.add(0, new RevengeGoal(this));
    }

    private void setAnimationStates() {
        if (this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = 240;
            this.idleAnimation.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new AmphibiousSwimNavigation(this, world);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient) {
            this.setAnimationStates();
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<PaleAxolotl>("walk", 5, this::walkAnimController));
        controllerRegistrar.add(new AnimationController<PaleAxolotl>("swim", 5, this::swimAnimController));
        controllerRegistrar.add(new AnimationController<PaleAxolotl>("idle", 5, this::idleAnimController));
        controllerRegistrar.add(new AnimationController<PaleAxolotl>("play_dead", 5, this::playDeadAnimController));
    }

    private <E extends PaleAxolotl>PlayState walkAnimController(final AnimationTest<E> animationTest){
        if (!animationTest.isMoving() || !this.isOnGround()) {
            return PlayState.STOP;
        }
        return animationTest.setAndContinue(WALK_ANIM);
    }

    protected <E extends PaleAxolotl> PlayState swimAnimController(final AnimationTest<E> animationTest){
        if (!animationTest.isMoving() || !this.submergedInWater){
            return PlayState.STOP;
        }
        return animationTest.setAndContinue(SWIM_ANIM);
    }


    protected <E extends PaleAxolotl> PlayState idleAnimController(final AnimationTest<E> animationTest){
        if (this.getNavigation().isIdle()){
            return animationTest.setAndContinue(IDLE_ANIM);
        }

        return PlayState.STOP;
    }

    protected <E extends PaleAxolotl> PlayState playDeadAnimController(final AnimationTest<E> animationTest){
        if (this.isPlayingDead()){
            return animationTest.setAndContinue(PLAY_DEAD_ANIM);
        }
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Override
    public List<? extends ExtendedSensor<? extends PaleAxolotl>> getSensors() {
        return List.of(
                new AxolotlSpecificSensor<>(),
                new NearbyLivingEntitySensor<>()
        );
    }

    static {
        FROM_BUCKET = DataTracker.registerData(PaleAxolotl.class, TrackedDataHandlerRegistry.BOOLEAN);
    }
    
}

