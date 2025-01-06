package vesper.pw.biomes;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import vesper.pw.PaleWorld;
import vesper.pw.world.PaleWorldPlacedFeatures;


public class PaleWorldBiomes {
    public static final RegistryKey<Biome> PALE_CAVE = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(PaleWorld.MOD_ID, "pale_cave"));
    public static final RegistryKey<Biome> PALE_VALLEY = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(PaleWorld.MOD_ID, "pale_valley"));

    public static void bootstrap(Registerable<Biome> biomeRegisterable) {
        biomeRegisterable.register(PALE_CAVE, paleCave(biomeRegisterable));
        biomeRegisterable.register(PALE_VALLEY, paleValley(biomeRegisterable));
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }

    private static Biome paleCave(Registerable<Biome> biomeRegisterable) {
        SpawnSettings.Builder spawner = new SpawnSettings.Builder();
        spawner.spawn(SpawnGroup.AXOLOTLS, new SpawnSettings.SpawnEntry(EntityType.AXOLOTL, 1, 1, 1));

        DefaultBiomeFeatures.addBatsAndMonsters(spawner);

        GenerationSettings.LookupBackedBuilder builder = new GenerationSettings.LookupBackedBuilder(biomeRegisterable.getRegistryLookup(RegistryKeys.PLACED_FEATURE), biomeRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));
        globalOverworldGeneration(builder);
        DefaultBiomeFeatures.addPlainsTallGrass(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addClayOre(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        DefaultBiomeFeatures.addInfestedStone(builder);
       // builder.feature((GenerationStep.Feature.UNDERGROUND_DECORATION), UndergroundPlacedFeatures.LUSH_CAVES_CEILING_VEGETATION);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, UndergroundPlacedFeatures.CAVE_VINES);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, UndergroundPlacedFeatures.LUSH_CAVES_CLAY);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PaleWorldPlacedFeatures.PALE_CAVE_VEG);
       builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PALE_MOSS_PATCH);


        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(builder.build())
                .spawnSettings(spawner.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xff76889D)
                        .waterFogColor(0xff556980)
                        .skyColor(0xffb9b9b9)
                        .grassColor(0xff778272)
                        .foliageColor(0xff878D76)
                        .fogColor(0xff817770)
                        .moodSound(BiomeMoodSound.CAVE).build())
            .build();


    }

    public static Biome paleValley(Registerable<Biome> biomeRegisterable){

        SpawnSettings.Builder spawner = new SpawnSettings.Builder();
        GenerationSettings.LookupBackedBuilder builder = new GenerationSettings.LookupBackedBuilder(biomeRegisterable.getRegistryLookup(RegistryKeys.PLACED_FEATURE), biomeRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        DefaultBiomeFeatures.addBatsAndMonsters(spawner);
        DefaultBiomeFeatures.addFarmAnimals(spawner);
       // builder.feature(GenerationStep.Feature.SURFACE_STRUCTURES, VillagePlacedFeatures.SPRUCE);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(1)
                .temperature(1)
                .generationSettings(builder.build())
                .spawnSettings(spawner.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xff76889D)
                        .waterFogColor(0xff556980)
                        .skyColor(0xffb9b9b9)
                        .grassColor(0xff778272)
                        .foliageColor(0xff878D76)
                        .fogColor(0xff817770).build()
                )
                .build();
    }
}
