package vesper.pw.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CaveVines;
import net.minecraft.block.CaveVinesHeadBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
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

import java.util.List;

public class PaleWorldConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_CAVE_PATCH = registryKey("pale_cave_patch");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_CAVE_VEG = registryKey("pale_cave_vegetation");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALE_VINE = registryKey("pale_vine");

    public static void bootstrap(Registerable<ConfiguredFeature<?,?>> configuredFeatureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = configuredFeatureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntryLookup<StructureProcessorList> registryEntryLookup2 = configuredFeatureRegisterable.getRegistryLookup(RegistryKeys.PROCESSOR_LIST);

    // Underground / Pale Cave features
        WeightedBlockStateProvider weightedBlockStateProvider = new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.CAVE_VINES_PLANT.getDefaultState(), 4).add((BlockState)Blocks.CAVE_VINES_PLANT.getDefaultState(), 1));

        RandomizedIntBlockStateProvider randomizedIntBlockStateProvider = new RandomizedIntBlockStateProvider(new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                .add(PaleWorldBlocks.PALE_VINE.getDefaultState(), 4)
                .add((BlockState)PaleWorldBlocks.PALE_VINE.getDefaultState(), 1)), CaveVinesHeadBlock.AGE, UniformIntProvider.create(23, 25));

        // register(configuredFeatureRegisterable, PALE_VINE, Feature.BLOCK_COLUMN, new BlockColumnFeatureConfig(List.of(BlockColumnFeatureConfig.createLayer(new WeightedListIntProvider(DataPool.builder().add(UniformIntProvider.create(0, 19), 2).add(UniformIntProvider.create(0, 2), 3).add(UniformIntProvider.create(0, 6), 10).build()), weightedBlockStateProvider), BlockColumnFeatureConfig.createLayer(ConstantIntProvider.create(1), randomizedIntBlockStateProvider)), Direction.DOWN, BlockPredicate.IS_AIR, true));

       register(configuredFeatureRegisterable, PALE_CAVE_VEG, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(
                DataPool.<BlockState>builder()
                        .add(Blocks.DEAD_BUSH.getDefaultState(),5)
                        .add(Blocks.AZALEA.getDefaultState(),2)
                        .add(Blocks.PALE_MOSS_CARPET.getDefaultState(), 25)
                        .add(Blocks.SHORT_GRASS.getDefaultState(),50)
                        .add(Blocks.TALL_GRASS.getDefaultState(),10))));


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
    }

    public static RegistryKey<ConfiguredFeature<?,?>> registryKey(String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(PaleWorld.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?,?>> context,

                                                                                  RegistryKey<ConfiguredFeature<?,?>> key, F feature, FC config){
        context.register(key, new ConfiguredFeature<>(feature, config));
    }





}
