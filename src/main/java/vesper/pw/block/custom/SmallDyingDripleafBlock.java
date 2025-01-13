package vesper.pw.block.custom;


import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;


public class SmallDyingDripleafBlock extends SmallDripleafBlock {

    public SmallDyingDripleafBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return false;
    }
}
