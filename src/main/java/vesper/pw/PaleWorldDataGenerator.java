package vesper.pw;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import vesper.pw.biomes.PaleWorldBiomes;
import vesper.pw.datagen.Advancements;
import vesper.pw.datagen.WorldGenerator;
import vesper.pw.enchanting.Enchantments;
import vesper.pw.world.PaleWorldConfiguredFeatures;
import vesper.pw.world.PaleWorldPlacedFeatures;

public class PaleWorldDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(WorldGenerator::new);
		pack.addProvider(Advancements::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.BIOME, PaleWorldBiomes::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, PaleWorldConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PaleWorldPlacedFeatures::bootstrap);
		// registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, Enchantments::bootstrap);
	}
}
