package vesper.pw.block.custom;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import vesper.pw.block.PaleWorldBlocks;

import static net.minecraft.block.CaveVines.BERRIES;


public class PaleVineBodyBlock extends AbstractPlantBlock implements CaveVines{

    public static final MapCodec<PaleVineBodyBlock> CODEC = createCodec(PaleVineBodyBlock::new);

    public static final int AGE = 1;


    public PaleVineBodyBlock(AbstractBlock.Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false);
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock) PaleWorldBlocks.PALE_VINE;
    }

    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(PaleWorldBlocks.PALE_VINE);
    }

    @Override
    protected MapCodec<? extends AbstractPlantBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (world instanceof World mutableWorld) {
            if (direction == Direction.DOWN && world.getBlockState(pos.down()).isAir()) {
                mutableWorld.setBlockState(pos, PaleWorldBlocks.PALE_VINE.getDefaultState());
            }
        }
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add();
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos,(BlockState) state.with(BERRIES, false), 2);
    }
}
