package dev.vesper.paleworld.common.entity.PaleAxolotl;

import dev.vesper.paleworld.common.items.PaleWorldItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.AxolotlSpecificSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animatable.processing.AnimationTest;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class PaleAxolotl extends Axolotl implements Bucketable, GeoEntity, SmartBrainOwner<PaleAxolotl> {
	public final net.minecraft.world.entity.AnimationState idleAnimation = new net.minecraft.world.entity.AnimationState();
	private int idleAnimationTimeout = 0;
	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
	protected static final RawAnimation WALK_ANIMATION = RawAnimation.begin().thenLoop("pa.walk");
	protected static final RawAnimation SWIM_ANIMATION = RawAnimation.begin().thenLoop("pa.swim");
	protected static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("pa.idle");
	protected static final RawAnimation PLAY_DEAD_ANIM = RawAnimation.begin().thenLoop("pa.play_dead");
	private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(PaleAxolotl.class, EntityDataSerializers.BOOLEAN);

	public PaleAxolotl(EntityType<? extends Axolotl> entityType, Level level) {
		super(entityType, level);
		this.setPathfindingMalus(PathType.WATER_BORDER, 0);
	}

	public static AttributeSupplier.Builder	setAttributes(){
		return PaleAxolotl.createAnimalAttributes()
				.add(Attributes.MAX_HEALTH, 14)
				.add(Attributes.ATTACK_DAMAGE, 2)
				.add(Attributes.ATTACK_SPEED, 2)
				.add(Attributes.MOVEMENT_SPEED, 0.5f)
				.add(Attributes.STEP_HEIGHT, .75)
				.add(Attributes.JUMP_STRENGTH, 2)
				.add(Attributes.TEMPT_RANGE, 5);
	}

	@Override
	public void travel(Vec3 vec3) {
		super.travel(vec3);
	}

	@Override
	public boolean fromBucket() {
		return this.entityData.get(FROM_BUCKET);
	}

	@Override
	public ItemStack getBucketItemStack() {
		return new ItemStack(PaleWorldItems.PALE_AXOLOTL_BUCKET);
	}

	@Override
	public SoundEvent getPickupSound() {
		return SoundEvents.BUCKET_FILL_AXOLOTL;
	}

	@Override
	public boolean requiresCustomPersistence() {
		return super.requiresCustomPersistence() || this.fromBucket();
	}

	@Override
	protected void registerGoals() {

	}

	private void setAnimationStates(){
		if (this.idleAnimationTimeout <= 0){
			this.idleAnimationTimeout = 240;
			this.idleAnimation.start(this.age);
		} else {
			--this.idleAnimationTimeout;
		}
	}

	@Override
	protected PathNavigation createNavigation(Level level) {
		return new AmphibiousPathNavigation(this, level);
	}

	@Override
	public void tick() {
		super.tick();

		if (this.level().isClientSide()){
			this.setAnimationStates();
		}
	}

	@Override
	public List<? extends ExtendedSensor<? extends PaleAxolotl>> getSensors() {
		return List.of(
				new AxolotlSpecificSensor<>(),
				new NearbyLivingEntitySensor<>()
		);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<PaleAxolotl>("walk", 5, this::walkAnimController));
		controllers.add(new AnimationController<PaleAxolotl>("swim", 5, this::swimAnimController));
		controllers.add(new AnimationController<PaleAxolotl>("idle", 5, this::idleAnimController));
		controllers.add(new AnimationController<PaleAxolotl>("play_dead", 5, this::playDeadAnimController));
	}

	private <E extends PaleAxolotl> PlayState walkAnimController(final AnimationTest<E> animationTest){
		if (animationTest.isMoving() && !this.wasTouchingWater){
			return animationTest.setAndContinue(WALK_ANIMATION);
		}
		return PlayState.STOP;
	}

	private <E extends PaleAxolotl>PlayState swimAnimController(final AnimationTest<E> animationTest){
		if (animationTest.isMoving() && this.wasTouchingWater || this.wasEyeInWater || this.wasTouchingWater){
			return animationTest.setAndContinue(SWIM_ANIMATION);
		}
		return PlayState.STOP;
	}

	private <E extends PaleAxolotl> PlayState idleAnimController(final AnimationTest<E> animationTest){
		if (!animationTest.isMoving() && !this.wasTouchingWater){
			return animationTest.setAndContinue(IDLE_ANIMATION);
		}
		return PlayState.STOP;
	}

	private <E extends PaleAxolotl> PlayState playDeadAnimController(final AnimationTest<E> animationTest){
		if (this.isPlayingDead()){
			return animationTest.setAndContinue(PLAY_DEAD_ANIM);
		}
		return PlayState.STOP;
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}
}
