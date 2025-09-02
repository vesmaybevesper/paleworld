package vesper.pw.entity.ai.targets;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.NoPenaltySolidTargeting;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.WalkTarget;
import net.minecraft.entity.ai.brain.task.TargetUtil;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.object.MemoryTest;
import net.tslat.smartbrainlib.object.SquareRadius;
import net.tslat.smartbrainlib.object.ToFloatBiFunction;
import net.tslat.smartbrainlib.util.BrainUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;

public class FlyToAttackTarget <E extends PathAwareEntity> extends ExtendedBehaviour<E> {
    private static final MemoryTest MEMORY_REQUIREMENTS = MemoryTest.builder(1).noMemory(MemoryModuleType.WALK_TARGET);
    protected ToFloatBiFunction<E, Vec3d> speedModifier = (entity, targetPos) -> 1f;
    protected SquareRadius radius = new SquareRadius(10, 7);
    protected BiPredicate<E, Vec3d> positionPredicate = (entity, pos) -> true;
    protected ToIntFunction<E> verticalWeight = entity -> -2;

    public FlyToAttackTarget(){

    }

    public FlyToAttackTarget<E> setRadius(double radius) {
        return setRadius(radius, radius);
    }

    public FlyToAttackTarget<E> setRadius(double xz, double y) {
        this.radius = new SquareRadius(xz, y);

        return this;
    }

    public FlyToAttackTarget<E> speedModifier(float modifier) {
        return speedModifier((entity, targetPos) -> modifier);
    }

    public FlyToAttackTarget<E> speedModifier(ToFloatBiFunction<E, Vec3d> function) {
        this.speedModifier = function;

        return this;
    }

    public FlyToAttackTarget<E> flightTargetPredicate(BiPredicate<E, Vec3d> predicate) {
        this.positionPredicate = predicate;

        return this;
    }

    @Override
    protected List<Pair<MemoryModuleType<?>, MemoryModuleState>> getMemoryRequirements() {
        return MEMORY_REQUIREMENTS;
    }

    @Override
    protected void start(E entity) {
        Brain<?> brain = entity.getBrain();
        LivingEntity target = BrainUtil.getTargetOfEntity(entity);

        if (entity.getVisibilityCache().canSee(target) && TargetUtil.isTargetWithinAttackRange(entity, target, 1)) {
            BrainUtil.clearMemory(brain, MemoryModuleType.WALK_TARGET);
        } else {

        }
    }

    @Nullable
    protected Vec3d getTargetPos(E entity){
        Vec3d entityFacing = entity.getRotationVec(0);
        Vec3d hoverPos = AboveGroundTargeting.find(entity, (int) Math.ceil(this.radius.xzRadius()), (int) Math.ceil(this.radius.yRadius()), entityFacing.x, entityFacing.z, MathHelper.HALF_PI, 3, 1);

        if (hoverPos != null){
            return hoverPos;
        }
        return NoPenaltySolidTargeting.find(entity, (int)(Math.ceil(this.radius.xzRadius())), (int)Math.ceil(this.radius.yRadius()), this.verticalWeight.applyAsInt(entity), entityFacing.x, entityFacing.z, MathHelper.HALF_PI);
    }


}
