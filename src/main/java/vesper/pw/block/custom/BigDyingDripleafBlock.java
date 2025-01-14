package vesper.pw.block.custom;

import net.minecraft.block.BigDripleafBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import vesper.pw.block.PaleWorldBlocks;

public class BigDyingDripleafBlock extends BigDripleafBlock {
    private static final BooleanProperty WATERLOGGED;

    public BigDyingDripleafBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return false;
    }


    public static boolean canGrowIntoDying(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) || state.isOf(PaleWorldBlocks.SMALL_DYING_DRIPLEAF);
    }

    protected static boolean placeDyingDripleafAt(WorldAccess world, BlockPos pos, FluidState fluidState, Direction direction) {
        BlockState blockState = (BlockState)((BlockState)PaleWorldBlocks.BIG_DYING_DRIPLEAF.getDefaultState().with(WATERLOGGED, fluidState.isEqualAndStill(Fluids.WATER))).with(FACING, direction);
        return world.setBlockState(pos, blockState, 3);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isOf(this) || blockState.isOf(PaleWorldBlocks.BIG_DYING_DRIPLEAF_STEM) || blockState.isIn(BlockTags.BIG_DRIPLEAF_PLACEABLE);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            if ((Boolean)state.get(WATERLOGGED)) {
                tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }

            return direction == Direction.UP && neighborState.isOf(this) ? PaleWorldBlocks.BIG_DYING_DRIPLEAF_STEM.getStateWithProperties(state) : super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().down());
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean bl = blockState.isOf(Blocks.BIG_DRIPLEAF) || blockState.isOf(PaleWorldBlocks.BIG_DYING_DRIPLEAF_STEM);
        return (BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, fluidState.isEqualAndStill(Fluids.WATER))).with(FACING, bl ? (Direction)blockState.get(FACING) : ctx.getHorizontalPlayerFacing().getOpposite());
    }

    static {
        WATERLOGGED = Properties.WATERLOGGED;
    }
}
