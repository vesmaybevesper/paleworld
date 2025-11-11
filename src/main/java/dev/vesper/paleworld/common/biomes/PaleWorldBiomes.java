package dev.vesper.paleworld.common.biomes;

import dev.vesper.paleworld.PaleWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class PaleWorldBiomes {
    public static final ResourceKey<Biome> PALE_CAVE = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, "pale_caves"));

    public static void bootstrap(BootstrapContext<Biome> biomeBootstrapContext){
        biomeBootstrapContext.register(PALE_CAVE, paleCave(biomeBootstrapContext));
    }

    public static Biome paleCave(BootstrapContext<Biome> biomeBootstrapContext){
        MobSpawnSettings.Builder spawner = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawner);
        BiomeDefaultFeatures.oceanSpawns(spawner, 1, 1, 3);

        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(biomeBootstrapContext.lookup(Registries.PLACED_FEATURE), biomeBootstrapContext.lookup(Registries.CONFIGURED_CARVER));
        builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, PaleWorldPlacedFeatures.PALE_GEODE);
        // add global gen here
        builder.addCarver(Carvers.CAVE);
        builder.addCarver(Carvers.CAVE_EXTRA_UNDERGROUND);
        builder.addCarver(Carvers.CANYON);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addLushCavesSpecialOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder); // make sure this is right
        BiomeDefaultFeatures.addInfestedStone(builder);
        builder.addFeature();
        builder.addFeature();
        builder.addFeature();
        builder.addFeature();
        builder.addFeature();

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(builder.build())
                .mobSpawnSettings(spawner.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xff76889D)
                        .waterFogColor(0xff556980)
                        .skyColor(0xffb9b9b9)
                        .grassColorOverride(0xff778272)
                        .foliageColorOverride(0xff878D76)
                        .fogColor(0xff817770)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientParticle(config) // remember to create this after remaking particle
                        .build())
                .build();
    }
}
