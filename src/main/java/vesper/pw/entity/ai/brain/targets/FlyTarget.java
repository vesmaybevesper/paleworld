package vesper.pw.entity.ai.brain.targets;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.brain.BlockPosLookTarget;
import net.minecraft.entity.ai.brain.EntityLookTarget;
import net.minecraft.entity.ai.brain.LookTarget;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class FlyTarget {

    private final LookTarget lookTarget;
    private final float speed;
    private final int completionRange;

    public FlyTarget(BlockPos pos, float speed, int completionRange){
      this((LookTarget)(new BlockPosLookTarget(pos)), speed, completionRange);
    }

    public FlyTarget(Vec3d pos, float speed, int completionRange){
        this((LookTarget)(new BlockPosLookTarget(BlockPos.ofFloored(pos))), speed, completionRange);
    }

    public FlyTarget(Entity entity, float speed, int completionRange){
        this((LookTarget)(new EntityLookTarget(entity, false)), speed, completionRange);
    }

    public FlyTarget(LookTarget lookTarget, float speed, int completionRange){
        this.lookTarget = lookTarget;
        this.speed = speed;
        this.completionRange = completionRange;
    }


    public LookTarget getLookTarget() {
        return lookTarget;
    }

    public float getSpeed() {
        return speed;
    }

    public int getCompletionRange() {
        return completionRange;
    }
}
