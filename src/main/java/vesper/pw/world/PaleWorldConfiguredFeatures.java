package vesper.pw.world;

import net.minecraft.block.*;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import vesper.pw.PaleWorld;
import vesper.pw.block.PaleWorldBlocks;
import vesper.pw.block.custom.PaleVineBodyBlock;
import net.minecraft.block.BlockState;
import vesper.pw.block.custom.SmallDyingDripleafBlock;
import java.util.List;

public class PaleWorldConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_CAVE_PATCH = registryKey("pale_cave_patch");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_CAVE_VEG = registryKey("pale_cave_vegetation");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_CAVE_CEILING = registryKey("pale_vine_ceiling");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_VINE_IN_MOSS = registryKey("pale_vine_in_moss");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_CAVES_CLAY = registryKey("pale_caves_clay");
    public static final RegistryKey<ConfiguredFeature<?,?>> CLAY_DRIPLEAF = registryKey("clay_dripleaf");
    public static final RegistryKey<ConfiguredFeature<?,?>> CLAY_POOL_DRIPLEAF = registryKey("clay_pool_dripleaf");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_VINE = registryKey("pale_vine_feature");
    public static final RegistryKey<ConfiguredFeature<?,?>> DYING_DRIPLEAF = registryKey("dying_dripleaves");


    private static RegistryEntry<PlacedFeature> createSmallDyingDripleaf(){
        return PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                        .add((BlockState) PaleWorldBlocks.SMALL_DYING_DRIPLEAF.getDefaultState().with(SmallDyingDripleafBlock.FACING, Direction.EAST), 1)
                        .add((BlockState) PaleWorldBlocks.SMALL_DYING_DRIPLEAF.getDefaultState().with(SmallDyingDripleafBlock.FACING, Direction.WEST), 1)
                        .add((BlockState) PaleWorldBlocks.SMALL_DYING_DRIPLEAF.getDefaultState().with(SmallDyingDripleafBlock.FACING, Direction.NORTH), 1)
                        .add((BlockState) PaleWorldBlocks.SMALL_DYING_DRIPLEAF.getDefaultState().with(SmallDyingDripleafBlock.FACING, Direction.SOUTH), 1))),
                new PlacementModifier[0]
        );
    }

    private static RegistryEntry<PlacedFeature> createBigDyingDripleaf(Direction direction) {
        return PlacedFeatures.createEntry(Feature.BLOCK_COLUMN, new BlockColumnFeatureConfig(List.of(BlockColumnFeatureConfig.createLayer(new WeightedListIntProvider(DataPool.<IntProvider>builder()
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

    public static void bootstrap(Registerable<ConfiguredFeature<?,?>> configuredFeatureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = configuredFeatureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntryLookup<StructureProcessorList> registryEntryLookup2 = configuredFeatureRegisterable.getRegistryLookup(RegistryKeys.PROCESSOR_LIST);

    // Underground / Pale Cave features
        WeightedBlockStateProvider weightedBlockStateProvider = new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(PaleWorldBlocks.PALE_VINE_BODY.getDefaultState(), 2).add((BlockState)PaleWorldBlocks.PALE_VINE_BODY.getDefaultState(), 1));

        RandomizedIntBlockStateProvider randomizedIntBlockStateProvider = new RandomizedIntBlockStateProvider(new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                .add(PaleWorldBlocks.PALE_VINE.getDefaultState(), 1)
                .add((BlockState)PaleWorldBlocks.PALE_VINE.getDefaultState(), 1)), String.valueOf(PaleVineBodyBlock.AGE), UniformIntProvider.create(20, 23));

        register(configuredFeatureRegisterable,
                DYING_DRIPLEAF,
                Feature.SIMPLE_RANDOM_SELECTOR,
                new SimpleRandomFeatureConfig(
                        RegistryEntryList.of(new RegistryEntry[]{
                                createSmallDyingDripleaf(),
                                createSmallDyingDripleaf(),
                                createBigDyingDripleaf(Direction.EAST),
                                createBigDyingDripleaf(Direction.WEST),
                                createBigDyingDripleaf(Direction.SOUTH),
                                createBigDyingDripleaf(Direction.NORTH)})
                )
                );

       register(configuredFeatureRegisterable, PALE_CAVE_VEG, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(
                DataPool.<BlockState>builder()
                        .add(Blocks.DEAD_BUSH.getDefaultState(),5)
                        .add(PaleWorldBlocks.DYING_AZALEA.getDefaultState(),2)
                        .add(Blocks.PALE_MOSS_CARPET.getDefaultState(), 25)
                        .add(Blocks.SHORT_GRASS.getDefaultState(),30)
                        .add(Blocks.TALL_GRASS.getDefaultState(),7))));


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
                                               DataPool.<IntProvider>builder()
                                                       .add(UniformIntProvider.create(0,19), 2)
                                                       .add(UniformIntProvider.create(0,2),3)
                                                       .add(UniformIntProvider.create(0,6),10)
                                                       .build()
                                       ),
                                       weightedBlockStateProvider
                               ),
                               BlockColumnFeatureConfig.createLayer(ConstantIntProvider.create(1), randomizedIntBlockStateProvider)
                       ),
                       Direction.DOWN,
                       BlockPredicate.IS_AIR,
                       true
               ));

       register(configuredFeatureRegisterable,
               PALE_VINE_IN_MOSS,
               Feature.BLOCK_COLUMN,
               new BlockColumnFeatureConfig(
                       List.of(
                               BlockColumnFeatureConfig.createLayer(
                                       new WeightedListIntProvider(DataPool.<IntProvider>builder().add(UniformIntProvider.create(0,3), 5).add(UniformIntProvider.create(1,7),1).build()),
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
                       0.8f,
                       UniformIntProvider.create(4,7),
                       0.3f
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
    }

    public static RegistryKey<ConfiguredFeature<?,?>> registryKey(String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(PaleWorld.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?,?>> context,

                                                                                  RegistryKey<ConfiguredFeature<?,?>> key, F feature, FC config){
        context.register(key, new ConfiguredFeature<>(feature, config));
    }





}
