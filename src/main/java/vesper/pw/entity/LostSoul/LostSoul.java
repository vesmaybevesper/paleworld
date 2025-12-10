package vesper.pw.entity.LostSoul;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.ClientUtil;
import software.bernie.geckolib.util.GeckoLibUtil;
import vesper.pw.client.render.particle.ParticleTypes;

public class LostSoul extends AmbientEntity implements GeoEntity {



    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public LostSoul(EntityType<? extends AmbientEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        //this.setVelocity(this.getVelocity().multiply(1F, 0.6F, 1F));

    }

    @Override
    protected void mobTick(ServerWorld world) {
        super.mobTick(world);
        world.addParticleClient(ParticleTypes.LOST_SOUL_AURA, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        BlockPos currentPos = this.getBlockPos();

        if (this.random.nextInt(200) == 0) {
            this.bodyYaw = (float)this.random.nextInt(360);
        }

        Vec3d velocity = this.getVelocity();
        Vec3d updateVel = velocity.add((Math.signum(currentPos.getX()) * (double)0.5F - velocity.x) *  (random.nextDouble() / random.nextFloat()), (Math.signum(currentPos.getY()) * (double)0.7F - velocity.y) * (random.nextDouble() / random.nextFloat()), (Math.signum(currentPos.getZ()) * (double)0.5F - velocity.z) *  (random.nextDouble() / random.nextFloat()));
        this.setVelocity(updateVel);
        float face = (float) MathHelper.atan2(updateVel.z, updateVel.x) * (180/ (float) Math.PI) - 90;
        float newFace = MathHelper.wrapDegrees(face - this.getYaw());
        this.forwardSpeed = 0.5F;
        this.setYaw(this.getYaw() + newFace);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(
                new AnimationController<>(10, state -> state.setAndContinue(DefaultAnimations.IDLE))
                        .setCustomInstructionKeyframeHandler(animTest -> {
                    PlayerEntity player = ClientUtil.getClientPlayer();
                    if (player != null){
                        player.sendMessage(Text.literal("KeyFraming"), true);
                    }
                }),
                DefaultAnimations.genericLivingController());
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 6)
                .add(EntityAttributes.MOVEMENT_EFFICIENCY, 0.0001)
                .add(EntityAttributes.MOVEMENT_SPEED, .0001);
    }

    @Override
    public boolean canAvoidTraps() {
        return true;
    }
}
