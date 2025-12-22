package dev.vesper.paleworld.common.biomes;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import net.minecraft.world.level.biome.Biomes;

public class PaleWorldBiomePlacement {
	public static void place(){
		BiomePlacement.replaceOverworld(Biomes.PALE_GARDEN, Biomes.DARK_FOREST, 0.25);
		BiomePlacement.replaceOverworld(PaleWorldBiomes.PALE_CAVE, Biomes.LUSH_CAVES, 0.15);
		BiomePlacement.replaceOverworld(PaleWorldBiomes.PALE_VALLEY, Biomes.FOREST, 0.1);
	}
}
