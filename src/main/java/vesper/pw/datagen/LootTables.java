package vesper.pw.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import vesper.pw.block.PaleWorldBlocks;

import java.util.concurrent.CompletableFuture;

public class LootTables extends FabricBlockLootTableProvider {
    public LootTables(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(PaleWorldBlocks.WHITE_CRYSTAL);
        addDrop(PaleWorldBlocks.PALE_STONE);
        //addDrop(PaleWorldBlocks.CHRYSANTHEMUM);

    }
}
