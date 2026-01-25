package dev.vesper.paleworld.common.biomes;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import net.minecraft.world.level.biome.Biomes;

public class PaleWorldBiomePlacement {
	public static void place(){
		BiomePlacement.replaceOverworld(Biomes.DARK_FOREST, Biomes.PALE_GARDEN, 0.15);
		BiomePlacement.replaceOverworld(Biomes.LUSH_CAVES, PaleWorldBiomes.PALE_CAVE, 0.1);
		BiomePlacement.addSubOverworld(Biomes.PALE_GARDEN, PaleWorldBiomes.PALE_VALLEY, CriterionBuilder.NEAR_BORDER);
	}


}
