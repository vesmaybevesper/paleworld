package dev.vesper.paleworld.common.blocks.custom;

import dev.vesper.paleworld.common.world.blocks.PaleWorldBlocks;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BigDripleafStemBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class BigDyingDripleafStemBlock extends BigDripleafStemBlock {
	private static final BooleanProperty WATERLOGGED;
	public BigDyingDripleafStemBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
		return false;
	}

	@Override
	protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
		BlockPos blockPos1 = blockPos.below();
		BlockState blockState1 = levelReader.getBlockState(blockPos1);
		BlockState blockState2 = levelReader.getBlockState(blockPos.above());
		return (blockState1.is(this) || blockState1.is(BlockTags.BIG_DRIPLEAF_PLACEABLE)) && (blockState2.is(this) || blockState2.is(PaleWorldBlocks.BIG_DYING_DRIPLEAF));
	}

	protected static boolean placeStemAtDying(LevelAccessor world, BlockPos pos, FluidState fluidState, Direction direction) {
		BlockState blockState = (BlockState)((BlockState)PaleWorldBlocks.BIG_DYING_DRIPLEAF_STEM.defaultBlockState().setValue(WATERLOGGED, fluidState.isSourceOfType(Fluids.WATER))).setValue(FACING, direction);
		return world.setBlock(pos, blockState, 3);
	}

	@Override
	protected @NotNull ItemStack getCloneItemStack(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
		return new ItemStack(PaleWorldBlocks.BIG_DYING_DRIPLEAF);
	}

	@Override
	public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
		Optional<BlockPos> optional = BlockUtil.getTopConnectedBlock(serverLevel, blockPos, blockState.getBlock(), Direction.UP, Blocks.BIG_DRIPLEAF);
		if (!optional.isEmpty()) {
			BlockPos blockPos1 = (BlockPos)optional.get();
			BlockPos blockPos2 = blockPos1.above();
			Direction direction = (Direction)blockState.getValue(FACING);
			placeStemAtDying(serverLevel, blockPos, serverLevel.getFluidState(blockPos), direction);
			BigDyingDripleafBlock.placeDyingDripleafAt(serverLevel, blockPos2, serverLevel.getFluidState(blockPos2), direction);
		}
	}

	static {
		WATERLOGGED = BlockStateProperties.WATERLOGGED;
	}
}
