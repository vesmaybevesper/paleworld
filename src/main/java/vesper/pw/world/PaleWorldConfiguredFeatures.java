package vesper.pw.world;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.*;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.foliage.DarkOakFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.NoiseBlockStateProvider;
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.*;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import vesper.pw.PaleWorld;
import vesper.pw.block.PaleWorldBlocks;
import vesper.pw.block.custom.PaleVineBodyBlock;
import net.minecraft.block.BlockState;
import vesper.pw.block.custom.PaleVines;
import vesper.pw.block.custom.SmallDyingDripleafBlock;
import vesper.pw.world.gen.feature.BlankLeaves;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;


public class PaleWorldConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_CAVE_PATCH = registryKey("pale_cave_patch");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_CAVE_VEG = registryKey("pale_cave_vegetation");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_CAVE_CEILING = registryKey("pale_vine_ceiling");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_VINE_IN_MOSS = registryKey("pale_vine_in_moss");
    public static final RegistryKey<ConfiguredFeature<?,?>> HANGING_MOSS_IN_PALE = registryKey("hanging_moss_in_moss");
    public static final RegistryKey<ConfiguredFeature<?,?>> CEILING_HANGING_MOSS = registryKey("ceiling_in_moss");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_CAVES_CLAY = registryKey("pale_caves_clay");
    public static final RegistryKey<ConfiguredFeature<?,?>> CLAY_DRIPLEAF = registryKey("clay_dripleaf");
    public static final RegistryKey<ConfiguredFeature<?,?>> CLAY_POOL_DRIPLEAF = registryKey("clay_pool_dripleaf");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_VINE = registryKey("pale_vine_feature");
    public static final RegistryKey<ConfiguredFeature<?,?>> DYING_DRIPLEAF = registryKey("dying_dripleaves");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_CAVE_GEODE = registryKey("pale_cave_dripleaf");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_CAVE_CEILING_VEG_MIXED = registryKey("pale_cave_ceiling_veg_mixed");
    public static final RegistryKey<ConfiguredFeature<?,?>> STRIPPED_PALE_OAK = registryKey("stripped_pale_oak");
    public static final RegistryKey<ConfiguredFeature<?,?>> TALL_STRIPPED_PALE_OAK = registryKey("tall_stripped_pale_oak");
    public static final RegistryKey<ConfiguredFeature<?,?>> TALL_PALE_OAK = registryKey("tall_pale_oak");
    public static final RegistryKey<ConfiguredFeature<?,?>> SMALL_PALE_OAK = registryKey("small_pale_oak");
    public static final RegistryKey<ConfiguredFeature<?,?>> STRIPPED_SMALL_PALE_OAK = registryKey("stripped_small_pale_oak");
    public static final RegistryKey<ConfiguredFeature<?,?>> BARE_SMALL_PALE_OAK = registryKey("bare_small_pale_oak");
    public static final RegistryKey<ConfiguredFeature<?,?>> BARE_STRIPPED_SMALL_PALE_OAK = registryKey("bare_stripped_small_pale_oak");
    public static final RegistryKey<ConfiguredFeature<?,?>> BARE_PALE_OAK = registryKey("bare_pale_oak");
    public static final RegistryKey<ConfiguredFeature<?,?>> STRIPPED_BARE_PALE_OAK = registryKey("stripped_bare_pale_oak");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_SPIKE = registryKey("pale_spike");
    public static final RegistryKey<ConfiguredFeature<?,?>> FALLEN_PALE_OAK = registryKey("fallen_pale_oak");
    public static final RegistryKey<ConfiguredFeature<?,?>> TALL_PALE_OAK_WITH_HEART = registryKey("tall_pale_oak_with_heart");
    public static final RegistryKey<ConfiguredFeature<?,?>> SMALL_PALE_OAK_WITH_HEART = registryKey("small_pale_oak_with_heart");
    public static final RegistryKey<ConfiguredFeature<?,?>> BARE_SMALL_PALE_OAK_WITH_HEART = registryKey("bare_small_pale_oak_with_heart");
    public static final RegistryKey<ConfiguredFeature<?,?>> BARE_PALE_OAK_WITH_HEART = registryKey("bare_pale_oak_with_heart");
    public static final RegistryKey<ConfiguredFeature<?,?>> CHRYSANTHEMUM = registryKey("chrysanthemum");
    public static final RegistryKey<ConfiguredFeature<?,?>> ASPHODEL = registryKey("asphodel");


    private static RegistryEntry<PlacedFeature> createSmallDyingDripleaf(){
        return PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(Pool.<BlockState>builder()
                        .add((BlockState) PaleWorldBlocks.SMALL_DYING_DRIPLEAF.getDefaultState().with(SmallDyingDripleafBlock.FACING, Direction.EAST), 1)
                        .add((BlockState) PaleWorldBlocks.SMALL_DYING_DRIPLEAF.getDefaultState().with(SmallDyingDripleafBlock.FACING, Direction.WEST), 1)
                        .add((BlockState) PaleWorldBlocks.SMALL_DYING_DRIPLEAF.getDefaultState().with(SmallDyingDripleafBlock.FACING, Direction.NORTH), 1)
                        .add((BlockState) PaleWorldBlocks.SMALL_DYING_DRIPLEAF.getDefaultState().with(SmallDyingDripleafBlock.FACING, Direction.SOUTH), 1))),
                new PlacementModifier[0]
        );
    }

    private static RegistryEntry<PlacedFeature> createBigDyingDripleaf(Direction direction) {
        return PlacedFeatures.createEntry(Feature.BLOCK_COLUMN, new BlockColumnFeatureConfig(List.of(BlockColumnFeatureConfig.createLayer(new WeightedListIntProvider(Pool.<IntProvider>builder()
                .add(UniformIntProvider.create(0, 4), 2)
                .add(ConstantIntProvider.create(0), 1).build()),
                BlockStateProvider.of((BlockState)PaleWorldBlocks.BIG_DYING_DRIPLEAF_STEM.getDefaultState()
                        .with(Properties.HORIZONTAL_FACING, direction))),
                BlockColumnFeatureConfig.createLayer(ConstantIntProvider.create(1),
                        BlockStateProvider.of((BlockState)PaleWorldBlocks.BIG_DYING_DRIPLEAF.getDefaultState()
                                .with(Properties.HORIZONTAL_FACING, direction)))),
                        Direction.UP, BlockPredicate.IS_AIR_OR_WATER, true),
                new PlacementModifier[0]);
    }

    private static FallenTreeFeatureConfig.Builder fallenPaleOak() {
        return fallen(Blocks.PALE_OAK_LOG, 4, 7).stumpDecorators(ImmutableList.of(TrunkVineTreeDecorator.INSTANCE));
    }

    private static FallenTreeFeatureConfig.Builder fallen(Block log, int minLength, int maxLength) {
        return (new FallenTreeFeatureConfig.Builder(BlockStateProvider.of(log), UniformIntProvider.create(minLength, maxLength))).logDecorators(ImmutableList.of());
    }

    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry((Feature)Feature.SIMPLE_BLOCK, (FeatureConfig)(new SimpleBlockFeatureConfig(block))));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?,?>> configuredFeatureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = configuredFeatureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntryLookup<StructureProcessorList> registryEntryLookup2 = configuredFeatureRegisterable.getRegistryLookup(RegistryKeys.PROCESSOR_LIST);

        // Underground / Pale Cave features
        WeightedBlockStateProvider weightedBlockStateProvider = new WeightedBlockStateProvider(Pool.<BlockState>builder().add(PaleWorldBlocks.PALE_VINE_BODY.getDefaultState(), 4).add((BlockState)PaleWorldBlocks.PALE_VINE_BODY.getDefaultState().with(PaleVines.BERRIES, Boolean.TRUE), 1));
        WeightedBlockStateProvider hangingWeighted = new WeightedBlockStateProvider(Pool.<BlockState>builder().add(Blocks.PALE_HANGING_MOSS.getDefaultState(), 2).add((BlockState)Blocks.PALE_HANGING_MOSS.getDefaultState(), 1));

        RandomizedIntBlockStateProvider randomizedIntBlockStateProvider = new RandomizedIntBlockStateProvider(new WeightedBlockStateProvider(Pool.<BlockState>builder()
                .add(PaleWorldBlocks.PALE_VINE.getDefaultState(), 4)
                .add((BlockState)PaleWorldBlocks.PALE_VINE.getDefaultState().with(PaleVines.BERRIES, Boolean.TRUE), 1)), String.valueOf(PaleVineBodyBlock.AGE), UniformIntProvider.create(20, 23));

        RandomizedIntBlockStateProvider hangingRandomized = new RandomizedIntBlockStateProvider(new WeightedBlockStateProvider(Pool.<BlockState>builder()
                .add(Blocks.PALE_HANGING_MOSS.getDefaultState(), 1)
                .add((BlockState)Blocks.PALE_HANGING_MOSS.getDefaultState(), 1)), String.valueOf(PaleVineBodyBlock.AGE), UniformIntProvider.create(20, 23));

        register(configuredFeatureRegisterable,
                DYING_DRIPLEAF,
                Feature.SIMPLE_RANDOM_SELECTOR,
                new SimpleRandomFeatureConfig(
                        RegistryEntryList.of(createSmallDyingDripleaf(),
                                createSmallDyingDripleaf(),
                                createBigDyingDripleaf(Direction.EAST),
                                createBigDyingDripleaf(Direction.WEST),
                                createBigDyingDripleaf(Direction.SOUTH),
                                createBigDyingDripleaf(Direction.NORTH))
                )
        );

        register(configuredFeatureRegisterable, PALE_CAVE_VEG, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(
                Pool.<BlockState>builder()
                        .add(Blocks.DEAD_BUSH.getDefaultState(),5)
                        .add(PaleWorldBlocks.DYING_AZALEA.getDefaultState(),2)
                        .add(Blocks.PALE_MOSS_CARPET.getDefaultState(), 25)
                        .add(Blocks.SHORT_GRASS.getDefaultState(),15)
                        .add(Blocks.SHORT_DRY_GRASS.getDefaultState(),30)
                        .add(Blocks.TALL_DRY_GRASS.getDefaultState(),7))));

        register(configuredFeatureRegisterable, PALE_CAVE_CEILING_VEG_MIXED, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(
                Pool.<BlockState>builder()
                        .add(Blocks.PALE_HANGING_MOSS.getDefaultState(),7)
                        .add(PaleWorldBlocks.PALE_VINE_BODY.getDefaultState(), 7)
                        .add(Blocks.AIR.getDefaultState(), 7))));


        register(configuredFeatureRegisterable,
                PALE_CAVE_PATCH,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        BlockTags.MOSS_REPLACEABLE,
                        BlockStateProvider.of(Blocks.PALE_MOSS_BLOCK),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(PALE_CAVE_VEG)),
                        VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(1),
                        0.0f,
                        5,
                        0.8f,
                        UniformIntProvider.create(4,7),
                        0.3f
                ));

        register(configuredFeatureRegisterable,
                PALE_VINE,
                Feature.BLOCK_COLUMN,
                new BlockColumnFeatureConfig(
                        List.of(
                                BlockColumnFeatureConfig.createLayer(
                                        new WeightedListIntProvider(
                                                Pool.<IntProvider>builder()
                                                        .add(UniformIntProvider.create(0,19), 2)
                                                        .add(UniformIntProvider.create(0,2),3)
                                                        .add(UniformIntProvider.create(0,6),10)
                                                        .build()
                                        ),
                                        weightedBlockStateProvider
                                ),
                                BlockColumnFeatureConfig.createLayer(ConstantIntProvider.create(1), randomizedIntBlockStateProvider)
                        ),
                        Direction.UP,
                        BlockPredicate.IS_AIR,
                        true
                ));

        register(configuredFeatureRegisterable,
                PALE_VINE_IN_MOSS,
                Feature.BLOCK_COLUMN,
                new BlockColumnFeatureConfig(
                        List.of(
                                BlockColumnFeatureConfig.createLayer(
                                        new WeightedListIntProvider(Pool.<IntProvider>builder().add(UniformIntProvider.create(0,3), 2).add(UniformIntProvider.create(1,7),1).add(UniformIntProvider.create(0,1), 10).build()),
                                        weightedBlockStateProvider
                                ),
                                BlockColumnFeatureConfig.createLayer(ConstantIntProvider.create(1), randomizedIntBlockStateProvider)
                        ),
                        Direction.DOWN,
                        BlockPredicate.IS_AIR,
                        true
                )
        );

        register(
                configuredFeatureRegisterable,
                PALE_CAVE_CEILING,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        BlockTags.MOSS_REPLACEABLE,
                        BlockStateProvider.of(Blocks.PALE_MOSS_BLOCK),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(PALE_VINE_IN_MOSS)),
                        VerticalSurfaceType.CEILING,
                        UniformIntProvider.create(1,2),
                        0.0f,
                        5,
                        0.5f,
                        UniformIntProvider.create(4,6),
                        0.3f
                ));

        register(configuredFeatureRegisterable, CEILING_HANGING_MOSS,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(
                        Pool.<BlockState>builder()
                                .add(Blocks.PALE_HANGING_MOSS.getDefaultState(), 5)
                                .add(Blocks.AIR.getDefaultState(), 7)
                )));

        register(configuredFeatureRegisterable,
                HANGING_MOSS_IN_PALE,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        BlockTags.MOSS_REPLACEABLE,
                        BlockStateProvider.of(Blocks.PALE_MOSS_BLOCK),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(CEILING_HANGING_MOSS)),
                        VerticalSurfaceType.CEILING,
                        UniformIntProvider.create(1,3),
                        0,
                        1,
                        0.8F,
                        UniformIntProvider.create(4,7),
                        0.3F
                ));

        register(
                configuredFeatureRegisterable,
                CLAY_DRIPLEAF,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        BlockTags.LUSH_GROUND_REPLACEABLE,
                        BlockStateProvider.of(Blocks.CLAY),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(DYING_DRIPLEAF)),
                        VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(3),
                        0.8F,
                        2,
                        0.5F,
                        UniformIntProvider.create(4, 7),
                        0.7F
                ));

        register(
                configuredFeatureRegisterable,
                CLAY_POOL_DRIPLEAF,
                Feature.WATERLOGGED_VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        BlockTags.LUSH_GROUND_REPLACEABLE,
                        BlockStateProvider.of(Blocks.CLAY),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(DYING_DRIPLEAF)),
                        VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(3),
                        0.8F,
                        5,
                        0.1F,
                        UniformIntProvider.create(4, 7),
                        0.7F
                )
        );

        register(
                configuredFeatureRegisterable,
                PALE_CAVES_CLAY,
                Feature.RANDOM_BOOLEAN_SELECTOR,
                new RandomBooleanFeatureConfig(
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(CLAY_DRIPLEAF)),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(CLAY_POOL_DRIPLEAF))
                )
        );

        register(
                configuredFeatureRegisterable,
                PALE_CAVE_GEODE,
                Feature.GEODE,
                new GeodeFeatureConfig(
                        new GeodeLayerConfig(
                                BlockStateProvider.of(Blocks.AIR),
                                BlockStateProvider.of(Blocks.AMETHYST_BLOCK),
                                BlockStateProvider.of(Blocks.BUDDING_AMETHYST),
                                BlockStateProvider.of(PaleWorldBlocks.WHITE_CRYSTAL),
                                BlockStateProvider.of(Blocks.SMOOTH_BASALT),
                                List.of(
                                        Blocks.SMALL_AMETHYST_BUD.getDefaultState(),
                                        Blocks.MEDIUM_AMETHYST_BUD.getDefaultState(),
                                        Blocks.LARGE_AMETHYST_BUD.getDefaultState(),
                                        Blocks.AMETHYST_CLUSTER.getDefaultState()
                                ),
                                BlockTags.FEATURES_CANNOT_REPLACE,
                                BlockTags.GEODE_INVALID_BLOCKS
                        ),
                        new GeodeLayerThicknessConfig(1.7, 2.2, 3.2, 4.2),
                        new GeodeCrackConfig(0.95, 2.0, 2),
                        0.35,
                        0.083,
                        true,
                        UniformIntProvider.create(4, 6),
                        UniformIntProvider.create(3, 4),
                        UniformIntProvider.create(1, 2),
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
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.STRIPPED_PALE_OAK_LOG), new DarkOakTrunkPlacer(6,2,1),
                        BlockStateProvider.of(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.25F, 0.4F, 0.8F)))
                        .ignoreVines()
                        .build());

        register(configuredFeatureRegisterable,
                TALL_STRIPPED_PALE_OAK,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.STRIPPED_PALE_OAK_LOG), new DarkOakTrunkPlacer(10,4,2),
                        BlockStateProvider.of(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.25F, 0.4F, 0.8F)))
                        .ignoreVines()
                        .build());

        register(configuredFeatureRegisterable,
                TALL_PALE_OAK,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.PALE_OAK_LOG), new DarkOakTrunkPlacer(10,4,2),
                        BlockStateProvider.of(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.25F, 0.4F, 0.8F)))
                        .ignoreVines()
                        .build());


        register(configuredFeatureRegisterable,
                BARE_PALE_OAK,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.PALE_OAK_LOG), new DarkOakTrunkPlacer(6,2,1),
                        BlockStateProvider.of(Blocks.AIR), new BlankLeaves(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.00F, 0.4F, 0.8F)))
                        .ignoreVines()
                        .build());

        register(configuredFeatureRegisterable,
                STRIPPED_BARE_PALE_OAK,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.STRIPPED_PALE_OAK_LOG), new DarkOakTrunkPlacer(6,2,1),
                        BlockStateProvider.of(Blocks.AIR), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.00F, 0.4F, 0.8F)))
                        .ignoreVines()
                        .build());

        register(configuredFeatureRegisterable,
                SMALL_PALE_OAK,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.PALE_OAK_LOG), new ForkingTrunkPlacer(6,2,1),
                        BlockStateProvider.of(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.25F, 0.4F, 0.8F)))
                        .ignoreVines()
                        .build());

        register(configuredFeatureRegisterable,
                STRIPPED_SMALL_PALE_OAK,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.STRIPPED_PALE_OAK_LOG), new ForkingTrunkPlacer(6,2,1),
                        BlockStateProvider.of(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.25F, 0.4F, 0.8F)))
                        .ignoreVines()
                        .build());

        register(configuredFeatureRegisterable,
                BARE_SMALL_PALE_OAK,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.PALE_OAK_LOG), new ForkingTrunkPlacer(6,2,1),
                        BlockStateProvider.of(Blocks.AIR), new BlankLeaves(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.25F, 0.4F, 0.8F)))
                        .ignoreVines()
                        .build());

        register(configuredFeatureRegisterable,
                BARE_STRIPPED_SMALL_PALE_OAK,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.STRIPPED_PALE_OAK_LOG), new ForkingTrunkPlacer(6,2,1),
                        BlockStateProvider.of(Blocks.AIR), new BlankLeaves(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.0F, 0.4F, 0.8F)))
                        .ignoreVines()
                        .build());

        // Pale Oaks with creaking hearts

        register(configuredFeatureRegisterable,
                TALL_PALE_OAK_WITH_HEART,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.PALE_OAK_LOG), new DarkOakTrunkPlacer(10,4,2),
                        BlockStateProvider.of(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.25F, 0.4F, 0.8F), new CreakingHeartTreeDecorator(1.0f)))
                        .ignoreVines()
                        .build());


        register(configuredFeatureRegisterable,
                BARE_PALE_OAK_WITH_HEART,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.PALE_OAK_LOG), new DarkOakTrunkPlacer(6,2,1),
                        BlockStateProvider.of(Blocks.AIR), new BlankLeaves(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.00F, 0.4F, 0.8F), new CreakingHeartTreeDecorator(1.0f)))
                        .ignoreVines()
                        .build());


        register(configuredFeatureRegisterable,
                SMALL_PALE_OAK_WITH_HEART,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.PALE_OAK_LOG), new ForkingTrunkPlacer(6,2,1),
                        BlockStateProvider.of(Blocks.PALE_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.25F, 0.4F, 0.8F), new CreakingHeartTreeDecorator(1.0f)))
                        .ignoreVines()
                        .build());


        register(configuredFeatureRegisterable,
                BARE_SMALL_PALE_OAK_WITH_HEART,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.PALE_OAK_LOG), new ForkingTrunkPlacer(6,2,1),
                        BlockStateProvider.of(Blocks.AIR), new BlankLeaves(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1,1,0,1,2, OptionalInt.empty()))
                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.25F, 0.4F, 0.8F), new CreakingHeartTreeDecorator(1.0f)))
                        .ignoreVines()
                        .build());

        register(configuredFeatureRegisterable, PALE_SPIKE, PaleWorld.PALE_SPIKE);

        register(configuredFeatureRegisterable, FALLEN_PALE_OAK, Feature.FALLEN_TREE, fallenPaleOak().build());

        register(configuredFeatureRegisterable,
                CHRYSANTHEMUM,
                Feature.FLOWER,
                new RandomPatchFeatureConfig(50, 4, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                        (new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2345L, new DoublePerlinNoiseSampler.NoiseParameters
                                (0, 1.0, new double[0]), 0.020833334F,
                                List.of(PaleWorldBlocks.CHRYSANTHEMUM.getDefaultState())))))));

        register(configuredFeatureRegisterable,
                ASPHODEL,
                Feature.FLOWER,
                new RandomPatchFeatureConfig(40, 3, 1, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                        (new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2345L, new DoublePerlinNoiseSampler.NoiseParameters
                                (0, 1.0, new double[0]), 0.020833334F,
                                List.of(PaleWorldBlocks.ASPHODEL.getDefaultState())))))));
    }


    public static void register(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, Feature<DefaultFeatureConfig> feature) {
        register(registerable, key, feature, FeatureConfig.DEFAULT);
    }


    public static RegistryKey<ConfiguredFeature<?,?>> registryKey(String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(PaleWorld.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?,?>> context,

                                                                                  RegistryKey<ConfiguredFeature<?,?>> key, F feature, FC config){
        context.register(key, new ConfiguredFeature<>(feature, config));
    }


    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return (F)(Registry.register(Registries.FEATURE, name, feature));
    }

}