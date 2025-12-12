package dev.vesper.paleworld.common.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class RockGen {
	public void generate(){
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.SURFACE_STRUCTURES, PaleWorldPlacedFeatures.PALE_SPIKE);
	}
}
