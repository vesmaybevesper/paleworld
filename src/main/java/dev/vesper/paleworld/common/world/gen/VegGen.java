package dev.vesper.paleworld.common.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class VegGen {
	public static void generateVeg(){
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.CHRYSANTHEMUM);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_VALLEY), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.CHRYSANTHEMUM);
	}
}
