package dev.vesper.paleworld.common.world.gen.feature;

import dev.vesper.paleworld.common.blocks.PaleWorldBlocks;
import dev.vesper.paleworld.common.tags.PaleWorldBlockTags;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SpikeConfiguration;

public class MiscOverworldFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> PALE_SPIKE = FeatureUtils.createKey("pale_spike");

	public static void bootstrap(final BootstrapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, PALE_SPIKE, Feature.SPIKE, new SpikeConfiguration(PaleWorldBlocks.PALE_STONE.defaultBlockState(), BlockPredicate.matchesBlocks(Blocks.PALE_MOSS_BLOCK), BlockPredicate.matchesTag(PaleWorldBlockTags.PALE_SPIKE_REPLACEABLE)));
	}

	public static void init(){}
}
