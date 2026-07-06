package dev.vesper.paleworld.common.blocks.custom;


import dev.vesper.paleworld.common.blocks.PaleWorldBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BigDripleafBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class BigDyingDripleafBlock extends BigDripleafBlock {
	private static final BooleanProperty WATERLOGGED;

	public BigDyingDripleafBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isBonemealSuccess(@NonNull Level level, @NonNull RandomSource randomSource, @NonNull BlockPos blockPos, @NonNull BlockState blockState) {
		return false;
	}

	public static boolean canGrowIntoDying(BlockState blockState){
		return blockState.isAir() || blockState.is(Blocks.WATER) || blockState.is(PaleWorldBlocks.SMALL_DYING_DRIPLEAF);
	}

	protected static boolean placeDyingDripleafAt(LevelAccessor level, BlockPos pos, FluidState fluidState, Direction direction){
		BlockState blockState = PaleWorldBlocks.BIG_DYING_DRIPLEAF.defaultBlockState().setValue(WATERLOGGED, fluidState.isSourceOfType(Fluids.WATER)).setValue(FACING, direction);
		return level.setBlock(pos, blockState, 3);
	}

	@Override
	protected boolean canSurvive(@NonNull BlockState blockState, LevelReader levelReader, @NonNull BlockPos blockPos) {
		BlockState blockState1 = levelReader.getBlockState(blockPos);
		return blockState1.is(this) || blockState1.is(PaleWorldBlocks.BIG_DYING_DRIPLEAF_STEM) || blockState1.is(BlockTags.SUPPORTS_BIG_DRIPLEAF);
	}

	@Override
	protected @NotNull BlockState updateShape(@NonNull BlockState blockState, @NonNull LevelReader levelReader, @NonNull ScheduledTickAccess scheduledTickAccess, @NonNull BlockPos blockPos, @NonNull Direction direction, @NonNull BlockPos blockPos2, @NonNull BlockState blockState2, @NonNull RandomSource randomSource) {
		if (direction == Direction.DOWN && !blockState.canSurvive(levelReader, blockPos)){
			return Blocks.AIR.defaultBlockState();
		} else {
			if (blockState.getValue(WATERLOGGED)){
				scheduledTickAccess.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelReader));
			}

			return direction == Direction.UP && blockState2.is(this) ? PaleWorldBlocks.BIG_DYING_DRIPLEAF_STEM.withPropertiesOf(blockState) : super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
		}
	}

	@Override
	public @NotNull BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
		BlockState blockState = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos().below());
		FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
		boolean bl = blockState.is(Blocks.BIG_DRIPLEAF) || blockState.is(PaleWorldBlocks.BIG_DYING_DRIPLEAF_STEM);
		return this.defaultBlockState().setValue(WATERLOGGED, fluidState.isSourceOfType(Fluids.WATER)).setValue(FACING, bl ? blockState.getValue(FACING) : blockPlaceContext.getHorizontalDirection().getOpposite());
	}

	static {
		WATERLOGGED = BlockStateProperties.WATERLOGGED;
	}
}
