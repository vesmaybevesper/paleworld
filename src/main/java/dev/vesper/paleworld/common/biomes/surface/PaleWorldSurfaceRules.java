package dev.vesper.paleworld.common.biomes.surface;

import dev.vesper.paleworld.common.biomes.PaleWorldBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class PaleWorldSurfaceRules {

    static SurfaceRules.RuleSource MOSS = makeRules(Blocks.PALE_MOSS_BLOCK);

    public static SurfaceRules.RuleSource makeRules(Block block){
        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(PaleWorldBiomes.PALE_CAVE), SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, MOSS))),
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(PaleWorldBiomes.PALE_CAVE), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MOSS))));
    }
}
