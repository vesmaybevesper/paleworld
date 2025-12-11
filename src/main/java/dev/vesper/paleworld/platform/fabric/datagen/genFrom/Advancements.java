package dev.vesper.paleworld.platform.fabric.datagen.genFrom;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.Items;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static dev.vesper.paleworld.PaleWorld.MOD_ID;

public class Advancements extends FabricAdvancementProvider {
	protected Advancements(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup);
	}

	@Override
	public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
		AdvancementHolder root = Advancement.Builder.advancement().build(consumer, MOD_ID + "/root");
	}
}
