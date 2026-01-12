package dev.vesper.paleworld.common.biomes;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import net.minecraft.world.level.biome.Biomes;

public class PaleWorldBiomePlacement {
	public static void place(){
		BiomePlacement.replaceOverworld(Biomes.DARK_FOREST, Biomes.PALE_GARDEN, 0.15);
		BiomePlacement.replaceOverworld(Biomes.LUSH_CAVES, PaleWorldBiomes.PALE_CAVE, 0.1);
		BiomePlacement.replaceOverworld(Biomes.FOREST, PaleWorldBiomes.PALE_VALLEY,0.09);
		//I want it to be a sub biome but i cant get it looking good so for now its a stand-alone biome
		//BiomePlacement.addSubOverworld(Biomes.PALE_GARDEN, PaleWorldBiomes.PALE_VALLEY, CriterionBuilder.ratio(RatioTargets.EDGE, .3f, .5f));
	}


}
