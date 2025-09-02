package vesper.pw.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import vesper.pw.biomes.PaleWorldBiomes;
import vesper.pw.world.PaleWorldPlacedFeatures;

public class RockGen {
    public static void rockGenerator(){
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PALE_GARDEN), GenerationStep.Feature.SURFACE_STRUCTURES, PaleWorldPlacedFeatures.PALE_SPIKE);
            // TODO: Make small pale spikes
            // BiomeModifications.addFeature(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_VALLEY), GenerationStep.Feature.SURFACE_STRUCTURES, PaleWorldPlacedFeatures.PALE_SPIKE);
    }
}
