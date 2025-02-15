package vesper.pw.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import vesper.pw.PaleWorld;
import vesper.pw.PaleWorldConfig;

import java.util.List;



public class PaleWorldPlacedFeatures {

    public static final RegistryKey<PlacedFeature> PALE_CAVE_VEG = registryKey("pale_cave_veg");
    public static final RegistryKey<PlacedFeature> PALE_CAVE_PATCH = registryKey("pale_cave_patch");
    public static final RegistryKey<PlacedFeature> PALE_CAVE_CEILING_PATCH = registryKey("pale_cave_ceiling_patch");
    public static final RegistryKey<PlacedFeature> PALE_CAVE_CEILING_PATCH_MIXED = registryKey("pale_cave_ceiling_patch_mixed");
    public static final RegistryKey<PlacedFeature> HANGING_MOSS_CEILING = registryKey("hanging_moss_ceiling");
    public static final RegistryKey<PlacedFeature> CLAY_WITH_DYING_DRIPLEAF = registryKey("clay_with_dying_dripleaf");
    public static final RegistryKey<PlacedFeature> CLAY_POOL_WITH_DYING_DRIPLEAF = registryKey("clay_pool_with_dying_dripleaf");
    public static final RegistryKey<PlacedFeature> PALE_CAVE_CLAY = registryKey("pale_cave_clay");
    public static final RegistryKey<PlacedFeature> PALE_VINE = registryKey("pale_vine_feature");
    public static final RegistryKey<PlacedFeature> PALE_GEODE = registryKey("pale_geode");
    public static final RegistryKey<PlacedFeature> STRIPPED_PALE_OAK = registryKey("stripped_pale_oak");


    public static void bootstrap(Registerable<PlacedFeature> registerable){
        var configuredFeatures = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);


        // Pale CAve Features

        register(
                registerable,
                PALE_VINE,
                configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_VINE),
                CountPlacementModifier.of(200),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
                BiomePlacementModifier.of()
        );

        register(
                registerable,
                PALE_CAVE_VEG,
                configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_CAVE_VEG),
                CountPlacementModifier.of(100),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
                BiomePlacementModifier.of()
        );


        register(
                registerable,
                PALE_CAVE_PATCH,
                configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_CAVE_PATCH),
                CountPlacementModifier.of(125),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
                BiomePlacementModifier.of()
        );

        register(
                registerable,
                PALE_CAVE_CEILING_PATCH,
                configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_CAVE_CEILING),
                CountPlacementModifier.of(50),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 10),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
                BiomePlacementModifier.of()
        );

        register(
                registerable,
                PALE_CAVE_CEILING_PATCH_MIXED,
                configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_CAVE_CEILING_VEG_MIXED),
                CountPlacementModifier.of(25),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 10),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
                BiomePlacementModifier.of()
        );

        register(
                registerable,
                CLAY_WITH_DYING_DRIPLEAF,
                configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.CLAY_DRIPLEAF),
                CountPlacementModifier.of(62),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
                BiomePlacementModifier.of()
        );
        register(
                registerable,
                CLAY_POOL_WITH_DYING_DRIPLEAF,
                configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.CLAY_POOL_DRIPLEAF),
                CountPlacementModifier.of(62),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
                BiomePlacementModifier.of()
        );

        register(
                registerable,
                PALE_CAVE_CLAY,
                configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_CAVES_CLAY),
                CountPlacementModifier.of(62),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
                BiomePlacementModifier.of()
        );

        register(registerable,
                HANGING_MOSS_CEILING,
                configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.HANGING_MOSS_IN_PALE),
                CountPlacementModifier.of(75),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR ,12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
                BiomePlacementModifier.of());

        register(
                registerable,
                PALE_GEODE,
                configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_CAVE_GEODE),
                RarityFilterPlacementModifier.of(24),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6), YOffset.fixed(30)),
                BiomePlacementModifier.of()
        );

        // Pale Garden Features

        register(registerable,
               STRIPPED_PALE_OAK,
                configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.STRIPPED_PALE_OAK),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(PaleWorldConfig.strippedOakCount, PaleWorldConfig.strippedOakExtraChance, PaleWorldConfig.strippedOakExtraCount), Blocks.PALE_OAK_SAPLING));
    }




    public static RegistryKey<PlacedFeature> registryKey(String name){
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(PaleWorld.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?,?>> configuredFeatureRegistryEntry, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuredFeatureRegistryEntry, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?,?>> configuredFeatureRegistryEntry, PlacementModifier... modifiers){
            register(context, key, configuredFeatureRegistryEntry, List.of(modifiers));
    }
}
