package vesper.pw.entity.PaleAxolotl;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import vesper.pw.entity.Entities;
import vesper.pw.item.PaleWorldItems;

public class PaleAxolotl extends AxolotlEntity implements Bucketable {
    public final AnimationState idleAnimation = new AnimationState();
    private int idleAnimationTimeout = 0;

    public PaleAxolotl(EntityType<? extends AxolotlEntity> entityType, World world) {
        super(entityType, world);
    }


    public static DefaultAttributeContainer.Builder setAttributes() {
        return PaleAxolotl.createAxolotlAttributes()
                .add(EntityAttributes.MAX_HEALTH, 14.0F)
                .add(EntityAttributes.ATTACK_DAMAGE, 2.0F)
                .add(EntityAttributes.ATTACK_SPEED, 2.0F)
                .add(EntityAttributes.MOVEMENT_SPEED, (double) 0.5F)
                .add(EntityAttributes.STEP_HEIGHT, 1.0F);
    }


    @Override
    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement() && this.isTouchingWater()){
            this.updateVelocity(this.getMovementSpeed(), movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.25));
        }
        super.travel(movementInput);
    }


    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(PaleWorldItems.PALE_AXOLOTL_BUCKET);
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
    public void tick() {
        super.tick();

        if (this.getWorld().isClient) {
            this.setAnimationStates();
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return Entities.PALE_AXOLOTL.create(world, SpawnReason.NATURAL);
    }
}

