package vesper.pw;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vesper.pw.block.PaleWorldBlocks;
import vesper.pw.entity.EntityGen;
import vesper.pw.entity.Entities;
import vesper.pw.entity.PaleAxolotl.PaleAxolotl;
import vesper.pw.entity.VampireBat.VampireBat;
import vesper.pw.item.PaleWorldItemGroup;
import vesper.pw.item.PaleWorldItems;
import vesper.pw.modify.PaleGardenSpawnRate;
import vesper.pw.world.gen.PaleWorldWorldGen;
import vesper.pw.world.gen.feature.PaleSpikeFeature;

public class PaleWorld implements ModInitializer {
	public static final String MOD_ID = "paleworld";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final SimpleParticleType FOG_PARTICLE = FabricParticleTypes.simple();

	// Add to registries
	public static final Feature<DefaultFeatureConfig> PALE_SPIKE;

	private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
		return (F)(Registry.register(Registries.FEATURE, name, feature));
	}

	static {
		PALE_SPIKE = register("pale_spike", new PaleSpikeFeature(DefaultFeatureConfig.CODEC));
	}




    @Override
	public void onInitialize() {
		// Register Items, Blocks and World Gen
		MidnightConfig.init(PaleWorld.MOD_ID, PaleWorldConfig.class);
		LOGGER.info("Registered Config");
		Entities.init();
		FabricDefaultAttributeRegistry.register(Entities.PALE_AXOLOTL, PaleAxolotl.setAttributes());
		FabricDefaultAttributeRegistry.register(Entities.VAMPIRE_BAT, VampireBat.createHostileAttributes());
		PaleGardenSpawnRate.override();
		LOGGER.info("Registered Entities");
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "fog_particle"), FOG_PARTICLE);
		PaleWorldItems.regModItems();
		PaleWorldBlocks.regModBlocks();
		PaleWorldItemGroup.regItemGroups();
		LOGGER.info("Registered Blocks & Items");
		PaleWorldWorldGen.genWorld();
		EntityGen.addSpawns();
		LOGGER.info("Registered Generation");
	}
}