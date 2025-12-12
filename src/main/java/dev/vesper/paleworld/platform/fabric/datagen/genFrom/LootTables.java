package dev.vesper.paleworld.platform.fabric.datagen.genFrom;
//? fabric{
import dev.vesper.paleworld.common.blocks.PaleWorldBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class LootTables extends FabricBlockLootTableProvider {
	public LootTables(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
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
