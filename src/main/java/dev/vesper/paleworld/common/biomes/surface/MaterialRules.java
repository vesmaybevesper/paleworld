package dev.vesper.paleworld.common.biomes.surface;

import dev.vesper.paleworld.common.biomes.PaleWorldBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class MaterialRules {
	static SurfaceRules.RuleSource MOSS = makeSateRule(Blocks.PALE_MOSS_BLOCK);

	public static SurfaceRules.RuleSource createRules() {
		return SurfaceRules.sequence(
				SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(PaleWorldBiomes.PALE_CAVE), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MOSS))),
				SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(PaleWorldBiomes.PALE_CAVE), SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, MOSS)))
		);
	}

	private static SurfaceRules.RuleSource makeSateRule(Block block){
		return SurfaceRules.state(block.defaultBlockState());
	}

	public static void init() {}
}
