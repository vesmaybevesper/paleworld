package vesper.pw.entity.VampireBat;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.BodyControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import vesper.pw.item.PaleWorldItems;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;


public class VampireBat extends FlyingEntity implements Monster {
    private static final TrackedData<Byte> VAMPIRE_BAT_FLAGS = DataTracker.registerData(VampireBat.class, TrackedDataHandlerRegistry.BYTE);
    Vec3d targetPosition;
    VampireBatMovementType movementType;
    BlockPos circlingCenter;

    public VampireBat(EntityType<? extends VampireBat> entityType, World world) {
        super(entityType, world);
        this.targetPosition = Vec3d.ZERO;
        this.circlingCenter = BlockPos.ORIGIN;
        this.movementType = VampireBatMovementType.CIRCLE;
        this.experiencePoints = 5;
        this.moveControl = new VBMoveControl(this);
        this.lookControl = new VBLookControl(this);


        if (!world.isClient) {
            this.setRoosting(false);
        }
    }

    protected BodyControl createBodyControl() {
        return new VBBodyControl(this);
    }

    static class VBLookControl extends LookControl {
        public VBLookControl(MobEntity mobEntity) {
            super(mobEntity);
        }

        public void tick() {
        }
    }

    class VBMoveControl extends net.minecraft.entity.ai.control.MoveControl {
        public VBMoveControl(MobEntity entity) {
            super(entity);
        }

        private float targetSpeed = 0.1F;


        public void tick() {
            if (VampireBat.this.horizontalCollision) {
                VampireBat.this.setYaw(VampireBat.this.getYaw() + 180.0F);
                this.targetSpeed = 0.1F;
            }

            double d = VampireBat.this.targetPosition.x - VampireBat.this.getX();
            double e = VampireBat.this.targetPosition.y - VampireBat.this.getY();
            double f = VampireBat.this.targetPosition.z - VampireBat.this.getZ();
            double g = Math.sqrt(d * d + f * f);
            if (Math.abs(g) > (double)1.0E-5F) {
                double h = (double)1.0F - Math.abs(e * (double)0.7F) / g;
                d *= h;
                f *= h;
                g = Math.sqrt(d * d + f * f);
                double i = Math.sqrt(d * d + f * f + e * e);
                float j = VampireBat.this.getYaw();
                float k = (float)MathHelper.atan2(f, d);
                float l = MathHelper.wrapDegrees(VampireBat.this.getYaw() + 90.0F);
                float m = MathHelper.wrapDegrees(k * (180F / (float)Math.PI));
                VampireBat.this.setYaw(MathHelper.stepUnwrappedAngleTowards(l, m, 4.0F) - 90.0F);
                VampireBat.this.bodyYaw = VampireBat.this.getYaw();
                if (MathHelper.angleBetween(j, VampireBat.this.getYaw()) < 3.0F) {
                    this.targetSpeed = MathHelper.stepTowards(this.targetSpeed, 1.8F, 0.005F * (1.8F / this.targetSpeed));
                } else {
                    this.targetSpeed = MathHelper.stepTowards(this.targetSpeed, 0.2F, 0.025F);
                }

                float n = (float)(-(MathHelper.atan2(-e, g) * (double)(180F / (float)Math.PI)));
                VampireBat.this.setPitch(n);
                float o = VampireBat.this.getYaw() + 90.0F;
                double p = (double)(this.targetSpeed * MathHelper.cos(o * ((float)Math.PI / 180F))) * Math.abs(d / i);
                double q = (double)(this.targetSpeed * MathHelper.sin(o * ((float)Math.PI / 180F))) * Math.abs(f / i);
                double r = (double)(this.targetSpeed * MathHelper.sin(n * ((float)Math.PI / 180F))) * Math.abs(e / i);
                Vec3d vec3d = VampireBat.this.getVelocity();
                VampireBat.this.setVelocity(vec3d.add((new Vec3d(p, r, q)).subtract(vec3d).multiply(0.2)));
            }
        }
    }

    class VBBodyControl extends BodyControl {
        public VBBodyControl(final MobEntity entity) {
            super(entity);
        }

        public void tick() {
            VampireBat.this.headYaw = VampireBat.this.bodyYaw;
            VampireBat.this.bodyYaw = VampireBat.this.getYaw();
        }
    }

    @Override
    public @Nullable EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        this.circlingCenter = this.getBlockPos().up(5);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("AX")) {
            this.circlingCenter = new BlockPos(nbt.getInt("AX"), nbt.getInt("AY"), nbt.getInt("AZ"));
        }

    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("AX", this.circlingCenter.getX());
        nbt.putInt("AY", this.circlingCenter.getY());
        nbt.putInt("AZ", this.circlingCenter.getZ());
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(3, new CircularMovementGoal());
        this.goalSelector.add(2, new SwoopGoal());
        this.goalSelector.add(2, new StartAttackGoal());
        this.targetSelector.add(1, new FindTargetGoal());

    }

    public static DefaultAttributeContainer.Builder createHostileAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, (double)35.0F)
                .add(EntityAttributes.FLYING_SPEED, (double)2F)
                .add(EntityAttributes.ATTACK_DAMAGE, (double)3.0F);
    }
    public int getWingFlapTickOffset() {
        return this.getId() * 3;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VAMPIRE_BAT_FLAGS, (byte) 0);
    }

    boolean testTargetPredicate(ServerWorld world, LivingEntity target, TargetPredicate predicate) {
        return predicate.test(world, this, target);
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

    static enum VampireBatMovementType {
        CIRCLE,
        SWOOP;

        private VampireBatMovementType() {
        }
    }

    @Override
    protected boolean isDisallowedInPeaceful() {
        return true;
    }

    @Nullable
    public SoundEvent getAmbientSound() {
        return this.isRoosting() && this.random.nextInt(4) != 0 ? null : SoundEvents.ENTITY_BAT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BAT_DEATH;
    }


    public boolean isRoosting() {
        return ((Byte)this.dataTracker.get(VAMPIRE_BAT_FLAGS) & 1) != 0;
    }


    @Override
    protected boolean shouldDropLoot() {
        return true;
    }

    @Override
    public @Nullable ItemStack getPickBlockStack() {
        return new ItemStack(PaleWorldItems.VAMPIRE_BAT_SPAWN_EGG);
    }

    abstract class MovementGoal extends Goal {
        public MovementGoal() {
            this.setControls(EnumSet.of(Control.MOVE));
        }

        protected boolean isNearTarget() {
            return VampireBat.this.targetPosition.squaredDistanceTo(VampireBat.this.getX(), VampireBat.this.getY(), VampireBat.this.getZ()) < (double)4.0F;
        }
    }
class CircularMovementGoal extends MovementGoal {
    private float angle;
    private float radius;
    private float yOffset;
    private float circlingDirection;

    CircularMovementGoal(){
    }

    @Override
    public boolean canStart() {
        return VampireBat.this.getTarget() == null || VampireBat.this.movementType == VampireBat.VampireBatMovementType.CIRCLE;
    }

    @Override
    public void start() {
        this.radius = 15.0F + VampireBat.this.random.nextFloat() * 10.0F;
        this.yOffset = 4.0F + VampireBat.this.random.nextFloat() * 9.0F;
        this.circlingDirection = VampireBat.this.random.nextBoolean() ? 1.0F : -1.0F;
        this.adjustDirection();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void tick() {
        if (VampireBat.this.random.nextInt(this.getTickCount(350)) == 0) {
            this.yOffset = 4.0F + VampireBat.this.random.nextFloat() * 9.0F;
        }

        if (VampireBat.this.random.nextInt(this.getTickCount(250)) == 0) {
            ++this.radius;
            if (this.radius > 45.0F) {
                this.radius = 15.0F;
                this.circlingDirection = -this.circlingDirection;
            }
        }

        if (VampireBat.this.random.nextInt(this.getTickCount(450)) == 0) {
            this.angle = VampireBat.this.random.nextFloat() * 2.0F * (float)Math.PI;
            this.adjustDirection();
        }

        if (this.isNearTarget()) {
            this.adjustDirection();
        }

        if (VampireBat.this.targetPosition.y < VampireBat.this.getY() && !VampireBat.this.getWorld().isAir(VampireBat.this.getBlockPos().down(1))) {
            this.yOffset = Math.max(1.0F, this.yOffset);
            this.adjustDirection();
        }

        if (VampireBat.this.targetPosition.y > VampireBat.this.getY() && !VampireBat.this.getWorld().isAir(VampireBat.this.getBlockPos().up(1))) {
            this.yOffset = Math.min(-1.0F, this.yOffset);
            this.adjustDirection();
        }

    }

    private void adjustDirection(){
        if (BlockPos.ORIGIN.equals(VampireBat.this.circlingCenter)) {
            VampireBat.this.circlingCenter = VampireBat.this.getBlockPos();
        }

        this.angle += this.circlingDirection * 15.0F * ((float)Math.PI / 180F);
        VampireBat.this.targetPosition = Vec3d.of(VampireBat.this.circlingCenter).add(this.radius * MathHelper.cos(this.angle), 4.0F + this.yOffset, this.radius * MathHelper.sin(this.angle));
    }


}

class SwoopGoal extends MovementGoal{

        SwoopGoal(){

        }
    @Override
    public boolean canStart() {
        return VampireBat.this.getTarget() != null && VampireBat.this.movementType == VampireBat.VampireBatMovementType.SWOOP;
    }

    @Override
    public boolean shouldContinue() {
        LivingEntity livingEntity = VampireBat.this.getTarget();
        if (livingEntity == null) {
            return false;
        } else if (!livingEntity.isAlive()) {
            return false;
        } else {
            if (livingEntity instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity)livingEntity;
                if (livingEntity.isSpectator() || playerEntity.isCreative()) {
                    return false;
                }
            }

            return this.canStart();
        }
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
        VampireBat.this.setTarget(null);
        VampireBat.this.movementType = VampireBatMovementType.CIRCLE;
    }

    @Override
    public void tick() {
        LivingEntity livingEntity = VampireBat.this.getTarget();
        if (livingEntity != null) {
            VampireBat.this.targetPosition = new Vec3d(livingEntity.getX(), livingEntity.getBodyY((double)0.5F), livingEntity.getZ());
            if (VampireBat.this.getBoundingBox().expand((double)0.2F).intersects(livingEntity.getBoundingBox())) {
                VampireBat.this.tryAttack(castToServerWorld(VampireBat.this.getWorld()), livingEntity);
                VampireBat.this.movementType = VampireBat.VampireBatMovementType.CIRCLE;
                if (!VampireBat.this.isSilent()) {
                    VampireBat.this.getWorld().syncWorldEvent(1039, VampireBat.this.getBlockPos(), 0);
                }
            } else if (VampireBat.this.horizontalCollision || VampireBat.this.hurtTime > 0) {
                VampireBat.this.movementType = VampireBat.VampireBatMovementType.CIRCLE;
            }
        }
    }
}

    class StartAttackGoal extends Goal{

        private int cooldown;

        StartAttackGoal(){
        }

        @Override
        public boolean canStart() {
            LivingEntity livingEntity = VampireBat.this.getTarget();
            return livingEntity != null ? VampireBat.this.testTargetPredicate(castToServerWorld(VampireBat.this.getWorld()), livingEntity, TargetPredicate.DEFAULT) : false;
        }

        @Override
        public void start() {
            this.cooldown = this.getTickCount(10);
            VampireBat.this.movementType = VampireBatMovementType.CIRCLE;
            this.startSwoop();
        }

        @Override
        public void stop() {
            VampireBat.this.circlingCenter = VampireBat.this.getWorld().getTopPosition(Heightmap.Type.MOTION_BLOCKING, VampireBat.this.circlingCenter).up(10 + VampireBat.this.random.nextInt(20));
        }

        @Override
        public void tick() {
            if (VampireBat.this.movementType == VampireBatMovementType.CIRCLE){
                --this.cooldown;
                if(this.cooldown <= 0){
                    VampireBat.this.movementType = VampireBatMovementType.SWOOP;
                    this.startSwoop();
                    this.cooldown = this.getTickCount(8 + VampireBat.this.random.nextInt(4) * 20);
                    VampireBat.this.playAttackSound();
                }
            }
        }

        public void startSwoop(){
            VampireBat.this.circlingCenter = VampireBat.this.getTarget().getBlockPos().up(20 + VampireBat.this.random.nextInt(20));
            if (VampireBat.this.circlingCenter.getY() < VampireBat.this.getWorld().getSeaLevel()){
                VampireBat.this.circlingCenter = new BlockPos(VampireBat.this.circlingCenter.getX(), VampireBat.this.getWorld().getSeaLevel() + 1, VampireBat.this.circlingCenter.getZ());
            }
        }
    }

    class FindTargetGoal extends Goal {
        private final TargetPredicate PLAYERS_IN_RANGE_PREDICATE = TargetPredicate.createAttackable().setBaseMaxDistance((double)64.0F);
        private int delay = toGoalTicks(20);

        FindTargetGoal(){}

        @Override
        public boolean canStart() {
            if (this.delay > 0) {
                --this.delay;
                return false;
            } else {
                this.delay = toGoalTicks(60);
                ServerWorld serverWorld = castToServerWorld(VampireBat.this.getWorld());
                List<PlayerEntity> list = serverWorld.getPlayers(this.PLAYERS_IN_RANGE_PREDICATE, VampireBat.this, VampireBat.this.getBoundingBox().expand((double)16.0F, (double)64.0F, (double)16.0F));
                if (!list.isEmpty()) {
                    list.sort(Comparator.comparing(Entity::getY).reversed());

                    for(PlayerEntity playerEntity : list) {
                        if (VampireBat.this.testTargetPredicate(serverWorld, playerEntity, TargetPredicate.DEFAULT)) {
                            VampireBat.this.setTarget(playerEntity);
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        @Override
        public boolean shouldContinue() {
            LivingEntity livingEntity = VampireBat.this.getTarget();
            return livingEntity != null && VampireBat.this.testTargetPredicate(castToServerWorld(VampireBat.this.getWorld()), livingEntity, TargetPredicate.DEFAULT);
        }
    }

}
