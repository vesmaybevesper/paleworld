package dev.vesper.paleworld.common.world;

import dev.vesper.paleworld.PaleWorld;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class PaleWorldPlacedFeatures {
	public static final ResourceKey<PlacedFeature> PALE_CAVE_VEG = registryKey("pale_cave_veg");
	public static final ResourceKey<PlacedFeature> PALE_CAVE_PATCH = registryKey("pale_cave_patch");
	public static final ResourceKey<PlacedFeature> PALE_CAVE_CEILING_PATCH = registryKey("pale_cave_ceiling_patch");
	public static final ResourceKey<PlacedFeature> PALE_CAVE_CEILING_PATCH_MIXED = registryKey("pale_cave_ceiling_patch_mixed");
	public static final ResourceKey<PlacedFeature> HANGING_MOSS_CEILING = registryKey("hanging_moss_ceiling");
	public static final ResourceKey<PlacedFeature> CLAY_WITH_DYING_DRIPLEAF = registryKey("clay_with_dying_dripleaf");
	public static final ResourceKey<PlacedFeature> CLAY_POOL_WITH_DYING_DRIPLEAF = registryKey("clay_pool_with_dying_dripleaf");
	public static final ResourceKey<PlacedFeature> PALE_CAVE_CLAY = registryKey("pale_cave_clay");
	public static final ResourceKey<PlacedFeature> PALE_VINE = registryKey("pale_vine_feature");
	public static final ResourceKey<PlacedFeature> PALE_GEODE = registryKey("pale_geode");
	public static final ResourceKey<PlacedFeature> STRIPPED_PALE_OAK = registryKey("stripped_pale_oak");
	public static final ResourceKey<PlacedFeature> TALL_STRIPPED_PALE_OAK = registryKey("tall_stripped_pale_oak");
	public static final ResourceKey<PlacedFeature> TALL_PALE_OAK = registryKey("tall_pale_oak");
	public static final ResourceKey<PlacedFeature> TALL_PALE_OAK_WITH_HEART = registryKey("tall_pale_oak_with_heart");
	public static final ResourceKey<PlacedFeature> SMALL_PALE_OAK = registryKey("small_pale_oak");
	public static final ResourceKey<PlacedFeature> SMALL_PALE_OAK_WITH_HEART = registryKey("small_pale_oak_with_heart");
	public static final ResourceKey<PlacedFeature> STRIPPED_SMALL_PALE_OAK = registryKey("stripped_small_pale_oak");
	public static final ResourceKey<PlacedFeature> BARE_SMALL_PALE_OAK = registryKey("bare_small_pale_oak");
	public static final ResourceKey<PlacedFeature> BARE_SMALL_PALE_OAK_WITH_HEART = registryKey("bare_small_pale_oak_with_heart");
	public static final ResourceKey<PlacedFeature> BARE_STRIPPED_SMALL_PALE_OAK = registryKey("bare_stripped_small_pale_oak");
	public static final ResourceKey<PlacedFeature> BARE_PALE_OAK = registryKey("bare_pale_oak");
	public static final ResourceKey<PlacedFeature> BARE_PALE_OAK_WITH_HEART = registryKey("bare_pale_oak_with_heart");
	public static final ResourceKey<PlacedFeature> STRIPPED_BARE_PALE_OAK = registryKey("stripped_bare_pale_oak");
	public static final ResourceKey<PlacedFeature> PALE_SPIKE = registryKey("pale_spike");
	public static final ResourceKey<PlacedFeature> CHRYSANTHEMUM = registryKey("chrysanthemum_patch");
	public static final ResourceKey<PlacedFeature> ASPHODEL = registryKey("asphodel_patch");


	public static void bootstrap(BootstrapContext<PlacedFeature> registerable){
		var configuredFeatures = registerable.lookup(Registries.CONFIGURED_FEATURE);


		// Pale Cave Features

		register(
				registerable,
				PALE_VINE,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_VINE),
				CountPlacement.of(200),
				InSquarePlacement.spread(),
				PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
				EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
				RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
				BiomeFilter.biome()
		);

		register(
				registerable,
				PALE_CAVE_VEG,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_CAVE_VEG),
				CountPlacement.of(100),
				InSquarePlacement.spread(),
				PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
				EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
				RandomOffsetPlacement.vertical(ConstantInt.of(1)),
				BiomeFilter.biome()
		);


		register(
				registerable,
				PALE_CAVE_PATCH,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_CAVE_PATCH),
				CountPlacement.of(125),
				InSquarePlacement.spread(),
				PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
				EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
				RandomOffsetPlacement.vertical(ConstantInt.of(1)),
				BiomeFilter.biome()
		);

		register(
				registerable,
				PALE_CAVE_CEILING_PATCH,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_CAVE_CEILING),
				CountPlacement.of(50),
				InSquarePlacement.spread(),
				PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
				EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 10),
				RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
				BiomeFilter.biome()
		);

		register(
				registerable,
				PALE_CAVE_CEILING_PATCH_MIXED,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_CAVE_CEILING_VEG_MIXED),
				CountPlacement.of(25),
				InSquarePlacement.spread(),
				PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
				EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 10),
				RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
				BiomeFilter.biome()
		);

		register(
				registerable,
				CLAY_WITH_DYING_DRIPLEAF,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.CLAY_DRIPLEAF),
				CountPlacement.of(62),
				InSquarePlacement.spread(),
				PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
				EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
				RandomOffsetPlacement.vertical(ConstantInt.of(1)),
				BiomeFilter.biome()
		);
		register(
				registerable,
				CLAY_POOL_WITH_DYING_DRIPLEAF,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.CLAY_POOL_DRIPLEAF),
				CountPlacement.of(62),
				InSquarePlacement.spread(),
				PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
				EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
				RandomOffsetPlacement.vertical(ConstantInt.of(1)),
				BiomeFilter.biome()
		);

		register(
				registerable,
				PALE_CAVE_CLAY,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_CAVES_CLAY),
				CountPlacement.of(62),
				InSquarePlacement.spread(),
				PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
				EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
				RandomOffsetPlacement.vertical(ConstantInt.of(1)),
				BiomeFilter.biome()
		);

		register(registerable,
				HANGING_MOSS_CEILING,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.HANGING_MOSS_IN_PALE),
				CountPlacement.of(75),
				InSquarePlacement.spread(),
				PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
				EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE ,12),
				RandomOffsetPlacement.vertical(ConstantInt.of(1)),
				BiomeFilter.biome());

		register(
				registerable,
				PALE_GEODE,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_CAVE_GEODE),
				RarityFilter.onAverageOnceEvery(24),
				InSquarePlacement.spread(),
				HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)),
				BiomeFilter.biome()
		);

		// Pale Garden Features

		register(registerable,
				BARE_PALE_OAK,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.BARE_PALE_OAK),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.005F, 1), Blocks.PALE_OAK_SAPLING));

		register(registerable,
				BARE_PALE_OAK_WITH_HEART,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.BARE_PALE_OAK_WITH_HEART),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.005F, 1), Blocks.PALE_OAK_SAPLING));

		register(registerable,
				STRIPPED_PALE_OAK,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.STRIPPED_PALE_OAK),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.5F, 2), Blocks.PALE_OAK_SAPLING));

		register(registerable,
				TALL_STRIPPED_PALE_OAK,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.TALL_STRIPPED_PALE_OAK),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.5F, 2), Blocks.PALE_OAK_SAPLING));

		register(registerable,
				TALL_PALE_OAK,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.TALL_PALE_OAK),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(4, 0.5F, 2), Blocks.PALE_OAK_SAPLING));

		register(registerable,
				TALL_PALE_OAK_WITH_HEART,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.TALL_PALE_OAK_WITH_HEART),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(4, 0.5F, 2), Blocks.PALE_OAK_SAPLING));

		register(registerable,
				STRIPPED_BARE_PALE_OAK,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.STRIPPED_BARE_PALE_OAK),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.005F, 1), Blocks.PALE_OAK_SAPLING));

		register(registerable,
				SMALL_PALE_OAK,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.SMALL_PALE_OAK),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(4, 0.5F, 2), Blocks.PALE_OAK_SAPLING));

		register(registerable,
				SMALL_PALE_OAK_WITH_HEART,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.SMALL_PALE_OAK_WITH_HEART),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(4, 0.5F, 2), Blocks.PALE_OAK_SAPLING));


		register(registerable,
				STRIPPED_SMALL_PALE_OAK,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.STRIPPED_SMALL_PALE_OAK),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.05F, 2), Blocks.PALE_OAK_SAPLING));

		register(registerable,
				BARE_SMALL_PALE_OAK,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.BARE_SMALL_PALE_OAK),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.005F, 2), Blocks.PALE_OAK_SAPLING));

		register(registerable,
				BARE_SMALL_PALE_OAK_WITH_HEART,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.BARE_SMALL_PALE_OAK_WITH_HEART),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.005F, 2), Blocks.PALE_OAK_SAPLING));

		register(registerable,
				BARE_STRIPPED_SMALL_PALE_OAK,
				configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.BARE_STRIPPED_SMALL_PALE_OAK),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.005F, 2), Blocks.PALE_OAK_SAPLING));

		register(registerable, PALE_SPIKE, configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.PALE_SPIKE), RarityFilter.onAverageOnceEvery(30), CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

		register(registerable, CHRYSANTHEMUM, configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.CHRYSANTHEMUM), RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

		register(registerable, ASPHODEL, configuredFeatures.getOrThrow(PaleWorldConfiguredFeatures.ASPHODEL), RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	}






	public static ResourceKey<PlacedFeature> registryKey(String name){
		return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, name));
	}

	private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?,?>> configuredFeatureRegistryEntry, List<PlacementModifier> modifiers) {
		context.register(key, new PlacedFeature(configuredFeatureRegistryEntry, List.copyOf(modifiers)));
	}

	private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?,?>> configuredFeatureRegistryEntry, PlacementModifier... modifiers){
		register(context, key, configuredFeatureRegistryEntry, List.of(modifiers));
	}

}
