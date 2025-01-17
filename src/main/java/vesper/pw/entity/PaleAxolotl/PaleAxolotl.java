package vesper.pw.entity.PaleAxolotl;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import vesper.pw.entity.Entities;

public class PaleAxolotl extends AxolotlEntity implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public PaleAxolotl(EntityType<? extends AxolotlEntity> entityType, World world) {
        super(entityType, world);
    }


    public static DefaultAttributeContainer.Builder setAttributes (){
        return AxolotlEntity.createAxolotlAttributes()
                .add(EntityAttributes.MAX_HEALTH, 14.0D)
                .add(EntityAttributes.ATTACK_DAMAGE, 2.0F)
                .add(EntityAttributes.ATTACK_SPEED,1.0F)
                .add(EntityAttributes.MOVEMENT_SPEED,0.25F);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimAroundGoal(this, 1.0F,1));
        this.goalSelector.add(3, new MeleeAttackGoal(this, 2.0D, false));
        this.goalSelector.add(5, new WanderAroundGoal(this, 1, 1));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1, 1));
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, AxolotlEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, GlowSquidEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, FishEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, SalmonEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, TropicalFishEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, SquidEntity.class, true));

    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return Entities.PALE_AXOLOTL.create(world, SpawnReason.NATURAL);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<PaleAxolotl> paleAxolotlAnimationState) {
        paleAxolotlAnimationState.getController().setAnimation(RawAnimation.begin().then("", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}

