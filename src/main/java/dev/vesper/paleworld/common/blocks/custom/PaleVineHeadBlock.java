package dev.vesper.paleworld.common.blocks.custom;

import com.mojang.serialization.MapCodec;
import dev.vesper.paleworld.common.blocks.PaleWorldBlocks;
import dev.vesper.paleworld.common.items.PaleWorldItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class PaleVineHeadBlock extends GrowingPlantHeadBlock implements PaleVines {
	public static final MapCodec<PaleVineHeadBlock> CODEC = simpleCodec(PaleVineHeadBlock::new);

	public PaleVineHeadBlock(Properties properties) {
		super(properties, Direction.DOWN, SHAPE, false, 0.1);
	}

	@Override
	protected @NotNull MapCodec<? extends GrowingPlantHeadBlock> codec() {
		return CODEC;
	}

	@Override
	protected @NotNull Block getBodyBlock() {
		return PaleWorldBlocks.PALE_VINE_BODY;
	}

	@Override
	protected int getBlocksToGrowWhenBonemealed(@NonNull RandomSource randomSource) {
		return 1;
	}

	@Override
	protected boolean canGrowInto(BlockState blockState) {
		return blockState.isAir();
	}

	@Override
	protected void onPlace(@NonNull BlockState blockState, Level level, @NonNull BlockPos blockPos, @NonNull BlockState blockState2, boolean bl) {
		if (!level.isClientSide()){
			level.scheduleTick(blockPos, this, 20);
		}
		super.onPlace(blockState, level, blockPos, blockState2, bl);
	}

	@Override
	protected void tick(@NonNull BlockState blockState, @NonNull ServerLevel serverLevel, @NonNull BlockPos blockPos, @NonNull RandomSource randomSource) {

		if (!PaleVines.hasBerries(blockState) && randomSource.nextFloat() < 0.10f){
			serverLevel.setBlock(blockPos, blockState.setValue(BERRIES, true), 2);
		}
		serverLevel.scheduleTick(blockPos, this, Mth.randomBetweenInclusive(randomSource, 100, 200));
	}

	@Override
	protected @NotNull BlockState updateBodyAfterConvertedFromHead(BlockState blockState, BlockState blockState2) {
		return blockState2.setValue(BERRIES, blockState.getValue(BERRIES));
	}

	@Override
	protected @NotNull BlockState getGrowIntoState(@NonNull BlockState blockState, @NonNull RandomSource randomSource) {
		return super.getGrowIntoState(blockState, randomSource).setValue(BERRIES, randomSource.nextFloat() < 0.11f);
	}

	@Override
	protected @NotNull ItemStack getCloneItemStack(@NonNull LevelReader levelReader, @NonNull BlockPos blockPos, @NonNull BlockState blockState, boolean bl) {
		return new ItemStack(PaleWorldItems.PALE_BERRIES);
	}

	@Override
	protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState blockState, @NonNull Level level, @NonNull BlockPos blockPos, @NonNull Player player, @NonNull BlockHitResult blockHitResult) {
		return PaleVines.pickBerries(player, blockState, level, blockPos);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(BERRIES);
	}

	@Override
	public boolean isValidBonemealTarget(@NonNull LevelReader levelReader, @NonNull BlockPos blockPos, BlockState blockState) {
		return !blockState.getValue(BERRIES);
	}

	@Override
	public boolean isBonemealSuccess(@NonNull Level level, @NonNull RandomSource randomSource, @NonNull BlockPos blockPos, @NonNull BlockState blockState) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel serverLevel, @NonNull RandomSource randomSource, @NonNull BlockPos blockPos, BlockState blockState) {
		serverLevel.setBlock(blockPos, blockState.setValue(BERRIES, true), 2);
	}

	@Override
	protected boolean canSurvive(@NonNull BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
		return levelReader.getBlockState(blockPos.above()).is(Blocks.PALE_MOSS_BLOCK);
	}
}
