package dev.vesper.paleworld.platform.fabric.datagen;

//? fabric {
import dev.vesper.paleworld.common.world.PaleWorldConfiguredFeatures;
import dev.vesper.paleworld.common.world.PaleWorldPlacedFeatures;
import dev.vesper.paleworld.platform.fabric.datagen.genFrom.Advancements;
import dev.vesper.paleworld.platform.fabric.datagen.genFrom.LootTables;
import dev.vesper.paleworld.platform.fabric.datagen.genFrom.ModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class FabricDataGeneratorEntrypoint implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		final FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(Advancements::new);
		pack.addProvider(LootTables::new);
		pack.addProvider(ModelProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.BIOME, PaleWorldBiomes::bootstrap);
		registryBuilder.add(Registries.CONFIGURED_FEATURE, PaleWorldConfiguredFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, PaleWorldPlacedFeatures::bootstrap);
	}
}
//?}
