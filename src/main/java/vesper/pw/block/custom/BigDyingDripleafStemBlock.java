package vesper.pw.block.custom;

import net.minecraft.block.BigDripleafStemBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import vesper.pw.block.PaleWorldBlocks;

import java.util.Optional;

public class BigDyingDripleafStemBlock extends BigDripleafStemBlock {
    private static final BooleanProperty WATERLOGGED;
    public BigDyingDripleafStemBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        BlockState blockState2 = world.getBlockState(pos.up());
        return (blockState.isOf(this) || blockState.isIn(BlockTags.BIG_DRIPLEAF_PLACEABLE)) && (blockState2.isOf(this) || blockState2.isOf(PaleWorldBlocks.BIG_DYING_DRIPLEAF));
    }


    protected static boolean placeStemAtDying(WorldAccess world, BlockPos pos, FluidState fluidState, Direction direction) {
        BlockState blockState = (BlockState)((BlockState)PaleWorldBlocks.BIG_DYING_DRIPLEAF_STEM.getDefaultState().with(WATERLOGGED, fluidState.isEqualAndStill(Fluids.WATER))).with(FACING, direction);
        return world.setBlockState(pos, blockState, 3);
    }



    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        Optional<BlockPos> optional = BlockLocating.findColumnEnd(world, pos, state.getBlock(), Direction.UP, Blocks.BIG_DRIPLEAF);
        if (!optional.isEmpty()) {
            BlockPos blockPos = (BlockPos)optional.get();
            BlockPos blockPos2 = blockPos.up();
            Direction direction = (Direction)state.get(FACING);
            placeStemAtDying(world, blockPos, world.getFluidState(blockPos), direction);
            BigDyingDripleafBlock.placeDyingDripleafAt(world, blockPos2, world.getFluidState(blockPos2), direction);
        }
    }

    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(PaleWorldBlocks.BIG_DYING_DRIPLEAF);
    }

    static {
        WATERLOGGED = Properties.WATERLOGGED;
    }
}
