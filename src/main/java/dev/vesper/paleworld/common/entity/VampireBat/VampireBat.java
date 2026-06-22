package dev.vesper.paleworld.common.entity.VampireBat;

import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.animation.object.PlayState;
import com.geckolib.animation.state.AnimationTest;
import com.geckolib.util.GeckoLibUtil;
import dev.vesper.paleworld.common.entity.PaleAxolotl.PaleAxolotl;
import dev.vesper.paleworld.common.items.PaleWorldItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
//? <26.2{
/*import net.minecraft.world.entity.animal.FlyingAnimal;
*///?}
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.ActivityBuilder;
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
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.navigation.SmoothFlyingPathNavigation;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.List;
//? <26.2{
/*public class VampireBat extends Monster implements FlyingAnimal, SmartBrainOwner<VampireBat>, GeoEntity {
	*///?} >= 26.2{
public class VampireBat extends Monster implements SmartBrainOwner<VampireBat>, GeoEntity{
	//?}
	private static final EntityDataAccessor<Byte> VAMPIRE_BAT_FLAGS = SynchedEntityData.defineId(VampireBat.class, EntityDataSerializers.BYTE);
	protected boolean isFlying = false;
	protected final FlyingPathNavigation flyingNav;
	protected final PathNavigation walkNav;
	protected final FlyingMoveControl flightControl;
	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
	protected static final RawAnimation FLYING_ANIM = RawAnimation.begin().thenLoop("vb.fly");

	public VampireBat(EntityType<? extends Monster> entityType, Level level) {
		super(entityType, level);
		this.flightControl = new FlyingMoveControl(this, 10, true);
		this.flyingNav = new FlyingPathNavigation(this, level);
		this.walkNav = this.navigation;
		this.walkNav.setCanFloat(true);

		this.xpReward = 5;
		if (!level.isClientSide()) {
			this.setRoosting(false);
		}
	}

	@Override
	protected @NotNull PathNavigation createNavigation(@NonNull Level level) {
		final SmoothFlyingPathNavigation nav = new SmoothFlyingPathNavigation(this, level);
		nav.setCanFloat(true);
		return nav;
	}

	@Override
	public @NotNull MoveControl getMoveControl() {
		this.moveControl = this.flightControl;
		return this.moveControl;
	}

	// Explicitly overwrite all goals with nothing so SBL can take over. needed? no. makes me feel good? kinda
	@Override
	protected void registerGoals() {
	}

	@Override
	public @NonNull ActivityBuilder<? extends VampireBat> getCoreBehaviourGroup(@NonNull VampireBat owner) {
		return SmartBrainOwner.super.getCoreBehaviourGroup(owner).behaviours(List.of(
				new AvoidSun<>(),
				new EscapeSun<>().cooldownFor(entity -> 20),
				new LookAtTarget<>(),
				new MoveToWalkTarget<>()
		));
	}

	@Override
	public @NonNull ActivityBuilder<? extends VampireBat> getIdleBehaviourGroup(@NonNull VampireBat owner) {
		return SmartBrainOwner.super.getIdleBehaviourGroup(owner).behaviours(List.of(
				new FirstApplicableBehaviour<VampireBat>(
						new SetPlayerLookTarget<>(),
						new SetRandomLookTarget<>()
				),
				new OneRandomBehaviour<>(
						new SetRandomFlyingTarget<VampireBat>().verticalWeight(entity -> -(entity.getRandom().nextInt(10) == 0 ? 1 : 0)).setRadius(4,4).startCondition(VampireBat::isFlying),
						new Idle<>().runFor(entity -> entity.getRandom().nextIntBetweenInclusive(30, 60)),
						new TargetOrRetaliate<>().useMemory(MemoryModuleType.NEAREST_ATTACKABLE).cooldownForBetween(1200, 2400)
				)
		));
	}

	@Override
	public @NonNull ActivityBuilder<? extends VampireBat> getFightingBehaviourGroup(@NonNull VampireBat owner) {
		return SmartBrainOwner.super.getFightingBehaviourGroup(owner).behaviours(List.of(
				new InvalidateAttackTarget<>(),
				new FirstApplicableBehaviour<>(
						new AnimatableMeleeAttack<>(0).whenStarting(entity -> setAggressive(true)).whenStarting(entity -> setAggressive(false)),
						new SetWalkTargetToAttackTarget<>()
				)
		));
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.updateSwingTime();

		if (this.level().isClientSide()){
			this.isFlying = true;
		}

		if (isFlyingVehicle() && this.isFlying){
			setDeltaMovement(getDeltaMovement().subtract(0, getAttributeValue(Attributes.GRAVITY), 0));
		}

		// im pretty sure isFlyingVehicle() is always false so this isn't doing anything but thats something to test later

		assert Minecraft.getInstance().level != null;
		if (this.getOnPos().getY() < Minecraft.getInstance().level.getHeight() + 1){
			this.setDeltaMovement(getDeltaMovement().x, getDeltaMovement().y + 0.0002f, getDeltaMovement().z);
		}
	}

	@Override
	protected void customServerAiStep(@NonNull ServerLevel serverLevel) {
		//tickBrain(this);

		boolean wasFlying = isFlying();
		this.isFlying = true;

		if (!wasFlying){
			getNavigation();
			getMoveControl();
		}
	}

	@Override
	public void tick() {
		super.tick();
	}

	public void setRoosting(boolean roosting){
		if (this.entityData == null) return;

		byte flags = (Byte) this.entityData.get(VAMPIRE_BAT_FLAGS);
		if (roosting){
			this.entityData.set(VAMPIRE_BAT_FLAGS, (byte) (flags | 1));
		} else {
			this.entityData.set(VAMPIRE_BAT_FLAGS, (byte) (flags & ~1));
		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
		super.defineSynchedData(builder);
		builder.define(VAMPIRE_BAT_FLAGS, (byte) 0);
	}


	public boolean isRoosting(){
		return (this.entityData.get(VAMPIRE_BAT_FLAGS) & 1) != 0;
	}

//? 26.1.2{
	/*@Override
	public boolean isFlying() {
		return !isRoosting();
	}
*///?}
//? >=26.2{
	public boolean isFlying(){
		return !isRoosting();
	}
//?}

	public static AttributeSupplier.Builder createHostileAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.FOLLOW_RANGE, (double)35.0F)
				.add(Attributes.FLYING_SPEED, (double).25F)
				.add(Attributes.ATTACK_DAMAGE, (double)1.0F)
				.add(Attributes.MAX_HEALTH, (double)5F);
	}

	@Override
	public boolean isFallFlying() {
		return false;
	}

	@Override
	public boolean causeFallDamage(double d, float f, @NonNull DamageSource damageSource) {
		return false;
	}

	@Override
	public boolean hurtServer(@NonNull ServerLevel serverLevel, @NonNull DamageSource damageSource, float f) {
		if (super.hurtServer(serverLevel, damageSource, (float) this.getAttributeBaseValue(Attributes.ATTACK_DAMAGE))){
			handleDamageEvent(damageSource);
			return true;
		}
		return false;
	}

	@Override
	protected void actuallyHurt(@NonNull ServerLevel serverLevel, @NonNull DamageSource damageSource, float f) {
		super.actuallyHurt(serverLevel, damageSource, (float) this.getAttributeBaseValue(Attributes.ATTACK_DAMAGE));
	}

	@Override
	public boolean onClimbable() {
		return false;
	}

	@Override
	protected void travelFlying(@NonNull Vec3 vec3, float f) {
		super.travelFlying(vec3, 0.2f);
	}

	@Override
	public void travel(@NonNull Vec3 vec3) {
		travelFlying(vec3, 0.2f);
	}

	@Override
	public @Nullable SpawnGroupData finalizeSpawn(@NonNull ServerLevelAccessor serverLevelAccessor, @NonNull DifficultyInstance difficultyInstance, @NonNull EntitySpawnReason entitySpawnReason, @Nullable SpawnGroupData spawnGroupData) {
		return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, entitySpawnReason, spawnGroupData);
	}

	public static boolean canSpawn(EntityType<VampireBat> vampireBatEntityType, ServerLevelAccessor serverWorldAccess, EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
		return serverWorldAccess.getLightEmission(blockPos) <= 7 || EntitySpawnReason.isSpawner(spawnReason) && serverWorldAccess.getDifficulty() != Difficulty.PEACEFUL;
	}

	@Override
	protected @Nullable SoundEvent getAmbientSound() {
		return SoundEvents.BAT_AMBIENT;
	}

	@Override
	protected @NotNull SoundEvent getHurtSound(@NonNull DamageSource damageSource) {
		return SoundEvents.BAT_HURT;
	}

	@Override
	protected @NotNull SoundEvent getDeathSound() {
		return SoundEvents.BAT_DEATH;
	}

	@Override
	protected boolean shouldDropLoot(@NonNull ServerLevel serverLevel) {
		return true;
	}

	@Override
	public @Nullable ItemStack getPickResult() {
		return new ItemStack(PaleWorldItems.VAMPIRE_BAT_SPAWN_EGG);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<VampireBat>("flying", 5, this::flyingAnimationController));
	}

	protected <E extends VampireBat> PlayState flyingAnimationController(final AnimationTest<E> animationTest){
		return animationTest.setAndContinue(FLYING_ANIM);
	}

	@Override
	public @NonNull AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}

	@Override
	public @NonNull List<? extends ExtendedSensor<?>> getSensors(@NonNull VampireBat vampireBat) {
		return List.of(
				new NearbyPlayersSensor<>(),
				new NearbyLivingEntitySensor<VampireBat>()
						.setPredicate((entity, target) ->
								target instanceof Player ||
										target instanceof Pig ||
										target instanceof Sheep ||
										target instanceof PaleAxolotl)
		);
	}
}
