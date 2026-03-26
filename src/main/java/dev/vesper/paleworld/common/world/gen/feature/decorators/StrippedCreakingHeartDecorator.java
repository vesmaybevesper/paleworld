/*
package dev.vesper.paleworld.common.world.gen.feature.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CreakingHeartBlock;
import net.minecraft.world.level.block.state.properties.CreakingHeartState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StrippedCreakingHeartDecorator extends TreeDecorator {
	public static final MapCodec<StrippedCreakingHeartDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
			.fieldOf("probability")
			.xmap(StrippedCreakingHeartDecorator::new, strippedCreakingHeartDecorator -> strippedCreakingHeartDecorator.probability);
	private final float probability;

	public StrippedCreakingHeartDecorator(float chance) {
		this.probability = chance;
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return TreeDecoratorType.CREAKING_HEART;
	}

	@Override
	public void place(Context context) {
		RandomSource randomSource = context.random();
		List<BlockPos> list = context.logs();
		if (!list.isEmpty()) {
			if (!(randomSource.nextFloat() >= this.probability)) {
				List<BlockPos> list2 = new ArrayList(list);
				Util.shuffle(list2, randomSource);
				Optional<BlockPos> optional = list2.stream().filter(blockPos -> {
					for (Direction direction : Direction.values()) {
						if (!context.checkBlock(blockPos.relative(direction), blockState -> blockState.is(BlockTags.LOGS))) {
							return false;
						}
					}

					return true;
				}).findFirst();
				if (!optional.isEmpty()) {
					context.setBlock(
							(BlockPos)optional.get(),
							Blocks.CREAKING_HEART.defaultBlockState().setValue(CreakingHeartBlock.STATE, CreakingHeartState.DORMANT).setValue(CreakingHeartBlock.NATURAL, true)
					);
				}
			}
		}
	}
}
*/
