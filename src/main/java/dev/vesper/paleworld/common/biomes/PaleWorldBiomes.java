package dev.vesper.paleworld.common.biomes;

import dev.vesper.paleworld.PaleWorld;
import dev.vesper.paleworld.common.client.render.particle.ParticleTypes;
import dev.vesper.paleworld.common.world.PaleWorldPlacedFeatures;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.resources.ResourceKey;
//? <1.21.11{
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
//?}
//? >=1.21.11{
/*import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.AmbientMoodSettings;
import net.minecraft.world.attribute.AmbientParticle;
import net.minecraft.resources.Identifier;
import net.minecraft.world.attribute.AmbientSounds;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
*///?}
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.List;
import java.util.Optional;

import static dev.vesper.paleworld.common.client.render.particle.ParticleTypes.MOSS_PARTICLE;

public class PaleWorldBiomes {
	//? <1.21.11{
	public static final ResourceKey<Biome> PALE_CAVE = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, "pale_caves"));
	public static final ResourceKey<Biome> PALE_VALLEY = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, "sparse_pale_garden"));
	//?}
	//? 1.21.11{
	/*public static final ResourceKey<Biome> PALE_CAVE = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(PaleWorld.MOD_ID, "pale_caves"));
	public static final ResourceKey<Biome> PALE_VALLEY = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(PaleWorld.MOD_ID, "sparse_pale_garden"));
	*///?}

	public static void bootstrap(BootstrapContext<Biome> context){
		context.register(PALE_CAVE, paleCave(context));
		context.register(PALE_VALLEY, paleValley(context));
	}

	public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
		BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
		BiomeDefaultFeatures.addDefaultSprings(builder);
		BiomeDefaultFeatures.addSurfaceFreezing(builder);
	}

	private static Biome paleCave(BootstrapContext<Biome> biomeRegisterable) {
		MobSpawnSettings.Builder spawner = new MobSpawnSettings.Builder();

		BiomeDefaultFeatures.commonSpawns(spawner);
		BiomeDefaultFeatures.oceanSpawns(spawner,1,1,3);

		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(biomeRegisterable.lookup(Registries.PLACED_FEATURE), biomeRegisterable.lookup(Registries.CONFIGURED_CARVER));
		builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, PaleWorldPlacedFeatures.PALE_GEODE);
		globalOverworldGeneration(builder);
		builder.addCarver(Carvers.CAVE);
		builder.addCarver(Carvers.CAVE_EXTRA_UNDERGROUND);
		builder.addCarver(Carvers.CANYON);
		//? <1.21.11{
		AmbientParticleSettings config = new AmbientParticleSettings((ParticleOptions) MOSS_PARTICLE, 0.05F);
		//?}
		//? 1.21.11{
		/*AmbientParticle config = new AmbientParticle((ParticleOptions) MOSS_PARTICLE, 0.05F);
		*///?}

		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addLushCavesSpecialOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		BiomeDefaultFeatures.addInfestedStone(builder);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.PALE_CAVE_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.PALE_CAVE_CEILING_PATCH_MIXED);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.PALE_CAVE_CEILING_PATCH);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.HANGING_MOSS_CEILING);
		builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, PaleWorldPlacedFeatures.PALE_CAVE_CLAY);

		return new Biome.BiomeBuilder()
				.hasPrecipitation(true)
				.downfall(0.8f)
				.temperature(0.7f)
				.generationSettings(builder.build())
				.mobSpawnSettings(spawner.build())
				.specialEffects((new BiomeSpecialEffects.Builder())
						.waterColor(0xff76889D)
						//? <1.21.11{
						.waterFogColor(0xff556980)
						.skyColor(0xffb9b9b9)
						.fogColor(0xff817770)
						.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
						.ambientParticle(config)
						//?}
						.grassColorOverride(0xff778272)
						.foliageColorOverride(0xff878D76)
						.build())
				//? >=1.21.11{
				/*.putAttributes(EnvironmentAttributeMap.builder()
						.set(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(MOSS_PARTICLE, 0.5f)))
						.set(EnvironmentAttributes.WATER_FOG_COLOR, 0xff556980)
						.set(EnvironmentAttributes.SKY_COLOR, 0xffb9b9b9)
						.set(EnvironmentAttributes.FOG_COLOR, 0xff817770)
						//.set(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(Optional.of(SoundEvents.AMBIENT_CAVE)))
						.set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_LUSH_CAVES))
						.build())
				*///?}
				.build();
	}


	public static Biome paleValley(BootstrapContext<Biome> biomeRegisterable){

		MobSpawnSettings.Builder spawner = new MobSpawnSettings.Builder();
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(biomeRegisterable.lookup(Registries.PLACED_FEATURE), biomeRegisterable.lookup(Registries.CONFIGURED_CARVER));

		BiomeDefaultFeatures.addDefaultSprings(builder);
		BiomeDefaultFeatures.commonSpawns(spawner);
		BiomeDefaultFeatures.farmAnimals(spawner);
		BiomeDefaultFeatures.addForestGrass(builder);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.BARE_STRIPPED_SMALL_PALE_OAK);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.BARE_SMALL_PALE_OAK);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.SMALL_PALE_OAK);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.STRIPPED_SMALL_PALE_OAK);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PaleWorldPlacedFeatures.CHRYSANTHEMUM);

		return new Biome.BiomeBuilder()
				.hasPrecipitation(true)
				.downfall(0.8f)
				.temperature(0.7f)
				.generationSettings(builder.build())
				.mobSpawnSettings(spawner.build())
				.specialEffects((new BiomeSpecialEffects.Builder())
						.waterColor(0xff76889D)
						//? <1.21.11{
						.waterFogColor(0xff556980)
						.skyColor(0xffb9b9b9)
						.fogColor(0xff817770)
						//?}
						.grassColorOverride(0xff778272)
						.foliageColorOverride(0xff878D76)
						.build()
				)
				//? >=1.21.11{
				/*.putAttributes(EnvironmentAttributeMap.builder()
						.set(EnvironmentAttributes.WATER_FOG_COLOR, 0xff556980)
						.set(EnvironmentAttributes.SKY_COLOR, 0xffb9b9b9)
						.set(EnvironmentAttributes.FOG_COLOR, 0xff817770)
						.build())
				*///?}
				.build();
	}
}
