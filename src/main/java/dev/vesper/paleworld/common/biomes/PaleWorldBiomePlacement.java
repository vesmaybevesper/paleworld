package dev.vesper.paleworld.common.biomes;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import net.minecraft.world.level.biome.Biomes;

public class PaleWorldBiomePlacement {
	public static void place(){
		BiomePlacement.replaceOverworld(Biomes.DARK_FOREST, Biomes.PALE_GARDEN, 0.25);
		BiomePlacement.replaceOverworld(Biomes.LUSH_CAVES, PaleWorldBiomes.PALE_CAVE, 0.1);
		BiomePlacement.replaceOverworld(Biomes.FOREST, PaleWorldBiomes.PALE_VALLEY,0.09);
	}


}
