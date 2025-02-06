package vesper.pw.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PlayerHeadItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import vesper.pw.block.PaleWorldBlocks;
import vesper.pw.item.PaleWorldItems;

public class PaleVineHeadBlock extends AbstractPlantStemBlock implements PaleVines {

    public static final MapCodec<PaleVineHeadBlock> CODEC = createCodec(PaleVineHeadBlock::new);

    public PaleVineHeadBlock(AbstractBlock.Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false, 0.1);
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
    protected MapCodec<? extends AbstractPlantStemBlock> getCodec() {
        return null;
    }

    @Override
    protected int getGrowthLength(Random random) {
        return 1;
    }

    @Override
    protected boolean chooseStemState(BlockState state) {
        return state.isAir();
    }

    @Override
    protected Block getPlant() {
        return PaleWorldBlocks.PALE_VINE_BODY;
    }

    @Override
    protected BlockState copyState(BlockState from, BlockState to) {
        return (BlockState) to.with(BERRIES, from.get(BERRIES));
    }

    @Override
    protected BlockState age(BlockState state, Random random) {
        return (BlockState)super.age(state, random).with(BERRIES, random.nextFloat() < 0.11F);
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(new Property[]{BERRIES});
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return !(Boolean)state.get(BERRIES);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, (BlockState)state.with(BERRIES, true), 2);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.up()).isOf(Blocks.PALE_MOSS_BLOCK);
    }

}

