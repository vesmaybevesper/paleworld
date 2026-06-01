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
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class PaleVineBodyBlock extends GrowingPlantBodyBlock implements PaleVines {
	public static final MapCodec<PaleVineBodyBlock> CODEC = simpleCodec(PaleVineBodyBlock::new);

	public static final int AGE = 0;

	public PaleVineBodyBlock(Properties properties) {
		super(properties, Direction.DOWN, SHAPE, false);
	}

	@Override
	protected void onPlace(@NonNull BlockState blockState, Level level, @NonNull BlockPos blockPos, @NonNull BlockState blockState2, boolean bl) {
		if (!level.isClientSide()) {
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
	protected @NotNull ItemStack getCloneItemStack(@NonNull LevelReader levelReader, @NonNull BlockPos blockPos, @NonNull BlockState blockState, boolean bl) {
		return new ItemStack(PaleWorldItems.PALE_BERRIES);
	}

	@Override
	protected @NotNull InteractionResult useWithoutItem(@NonNull BlockState blockState, @NonNull Level level, @NonNull BlockPos blockPos, @NonNull Player player, @NonNull BlockHitResult blockHitResult) {
		return PaleVines.pickBerries(player, blockState, level, blockPos);
	}

	@Override
	protected @NotNull BlockState updateShape(@NonNull BlockState blockState, @NonNull LevelReader levelReader, @NonNull ScheduledTickAccess scheduledTickAccess, @NonNull BlockPos blockPos, @NonNull Direction direction, @NonNull BlockPos blockPos2, @NonNull BlockState blockState2, @NonNull RandomSource randomSource) {
		if (levelReader instanceof Level mutableLevel){
			if (direction == Direction.DOWN && levelReader.getBlockState(blockPos.below()).isAir()){
				mutableLevel.setBlockAndUpdate(blockPos, PaleWorldBlocks.PALE_VINE.defaultBlockState());
			}
		}
		return super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
	}

	@Override
	protected @NotNull BlockState updateHeadAfterConvertedFromBody(BlockState blockState, BlockState blockState2) {
		return blockState2.setValue(BERRIES, blockState.getValue(BERRIES));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BERRIES);
	}

	@Override
	public boolean isBonemealSuccess(@NonNull Level level, @NonNull RandomSource randomSource, @NonNull BlockPos blockPos, @NonNull BlockState blockState) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel serverLevel, @NonNull RandomSource randomSource, @NonNull BlockPos blockPos, BlockState blockState) {
		serverLevel.setBlock(blockPos, blockState.setValue(BERRIES, false), 2);
	}

	@Override
	public boolean isValidBonemealTarget(@NonNull LevelReader levelReader, @NonNull BlockPos blockPos, BlockState blockState) {
		return !blockState.getValue(BERRIES);
	}

	@Override
	protected @NotNull MapCodec<? extends GrowingPlantBodyBlock> codec() {
		return CODEC;
	}

	@Override
	protected @NotNull GrowingPlantHeadBlock getHeadBlock() {
		return (GrowingPlantHeadBlock) PaleWorldBlocks.PALE_VINE;
	}
}
