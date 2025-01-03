package vesper.pw.utils;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.UndergroundPlacedFeatures;

public class BiomeQuickAdds {

    public static void addPaleVines(GenerationSettings.LookupBackedBuilder builder){
        builder.feature((GenerationStep.Feature.UNDERGROUND_DECORATION), UndergroundPlacedFeatures.LUSH_CAVES_CEILING_VEGETATION);
    }


}
