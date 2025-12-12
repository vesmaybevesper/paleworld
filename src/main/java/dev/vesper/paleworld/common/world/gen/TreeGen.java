package dev.vesper.paleworld.common.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class TreeGen {
	public static void generateTrees(){
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.BARE_STRIPPED_SMALL_PALE_OAK);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.BARE_SMALL_PALE_OAK);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.BARE_PALE_OAK);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.STRIPPED_BARE_PALE_OAK);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.STRIPPED_PALE_OAK);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.TALL_STRIPPED_PALE_OAK);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.TALL_PALE_OAK);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.SMALL_PALE_OAK);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.STRIPPED_SMALL_PALE_OAK);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.BARE_PALE_OAK_WITH_HEART);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.BARE_SMALL_PALE_OAK_WITH_HEART);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.TALL_PALE_OAK_WITH_HEART);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.STRIPPED_PALE_OAK);

		BiomeModifications.addFeature(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_VALLEY), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.BARE_STRIPPED_SMALL_PALE_OAK);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_VALLEY), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.BARE_SMALL_PALE_OAK);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_VALLEY), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.SMALL_PALE_OAK);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_VALLEY), GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.STRIPPED_SMALL_PALE_OAK);
	}
}
