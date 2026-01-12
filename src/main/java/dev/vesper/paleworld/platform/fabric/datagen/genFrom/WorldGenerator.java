package dev.vesper.paleworld.platform.fabric.datagen.genFrom;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class WorldGenerator extends FabricDynamicRegistryProvider {
	public WorldGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(HolderLookup.Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.BIOME));
		entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
		entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
	}

	@Override
	public @NotNull String getName() {
		return "Pale World World Generator";
	}
}
