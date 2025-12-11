package dev.vesper.paleworld.common.world.gen.feature;

import com.mojang.serialization.Codec;
import dev.vesper.paleworld.common.blocks.PaleWorldBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class PaleSpikeFeature extends Feature<NoneFeatureConfiguration> {

	public PaleSpikeFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
		BlockPos blockPos = featurePlaceContext.origin();
		RandomSource random = featurePlaceContext.random();

		WorldGenLevel structureWorldAccess;
		for (structureWorldAccess = featurePlaceContext.level(); structureWorldAccess.isEmptyBlock(blockPos) && blockPos.getY() > structureWorldAccess.getMinY() + 2; blockPos = blockPos.below()){
		}

		if (!structureWorldAccess.getBlockState(blockPos).is(Blocks.GRASS_BLOCK) && !structureWorldAccess.getBlockState(blockPos).is(Blocks.PALE_MOSS_BLOCK)){
			return false;
		} else {
			 blockPos = blockPos.above(random.nextInt(10));
			 int i = random.nextInt(5) + 7;
			 int j = i / 4 + random.nextInt(5);
			 if (j >= 1 && random.nextInt(60) == 0){
				 blockPos = blockPos.above(20 + random.nextInt(40));
			 }

			 for (int k = 0; k < i; ++k){
				 float f = (1.0f - (float) k / (float) i) * (float) j;
				 int l = Mth.ceil(f);

				 for (int m = -l; m <= l; ++m){
					float g = Mth.abs(m) - 0.25f;

					for (int n =-l; n <= l; ++n){
						float h = Mth.abs(n) - 0.25f;
						if ((m == 0 & n == 0 || !(g * g + h * h > f * f)) && (m != -l && m != l && n != -l && n != l || !(random.nextFloat() > 0.75f))){
							BlockState blockState = structureWorldAccess.getBlockState(blockPos.offset(m, k, n));
							if (blockState.isAir() || isDirt(blockState) || blockState.is(Blocks.PALE_MOSS_BLOCK) || blockState.is(Blocks.GRASS_BLOCK)){
								this.setBlock(structureWorldAccess, blockPos.offset(m, k, n), PaleWorldBlocks.PALE_STONE.defaultBlockState());
							}

							if (k != 0 && l > 1){
								blockState = structureWorldAccess.getBlockState(blockPos.offset(m, -k, n));
								if (blockState.isAir() || isDirt(blockState) || blockState.is(Blocks.PALE_MOSS_BLOCK) || blockState.is(Blocks.GRASS_BLOCK)){
									this.setBlock(structureWorldAccess, blockPos.offset(m, -k, n), PaleWorldBlocks.PALE_STONE.defaultBlockState());
								}
							}
						}
					}
				 }
			 }

			 int k = j - 1;
			 if (k < 0){
				 k = 0;
			 } else if (k > 1){
				 k = 1;
			 }

			 for (int o = -k; o <= k; ++o){
				 for (int l = - k; l <= k; ++l){
					 BlockPos blockPos1 = blockPos.offset(o, -1, l);
					 int p = 50;
					 if (Mth.abs(o) == 1 && Mth.abs(l) == 1){
						 p = random.nextInt(5);
					 }

					 while (blockPos1.getY() > 50){
						 BlockState blockState = structureWorldAccess.getBlockState(blockPos1);
						 if (!blockState.isAir() && !isDirt(blockState) && !blockState.is(Blocks.PALE_MOSS_BLOCK) && !blockState.is(Blocks.GRASS_BLOCK) && !blockState.is(PaleWorldBlocks.PALE_STONE)){
							break;
						 }

						 this.setBlock(structureWorldAccess, blockPos1, PaleWorldBlocks.PALE_STONE.defaultBlockState());
						 blockPos1 = blockPos1.below();
						 --p;
						 if (p <= 0){
							 blockPos1 = blockPos1.below();
							 p = random.nextInt(5);
						 }
					 }
				 }
			 }
			 return true;
		}
	}
}
