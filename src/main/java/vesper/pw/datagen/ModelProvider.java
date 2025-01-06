package vesper.pw.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.item.Item;
import vesper.pw.block.PaleWorldBlocks;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
            blockStateModelGenerator.registerHangingMoss(PaleWorldBlocks.PALE_VINE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(Item.fromBlock(PaleWorldBlocks.PALE_VINE), Models.CROP);
    }
}
