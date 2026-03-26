package dev.vesper.paleworld.platform.fabric.datagen.genFrom;
//? fabric{
import dev.vesper.paleworld.common.blocks.PaleWorldBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class LootTables extends FabricBlockLootSubProvider {
	public LootTables(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		dropSelf(PaleWorldBlocks.WHITE_CRYSTAL);
		dropSelf(PaleWorldBlocks.PALE_STONE);
		dropSelf(PaleWorldBlocks.CHRYSANTHEMUM);
		dropSelf(PaleWorldBlocks.RAFFLESIA);
		dropSelf(PaleWorldBlocks.ASPHODEL);
	}
}
//?}
