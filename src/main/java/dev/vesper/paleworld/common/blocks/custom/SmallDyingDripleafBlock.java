package dev.vesper.paleworld.common.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SmallDripleafBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class SmallDyingDripleafBlock extends SmallDripleafBlock {
	public SmallDyingDripleafBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isBonemealSuccess(@NonNull Level level, @NonNull RandomSource randomSource, @NonNull BlockPos blockPos, @NonNull BlockState blockState) {
		return false;
	}

	@Override
	public boolean isValidBonemealTarget(@NonNull LevelReader levelReader, @NonNull BlockPos blockPos, @NonNull BlockState blockState) {
		return false;
	}
}
