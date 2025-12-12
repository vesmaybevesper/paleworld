package dev.vesper.paleworld.common.world;

import com.google.common.collect.ImmutableList;
import dev.vesper.paleworld.PaleWorld;
import dev.vesper.paleworld.common.blocks.PaleWorldBlocks;
import dev.vesper.paleworld.common.blocks.custom.PaleVineBodyBlock;
import dev.vesper.paleworld.common.blocks.custom.PaleVines;
import dev.vesper.paleworld.common.blocks.custom.SmallDyingDripleafBlock;
import dev.vesper.paleworld.common.world.gen.feature.BlankLeaves;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FallenTreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomBooleanFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.CreakingHeartDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.PaleMossDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.OptionalInt;

public class PaleWorldConfiguredFeatures {
	public static final ResourceKey<ConfiguredFeature<?,?>> PALE_CAVE_PATCH = resourceKey("pale_cave_patch");
	public static final ResourceKey<ConfiguredFeature<?,?>> PALE_CAVE_VEG = resourceKey("pale_cave_vegetation");
	public static final ResourceKey<ConfiguredFeature<?,?>> PALE_CAVE_CEILING = resourceKey("pale_vine_ceiling");
	public static final ResourceKey<ConfiguredFeature<?,?>> PALE_VINE_IN_MOSS = resourceKey("pale_vine_in_moss");
	public static final ResourceKey<ConfiguredFeature<?,?>> HANGING_MOSS_IN_PALE = resourceKey("hanging_moss_in_moss");
	public static final ResourceKey<ConfiguredFeature<?,?>> CEILING_HANGING_MOSS = resourceKey("ceiling_in_moss");
	public static final ResourceKey<ConfiguredFeature<?,?>> PALE_CAVES_CLAY = resourceKey("pale_caves_clay");
	public static final ResourceKey<ConfiguredFeature<?,?>> CLAY_DRIPLEAF = resourceKey("clay_dripleaf");
	public static final ResourceKey<ConfiguredFeature<?,?>> CLAY_POOL_DRIPLEAF = resourceKey("clay_pool_dripleaf");
	public static final ResourceKey<ConfiguredFeature<?,?>> PALE_VINE = resourceKey("pale_vine_feature");
	public static final ResourceKey<ConfiguredFeature<?,?>> DYING_DRIPLEAF = resourceKey("dying_dripleaves");
	public static final ResourceKey<ConfiguredFeature<?,?>> PALE_CAVE_GEODE = resourceKey("pale_cave_dripleaf");
	public static final ResourceKey<ConfiguredFeature<?,?>> PALE_CAVE_CEILING_VEG_MIXED = resourceKey("pale_cave_ceiling_veg_mixed");
	public static final ResourceKey<ConfiguredFeature<?,?>> STRIPPED_PALE_OAK = resourceKey("stripped_pale_oak");
	public static final ResourceKey<ConfiguredFeature<?,?>> TALL_STRIPPED_PALE_OAK = resourceKey("tall_stripped_pale_oak");
	public static final ResourceKey<ConfiguredFeature<?,?>> TALL_PALE_OAK = resourceKey("tall_pale_oak");
	public static final ResourceKey<ConfiguredFeature<?,?>> SMALL_PALE_OAK = resourceKey("small_pale_oak");
	public static final ResourceKey<ConfiguredFeature<?,?>> STRIPPED_SMALL_PALE_OAK = resourceKey("stripped_small_pale_oak");
	public static final ResourceKey<ConfiguredFeature<?,?>> BARE_SMALL_PALE_OAK = resourceKey("bare_small_pale_oak");
	public static final ResourceKey<ConfiguredFeature<?,?>> BARE_STRIPPED_SMALL_PALE_OAK = resourceKey("bare_stripped_small_pale_oak");
	public static final ResourceKey<ConfiguredFeature<?,?>> BARE_PALE_OAK = resourceKey("bare_pale_oak");
	public static final ResourceKey<ConfiguredFeature<?,?>> STRIPPED_BARE_PALE_OAK = resourceKey("stripped_bare_pale_oak");
	public static final ResourceKey<ConfiguredFeature<?,?>> PALE_SPIKE = resourceKey("pale_spike");
	public static final ResourceKey<ConfiguredFeature<?,?>> FALLEN_PALE_OAK = resourceKey("fallen_pale_oak");
	public static final ResourceKey<ConfiguredFeature<?,?>> TALL_PALE_OAK_WITH_HEART = resourceKey("tall_pale_oak_with_heart");
	public static final ResourceKey<ConfiguredFeature<?,?>> SMALL_PALE_OAK_WITH_HEART = resourceKey("small_pale_oak_with_heart");
	public static final ResourceKey<ConfiguredFeature<?,?>> BARE_SMALL_PALE_OAK_WITH_HEART = resourceKey("bare_small_pale_oak_with_heart");
	public static final ResourceKey<ConfiguredFeature<?,?>> BARE_PALE_OAK_WITH_HEART = resourceKey("bare_pale_oak_with_heart");
	public static final ResourceKey<ConfiguredFeature<?,?>> CHRYSANTHEMUM = resourceKey("chrysanthemum");
	public static final ResourceKey<ConfiguredFeature<?,?>> ASPHODEL = resourceKey("asphodel");

	private static Holder<PlacedFeature> createSmallDyingDripleaf(){
		return PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(WeightedList.<BlockState>builder()
						.add((BlockState) PaleWorldBlocks.SMALL_DYING_DRIPLEAF.defaultBlockState().setValue(SmallDyingDripleafBlock.FACING, Direction.EAST), 1)
						.add((BlockState) PaleWorldBlocks.SMALL_DYING_DRIPLEAF.defaultBlockState().setValue(SmallDyingDripleafBlock.FACING, Direction.WEST), 1)
						.add((BlockState) PaleWorldBlocks.SMALL_DYING_DRIPLEAF.defaultBlockState().setValue(SmallDyingDripleafBlock.FACING, Direction.NORTH), 1)
						.add((BlockState) PaleWorldBlocks.SMALL_DYING_DRIPLEAF.defaultBlockState().setValue(SmallDyingDripleafBlock.FACING, Direction.SOUTH), 1))),
				new PlacementModifier[0]
		);
	}

	private static Holder<PlacedFeature> createBigDyingDripleaf(Direction direction) {
		return PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(new WeightedListInt(WeightedList.<IntProvider>builder()
										.add(UniformInt.of(0, 4), 2)
										.add(ConstantInt.of(0), 1).build()),
								BlockStateProvider.simple((BlockState)PaleWorldBlocks.BIG_DYING_DRIPLEAF_STEM.defaultBlockState()
										.setValue(BlockStateProperties.HORIZONTAL_FACING, direction))),
						BlockColumnConfiguration.layer(ConstantInt.of(1),
								BlockStateProvider.simple((BlockState)PaleWorldBlocks.BIG_DYING_DRIPLEAF.defaultBlockState()
										.setValue(BlockStateProperties.HORIZONTAL_FACING, direction)))),
						Direction.UP, BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, true),
				new PlacementModifier[0]);
	}

	private static FallenTreeConfiguration.FallenTreeConfigurationBuilder fallenPaleOak() {
		return fallen(Blocks.PALE_OAK_LOG, 4, 7).stumpDecorators(ImmutableList.of(TrunkVineDecorator.INSTANCE));
	}

	private static FallenTreeConfiguration.FallenTreeConfigurationBuilder fallen(Block log, int minLength, int maxLength) {
		return (new FallenTreeConfiguration.FallenTreeConfigurationBuilder(BlockStateProvider.simple(log), UniformInt.of(minLength, maxLength))).logDecorators(ImmutableList.of());
	}

	private static RandomPatchConfiguration createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
		return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.inlinePlaced((Feature)Feature.SIMPLE_BLOCK, (FeatureConfiguration) (new SimpleBlockConfiguration(block))));
	}

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> configuredFeatureRegisterable) {
		HolderGetter<ConfiguredFeature<?, ?>> registryEntryLookup = configuredFeatureRegisterable.lookup(Registries.CONFIGURED_FEATURE);
		HolderGetter<StructureProcessorList> registryEntryLookup2 = configuredFeatureRegisterable.lookup(Registries.PROCESSOR_LIST);

		// Underground / Pale Cave features
		WeightedStateProvider weightedBlockStateProvider = new WeightedStateProvider(WeightedList.<BlockState>builder().add(PaleWorldBlocks.PALE_VINE_BODY.defaultBlockState(), 4).add((BlockState)PaleWorldBlocks.PALE_VINE_BODY.defaultBlockState().setValue(PaleVines.BERRIES, Boolean.TRUE), 1));
		WeightedStateProvider hangingWeighted = new WeightedStateProvider(WeightedList.<BlockState>builder().add(Blocks.PALE_HANGING_MOSS.defaultBlockState(), 2).add((BlockState)Blocks.PALE_HANGING_MOSS.defaultBlockState(), 1));

		RandomizedIntStateProvider randomizedIntBlockStateProvider = new RandomizedIntStateProvider(new WeightedStateProvider(WeightedList.<BlockState>builder()
				.add(PaleWorldBlocks.PALE_VINE.defaultBlockState(), 4)
				.add((BlockState)PaleWorldBlocks.PALE_VINE.defaultBlockState().setValue(PaleVines.BERRIES, Boolean.TRUE), 1)), String.valueOf(PaleVineBodyBlock.AGE), UniformInt.of(20, 23));

		RandomizedIntStateProvider hangingRandomized = new RandomizedIntStateProvider(new WeightedStateProvider(WeightedList.<BlockState>builder()
				.add(Blocks.PALE_HANGING_MOSS.defaultBlockState(), 1)
				.add((BlockState)Blocks.PALE_HANGING_MOSS.defaultBlockState(), 1)), String.valueOf(PaleVineBodyBlock.AGE), UniformInt.of(20, 23));

		register(configuredFeatureRegisterable,
				DYING_DRIPLEAF,
				Feature.SIMPLE_RANDOM_SELECTOR,
				new SimpleRandomFeatureConfiguration(
						HolderSet.direct(createSmallDyingDripleaf(),
								createSmallDyingDripleaf(),
								createBigDyingDripleaf(Direction.EAST),
								createBigDyingDripleaf(Direction.WEST),
								createBigDyingDripleaf(Direction.SOUTH),
								createBigDyingDripleaf(Direction.NORTH))
				)
		);

		register(configuredFeatureRegisterable, PALE_CAVE_VEG, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
				WeightedList.<BlockState>builder()
						.add(Blocks.DEAD_BUSH.defaultBlockState(),5)
						.add(PaleWorldBlocks.DYING_AZALEA.defaultBlockState(),2)
						.add(Blocks.PALE_MOSS_CARPET.defaultBlockState(), 25)
						.add(Blocks.SHORT_GRASS.defaultBlockState(),15)
						.add(Blocks.SHORT_DRY_GRASS.defaultBlockState(),30)
						.add(Blocks.TALL_DRY_GRASS.defaultBlockState(),7))));

		register(configuredFeatureRegisterable, PALE_CAVE_CEILING_VEG_MIXED, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
				WeightedList.<BlockState>builder()
						.add(Blocks.PALE_HANGING_MOSS.defaultBlockState(),7)
						.add(PaleWorldBlocks.PALE_VINE_BODY.defaultBlockState(), 7)
						.add(Blocks.AIR.defaultBlockState(), 7))));


		register(configuredFeatureRegisterable,
				PALE_CAVE_PATCH,
				Feature.VEGETATION_PATCH,
				new VegetationPatchConfiguration(
						BlockTags.MOSS_REPLACEABLE,
						BlockStateProvider.simple(Blocks.PALE_MOSS_BLOCK),
						PlacementUtils.inlinePlaced(registryEntryLookup.getOrThrow(PALE_CAVE_VEG)),
						CaveSurface.FLOOR,
						ConstantInt.of(1),
						0.0f,
						5,
						0.8f,
						UniformInt.of(4,7),
						0.3f
				));

		register(configuredFeatureRegisterable,
				PALE_VINE,
				Feature.BLOCK_COLUMN,
				new BlockColumnConfiguration(
						List.of(
								BlockColumnConfiguration.layer(
										new WeightedListInt(
												WeightedList.<IntProvider>builder()
														.add(UniformInt.of(0,19), 2)
														.add(UniformInt.of(0,2),3)
														.add(UniformInt.of(0,6),10)
														.build()
										),
										weightedBlockStateProvider
								),
								BlockColumnConfiguration.layer(ConstantInt.of(1), randomizedIntBlockStateProvider)
						),
						Direction.UP,
						BlockPredicate.ONLY_IN_AIR_PREDICATE,
						true
				));

		register(configuredFeatureRegisterable,
				PALE_VINE_IN_MOSS,
				Feature.BLOCK_COLUMN,
				new BlockColumnConfiguration(
						List.of(
								BlockColumnConfiguration.layer(
										new WeightedListInt(WeightedList.<IntProvider>builder().add(UniformInt.of(0,3), 2).add(UniformInt.of(1,7),1).add(UniformInt.of(0,1), 10).build()),
										weightedBlockStateProvider
								),
								BlockColumnConfiguration.layer(ConstantInt.of(1), randomizedIntBlockStateProvider)
						),
						Direction.DOWN,
						BlockPredicate.ONLY_IN_AIR_PREDICATE,
						true
				)
		);

		register(
				configuredFeatureRegisterable,
				PALE_CAVE_CEILING,
				Feature.VEGETATION_PATCH,
				new VegetationPatchConfiguration(
						BlockTags.MOSS_REPLACEABLE,
						BlockStateProvider.simple(Blocks.PALE_MOSS_BLOCK),
						PlacementUtils.inlinePlaced(registryEntryLookup.getOrThrow(PALE_VINE_IN_MOSS)),
						CaveSurface.CEILING,
						UniformInt.of(1,2),
						0.0f,
						5,
						0.5f,
						UniformInt.of(4,6),
						0.3f
				));

		register(configuredFeatureRegisterable, CEILING_HANGING_MOSS,
				Feature.SIMPLE_BLOCK,
				new SimpleBlockConfiguration(new WeightedStateProvider(
						WeightedList.<BlockState>builder()
								.add(Blocks.PALE_HANGING_MOSS.defaultBlockState(), 5)
								.add(Blocks.AIR.defaultBlockState(), 7)
				)));

		register(configuredFeatureRegisterable,
				HANGING_MOSS_IN_PALE,
				Feature.VEGETATION_PATCH,
				new VegetationPatchConfiguration(
						BlockTags.MOSS_REPLACEABLE,
						BlockStateProvider.simple(Blocks.PALE_MOSS_BLOCK),
						PlacementUtils.inlinePlaced(registryEntryLookup.getOrThrow(CEILING_HANGING_MOSS)),
						CaveSurface.CEILING,
						UniformInt.of(1,3),
						0,
						1,
						0.8F,
						UniformInt.of(4,7),
						0.3F
				));

		register(
				configuredFeatureRegisterable,
				CLAY_DRIPLEAF,
				Feature.VEGETATION_PATCH,
				new VegetationPatchConfiguration(
						BlockTags.LUSH_GROUND_REPLACEABLE,
						BlockStateProvider.simple(Blocks.CLAY),
						PlacementUtils.inlinePlaced(registryEntryLookup.getOrThrow(DYING_DRIPLEAF)),
						CaveSurface.FLOOR,
						ConstantInt.of(3),
						0.8F,
						2,
						0.5F,
						UniformInt.of(4, 7),
						0.7F
				));

		register(
				configuredFeatureRegisterable,
				CLAY_POOL_DRIPLEAF,
				Feature.WATERLOGGED_VEGETATION_PATCH,
				new VegetationPatchConfiguration(
						BlockTags.LUSH_GROUND_REPLACEABLE,
						BlockStateProvider.simple(Blocks.CLAY),
						PlacementUtils.inlinePlaced(registryEntryLookup.getOrThrow(DYING_DRIPLEAF)),
						CaveSurface.FLOOR,
						ConstantInt.of(3),
						0.8F,
						5,
						0.1F,
						UniformInt.of(4, 7),
						0.7F
				)
		);

		register(
				configuredFeatureRegisterable,
				PALE_CAVES_CLAY,
				Feature.RANDOM_BOOLEAN_SELECTOR,
				new RandomBooleanFeatureConfiguration(
						PlacementUtils.inlinePlaced(registryEntryLookup.getOrThrow(CLAY_DRIPLEAF)),
						PlacementUtils.inlinePlaced(registryEntryLookup.getOrThrow(CLAY_POOL_DRIPLEAF))
				)
		);

		register(
				configuredFeatureRegisterable,
				PALE_CAVE_GEODE,
				Feature.GEODE,
				new GeodeConfiguration(
						new GeodeBlockSettings(
								BlockStateProvider.simple(Blocks.AIR),
								BlockStateProvider.simple(Blocks.AMETHYST_BLOCK),
								BlockStateProvider.simple(Blocks.BUDDING_AMETHYST),
								BlockStateProvider.simple(PaleWorldBlocks.WHITE_CRYSTAL),
								BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
								List.of(
										Blocks.SMALL_AMETHYST_BUD.defaultBlockState(),
										Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(),
										Blocks.LARGE_AMETHYST_BUD.defaultBlockState(),
										Blocks.AMETHYST_CLUSTER.defaultBlockState()
								),
								BlockTags.FEATURES_CANNOT_REPLACE,
								BlockTags.GEODE_INVALID_BLOCKS
						),
						new GeodeLayerSettings(1.7, 2.2, 3.2, 4.2),
						new GeodeCrackSettings(0.95, 2.0, 2),
						0.35,
						0.083,
						true,
						UniformInt.of(4, 6),
						UniformInt.of(3, 4),
						UniformInt.of(1, 2),
						-16,
						16,
						0.05,
						1
				)
		);

		// Pale Garden Features

		register(configuredFeatureRegisterable,
				STRIPPED_PALE_OAK,
				Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.STRIPPED_PALE_OAK_LOG), new DarkOakTrunkPlacer(6,2,1),
						BlockStateProvider.simple(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
						.decorators(ImmutableList.of(new PaleMossDecorator(0.25F, 0.4F, 0.8F)))
						.ignoreVines()
						.build());

		register(configuredFeatureRegisterable,
				TALL_STRIPPED_PALE_OAK,
				Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.STRIPPED_PALE_OAK_LOG), new DarkOakTrunkPlacer(10,4,2),
						BlockStateProvider.simple(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
						.decorators(ImmutableList.of(new PaleMossDecorator(0.25F, 0.4F, 0.8F)))
						.ignoreVines()
						.build());

		register(configuredFeatureRegisterable,
				TALL_PALE_OAK,
				Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.PALE_OAK_LOG), new DarkOakTrunkPlacer(10,4,2),
						BlockStateProvider.simple(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
						.decorators(ImmutableList.of(new PaleMossDecorator(0.25F, 0.4F, 0.8F)))
						.ignoreVines()
						.build());


		register(configuredFeatureRegisterable,
				BARE_PALE_OAK,
				Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.PALE_OAK_LOG), new DarkOakTrunkPlacer(6,2,1),
						BlockStateProvider.simple(Blocks.AIR), new BlankLeaves(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
						.decorators(ImmutableList.of(new PaleMossDecorator(0.00F, 0.4F, 0.8F)))
						.ignoreVines()
						.build());

		register(configuredFeatureRegisterable,
				STRIPPED_BARE_PALE_OAK,
				Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.STRIPPED_PALE_OAK_LOG), new DarkOakTrunkPlacer(6,2,1),
						BlockStateProvider.simple(Blocks.AIR), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
						.decorators(ImmutableList.of(new PaleMossDecorator(0.00F, 0.4F, 0.8F)))
						.ignoreVines()
						.build());

		register(configuredFeatureRegisterable,
				SMALL_PALE_OAK,
				Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.PALE_OAK_LOG), new ForkingTrunkPlacer(6,2,1),
						BlockStateProvider.simple(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
						.decorators(ImmutableList.of(new PaleMossDecorator(0.25F, 0.4F, 0.8F)))
						.ignoreVines()
						.build());

		register(configuredFeatureRegisterable,
				STRIPPED_SMALL_PALE_OAK,
				Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.STRIPPED_PALE_OAK_LOG), new ForkingTrunkPlacer(6,2,1),
						BlockStateProvider.simple(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
						.decorators(ImmutableList.of(new PaleMossDecorator(0.25F, 0.4F, 0.8F)))
						.ignoreVines()
						.build());

		register(configuredFeatureRegisterable,
				BARE_SMALL_PALE_OAK,
				Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.PALE_OAK_LOG), new ForkingTrunkPlacer(6,2,1),
						BlockStateProvider.simple(Blocks.AIR), new BlankLeaves(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
						.decorators(ImmutableList.of(new PaleMossDecorator(0.25F, 0.4F, 0.8F)))
						.ignoreVines()
						.build());

		register(configuredFeatureRegisterable,
				BARE_STRIPPED_SMALL_PALE_OAK,
				Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.STRIPPED_PALE_OAK_LOG), new ForkingTrunkPlacer(6,2,1),
						BlockStateProvider.simple(Blocks.AIR), new BlankLeaves(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
						.decorators(ImmutableList.of(new PaleMossDecorator(0.0F, 0.4F, 0.8F)))
						.ignoreVines()
						.build());

		// Pale Oaks with creaking hearts

		register(configuredFeatureRegisterable,
				TALL_PALE_OAK_WITH_HEART,
				Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.PALE_OAK_LOG), new DarkOakTrunkPlacer(10,4,2),
						BlockStateProvider.simple(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
						.decorators(ImmutableList.of(new PaleMossDecorator(0.25F, 0.4F, 0.8F), new CreakingHeartDecorator(1.0f)))
						.ignoreVines()
						.build());


		register(configuredFeatureRegisterable,
				BARE_PALE_OAK_WITH_HEART,
				Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.PALE_OAK_LOG), new DarkOakTrunkPlacer(6,2,1),
						BlockStateProvider.simple(Blocks.AIR), new BlankLeaves(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
						.decorators(ImmutableList.of(new PaleMossDecorator(0.00F, 0.4F, 0.8F), new CreakingHeartDecorator(1.0f)))
						.ignoreVines()
						.build());


		register(configuredFeatureRegisterable,
				SMALL_PALE_OAK_WITH_HEART,
				Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.PALE_OAK_LOG), new ForkingTrunkPlacer(6,2,1),
						BlockStateProvider.simple(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
						.decorators(ImmutableList.of(new PaleMossDecorator(0.25F, 0.4F, 0.8F), new CreakingHeartDecorator(1.0f)))
						.ignoreVines()
						.build());


		register(configuredFeatureRegisterable,
				BARE_SMALL_PALE_OAK_WITH_HEART,
				Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.PALE_OAK_LOG), new ForkingTrunkPlacer(6,2,1),
						BlockStateProvider.simple(Blocks.AIR), new BlankLeaves(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
						.decorators(ImmutableList.of(new PaleMossDecorator(0.25F, 0.4F, 0.8F), new CreakingHeartDecorator(1.0f)))
						.ignoreVines()
						.build());

		register(configuredFeatureRegisterable, PALE_SPIKE, PaleWorld.PALE_SPIKE);

		register(configuredFeatureRegisterable, FALLEN_PALE_OAK, Feature.FALLEN_TREE, fallenPaleOak().build());

		register(configuredFeatureRegisterable,
				CHRYSANTHEMUM,
				Feature.FLOWER,
				new RandomPatchConfiguration(50, 4, 2, PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK,
						(new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters
								(0, 1.0, new double[0]), 0.020833334F,
								List.of(PaleWorldBlocks.CHRYSANTHEMUM.defaultBlockState())))))));

		register(configuredFeatureRegisterable,
				ASPHODEL,
				Feature.FLOWER,
				new RandomPatchConfiguration(40, 3, 1, PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK,
						(new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters
								(0, 1.0, new double[0]), 0.020833334F,
								List.of(PaleWorldBlocks.ASPHODEL.defaultBlockState())))))));
	}

	public static void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Feature<NoneFeatureConfiguration> feature){
		register(context, key, feature, FeatureConfiguration.NONE);
	}

	private static<FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?,?>> key, F feature, FC config){
		context.register(key, new ConfiguredFeature<>(feature, config));
	}

	private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String name, F feature){
		return (F)(Registry.register(BuiltInRegistries.FEATURE, name, feature));
	}

	public static ResourceKey<ConfiguredFeature<?, ?>> resourceKey(String name){
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, name));
	}
}
