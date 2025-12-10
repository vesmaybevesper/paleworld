package dev.vesper.paleworld.common.world;

import dev.vesper.paleworld.PaleWorld;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

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
