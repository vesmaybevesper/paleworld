package vesper.pw.entity.PaleAxolotl;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.AmphibiousSwimNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.WalkOrRunToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.AxolotlSpecificSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import org.jetbrains.annotations.Nullable;
import vesper.pw.entity.Entities;
import vesper.pw.item.PaleWorldItems;

import java.util.List;

public class PaleAxolotl extends AxolotlEntity implements Bucketable, SmartBrainOwner<PaleAxolotl> {
    public final AnimationState idleAnimation = new AnimationState();
    private int idleAnimationTimeout = 0;

    public PaleAxolotl(EntityType<? extends AxolotlEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 0);
    }


    public static DefaultAttributeContainer.Builder setAttributes() {
        return PaleAxolotl.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 14.0F)
                .add(EntityAttributes.ATTACK_DAMAGE, 2.0F)
                .add(EntityAttributes.ATTACK_SPEED, 2.0F)
                .add(EntityAttributes.MOVEMENT_SPEED, (double) 0.5F)
                .add(EntityAttributes.STEP_HEIGHT, .75F)
                .add(EntityAttributes.JUMP_STRENGTH, 2F);
    }


    @Override
    public void travel(Vec3d movementInput) {
        super.travel(movementInput);
    }


    @Override
    public boolean isFromBucket() {
        return false;
    }

    @Override
    public void setFromBucket(boolean fromBucket) {

    }

    @Override
    public void copyDataToStack(ItemStack stack) {

    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {

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
    protected void mobTick(ServerWorld world) {
        tickBrain(this);
    }

    @Override
    public List<? extends ExtendedSensor<? extends PaleAxolotl>> getSensors() {
        return ObjectArrayList.of(
                new AxolotlSpecificSensor<>(),
                new HurtBySensor<>(),
                new NearbyLivingEntitySensor<>()
        );
    }

    @Override
    public BrainActivityGroup<? extends PaleAxolotl> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtAttackTarget(),
                new MoveToWalkTarget<>()
        );
    }

    @Override
    public BrainActivityGroup<? extends PaleAxolotl> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<PaleAxolotl>(
                        new TargetOrRetaliate<>().useMemory(MemoryModuleType.NEAREST_ATTACKABLE),
                        new SetPlayerLookTarget<>(),
                        new SetRandomLookTarget<>()
                ),
                new OneRandomBehaviour<>(
                        new SetRandomWalkTarget<>(),
                        new Idle<>().runFor(entity -> entity.getRandom().nextBetween(30, 60))
                )
        );
    }

    @Override
    public BrainActivityGroup<? extends PaleAxolotl> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget(),
                new SetWalkTargetToAttackTarget<>(),
                new AnimatableMeleeAttack<>(0)
        );
    }
}

