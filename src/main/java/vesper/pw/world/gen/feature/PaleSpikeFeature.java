package vesper.pw.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import vesper.pw.block.PaleWorldBlocks;

public class PaleSpikeFeature extends Feature<DefaultFeatureConfig> {
    public PaleSpikeFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();

        StructureWorldAccess structureWorldAccess;
        for(structureWorldAccess = context.getWorld(); structureWorldAccess.isAir(blockPos) && blockPos.getY() > structureWorldAccess.getBottomY() + 2; blockPos = blockPos.down()) {
        }

        if (!structureWorldAccess.getBlockState(blockPos).isOf(Blocks.GRASS_BLOCK) && !structureWorldAccess.getBlockState(blockPos).isOf(Blocks.PALE_MOSS_BLOCK)) {
            return false;
        } else {
            blockPos = blockPos.up(random.nextInt(10));
            int i = random.nextInt(5) + 7;
            int j = i / 4 + random.nextInt(5);
            if (j >= 1 && random.nextInt(60) == 0) {
                blockPos = blockPos.up(20 + random.nextInt(40));
            }

            for(int k = 0; k < i; ++k) {
                float f = (1.0F - (float)k / (float)i) * (float)j;
                int l = MathHelper.ceil(f);

                for(int m = -l; m <= l; ++m) {
                    float g = (float)MathHelper.abs(m) - 0.25F;

                    for(int n = -l; n <= l; ++n) {
                        float h = (float)MathHelper.abs(n) - 0.25F;
                        if ((m == 0 && n == 0 || !(g * g + h * h > f * f)) && (m != -l && m != l && n != -l && n != l || !(random.nextFloat() > 0.75F))) {
                            BlockState blockState = structureWorldAccess.getBlockState(blockPos.add(m, k, n));
                            if (blockState.isAir() || isSoil(blockState) || blockState.isOf(Blocks.PALE_MOSS_BLOCK) || blockState.isOf(Blocks.GRASS_BLOCK)) {
                                this.setBlockState(structureWorldAccess, blockPos.add(m, k, n), PaleWorldBlocks.PALE_STONE.getDefaultState());
                            }

                            if (k != 0 && l > 1) {
                                blockState = structureWorldAccess.getBlockState(blockPos.add(m, -k, n));
                                if (blockState.isAir() || isSoil(blockState) || blockState.isOf(Blocks.PALE_MOSS_BLOCK) || blockState.isOf(Blocks.GRASS_BLOCK)) {
                                    this.setBlockState(structureWorldAccess, blockPos.add(m, -k, n), PaleWorldBlocks.PALE_STONE.getDefaultState());
                                }
                            }
                        }
                    }
                }
            }

            int k = j - 1;
            if (k < 0) {
                k = 0;
            } else if (k > 1) {
                k = 1;
            }

            for(int o = -k; o <= k; ++o) {
                for(int l = -k; l <= k; ++l) {
                    BlockPos blockPos2 = blockPos.add(o, -1, l);
                    int p = 50;
                    if (Math.abs(o) == 1 && Math.abs(l) == 1) {
                        p = random.nextInt(5);
                    }

                    while(blockPos2.getY() > 50) {
                        BlockState blockState2 = structureWorldAccess.getBlockState(blockPos2);
                        if (!blockState2.isAir() && !isSoil(blockState2) && !blockState2.isOf(Blocks.GRASS_BLOCK) && !blockState2.isOf(Blocks.PALE_MOSS_BLOCK) && !blockState2.isOf(PaleWorldBlocks.PALE_STONE)) {
                            break;
                        }

                        this.setBlockState(structureWorldAccess, blockPos2, PaleWorldBlocks.PALE_STONE.getDefaultState());
                        blockPos2 = blockPos2.down();
                        --p;
                        if (p <= 0) {
                            blockPos2 = blockPos2.down(random.nextInt(5) + 1);
                            p = random.nextInt(5);
                        }
                    }
                }
            }

            return true;
        }
    }
}
