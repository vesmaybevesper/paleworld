package dev.vesper.paleworld;

import dev.vesper.paleworld.common.biomes.PaleWorldBiomePlacement;
import dev.vesper.paleworld.common.biomes.surface.MaterialRules;
import dev.vesper.paleworld.common.blocks.PaleWorldBlocks;
import dev.vesper.paleworld.common.client.render.particle.FogParticle;
import dev.vesper.paleworld.common.client.render.particle.MossParticle;
import dev.vesper.paleworld.common.client.render.particle.ParticleTypes;
import dev.vesper.paleworld.common.client.render.particle.RafflesiaParticle;
import dev.vesper.paleworld.common.entity.Entities;
import dev.vesper.paleworld.common.entity.EntityGen;
import dev.vesper.paleworld.common.entity.PaleAxolotl.PaleAxolotl;
import dev.vesper.paleworld.common.entity.PaleAxolotl.PaleAxolotlModel;
import dev.vesper.paleworld.common.entity.VampireBat.VampireBat;
import dev.vesper.paleworld.common.entity.VampireBat.VampireBatModel;
import dev.vesper.paleworld.common.entity.renderers.PaleAxolotlRenderer;
import dev.vesper.paleworld.common.entity.renderers.VampireBatRenderer;
import dev.vesper.paleworld.common.items.PaleWorldItemGroup;
import dev.vesper.paleworld.common.items.PaleWorldItems;
import dev.vesper.paleworld.common.modify.PaleGardenMobSpawnRates;
import dev.vesper.paleworld.common.world.gen.PaleWorldWorldGen;
import dev.vesper.paleworld.common.world.gen.feature.MiscOverworldFeatures;
import dev.vesper.paleworld.platform.Platform;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.vesper.paleworld.platform.fabric.FabricPlatform;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

@SuppressWarnings({"LoggingSimilarMessage"})
public class PaleWorld {

	public static final String MOD_ID = /*$ mod_id*/ "paleworld";
	public static final String MOD_VERSION = /*$ mod_version*/ "2.1.3";
	public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/ "Pale World";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	//public static final Feature<NoneFeatureConfiguration> PALE_SPIKE;
	
	private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String name, F feature) {
		return (F)(Registry.register(BuiltInRegistries.FEATURE, name, feature));
	}

	/*static {
		PALE_SPIKE = register("pale_spike", new PaleSpikeFeature(NoneFeatureConfiguration.CODEC));
	}*/

	public static void onInitialize() {
		LOGGER.info("Initializing {} on {}", MOD_ID, PaleWorld.xplat().loader());
		Entities.init();
		// these yell about failing but work fine
		FabricDefaultAttributeRegistry.register(Entities.PALE_AXOLOTL, PaleAxolotl.setAttributes().build());
		FabricDefaultAttributeRegistry.register(Entities.VAMPIRE_BAT, VampireBat.createHostileAttributes());
		//FabricDefaultAttributeRegistry.register(Entities.LOST_SOUL, LostSoul.createAttributes());
		PaleGardenMobSpawnRates.override();
		LOGGER.info("Registered Entities");
		ParticleTypes.register();
		PaleWorldItems.regModItems();
		PaleWorldBlocks.regModBlocks();
		PaleWorldItemGroup.regItemGroup();
		LOGGER.info("Registered Blocks & Items");
		//Registry.register(Registries.STATUS_EFFECT, Identifier.of("vcc", "brightness"), BRIGHTNESS);
		//LOGGER.info("Registered Effects");
        /*CustomSounds.init();
        LOGGER.info("Registered Sounds");*/
		MaterialRules.init();
		MiscOverworldFeatures.init();
		PaleWorldBiomePlacement.place();
		PaleWorldWorldGen.genWorld();
		EntityGen.addSpawns();
		LOGGER.info("Registered Generation");
	}

	public static void onInitializeClient() {
		LOGGER.info("Initializing {} Client on {}", MOD_ID, PaleWorld.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
		ModelLayerRegistry.registerModelLayer(PaleAxolotlModel.PALE_AXOLOTL, PaleAxolotlModel::getTexturedModelData);
		ModelLayerRegistry.registerModelLayer(VampireBatModel.VAMPIRE_BAT, VampireBatModel::getTexturedModelData);
		EntityRendererRegistry.register(Entities.PALE_AXOLOTL, PaleAxolotlRenderer::new);
		EntityRendererRegistry.register(Entities.VAMPIRE_BAT, VampireBatRenderer::new);
		//EntityRendererRegistry.register(Entities.LOST_SOUL, LostSoulRenderer::new);
		PaleWorld.LOGGER.info("Client: Mob Renderers Registered");

		ParticleProviderRegistry.getInstance().register(ParticleTypes.MOSS_PARTICLE, MossParticle.Factory::new);
		ParticleProviderRegistry.getInstance().register(ParticleTypes.FOG_PARTICLE, FogParticle.Factory::new);
		ParticleProviderRegistry.getInstance().register(ParticleTypes.RAFFLESIA_PARTICLE, RafflesiaParticle.Factory::new);
		//ParticleFactoryRegistry.getInstance().register(ParticleTypes.LOST_SOUL_AURA, LostSoulParticle.Factory::new);
		PaleWorld.LOGGER.info("Client: Particles Registered");
	}

	static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		 *///?}
	}
}
