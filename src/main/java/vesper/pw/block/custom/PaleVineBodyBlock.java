package vesper.pw.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import vesper.pw.block.PaleWorldBlocks;
import vesper.pw.item.PaleWorldItems;


public class PaleVineBodyBlock extends AbstractPlantBlock implements PaleVines{

    public static final MapCodec<PaleVineBodyBlock> CODEC = createCodec(PaleVineBodyBlock::new);

    public static final int AGE = 0;



    public PaleVineBodyBlock(AbstractBlock.Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false);
        this.setDefaultState(this.getStateManager().getDefaultState().with(BERRIES, false));
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient) {world.scheduleBlockTick(pos, this, 20);}
        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!PaleVines.hasBerries(state) && random.nextFloat() < 0.25F){
            world.setBlockState(pos,state.with(BERRIES, true), 2);
        }
        world.scheduleBlockTick(pos, this, MathHelper.nextBetween(random, 100, 200));
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock) PaleWorldBlocks.PALE_VINE;
    }

    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(PaleWorldItems.PALE_BERRIES);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        return PaleVines.pickBerries(player, state, world, pos);
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
    protected BlockState copyState(BlockState from, BlockState to) {
        return (BlockState) to.with(BERRIES, (Boolean) from.get(BERRIES));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BERRIES);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos,(BlockState) state.with(BERRIES, false), 2);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return !(Boolean)state.get(BERRIES);
    }

}
