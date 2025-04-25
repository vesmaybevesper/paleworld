package vesper.pw.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Model;
import net.minecraft.client.data.Models;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;
import vesper.pw.block.PaleWorldBlocks;
import vesper.pw.item.PaleWorldItems;

import java.util.Optional;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(PaleWorldBlocks.PALE_STONE);
        blockStateModelGenerator.registerSimpleCubeAll(PaleWorldBlocks.WHITE_CRYSTAL);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(PaleWorldItems.PALE_AXOLOTL_SPAWN_EGG);
        itemModelGenerator.register(PaleWorldItems.VAMPIRE_BAT_SPAWN_EGG);
        itemModelGenerator.register(Item.fromBlock(PaleWorldBlocks.BIG_DYING_DRIPLEAF));
        itemModelGenerator.register(Item.fromBlock(PaleWorldBlocks.SMALL_DYING_DRIPLEAF));
        itemModelGenerator.register(Item.fromBlock(PaleWorldBlocks.DYING_AZALEA));
        /*itemModelGenerator.register(Item.fromBlock(PaleWorldBlocks.PALE_STONE));
        itemModelGenerator.register(Item.fromBlock(PaleWorldBlocks.WHITE_CRYSTAL));*/
        itemModelGenerator.register(Item.fromBlock(PaleWorldBlocks.PETRIFIED_PALE_OAK));
        itemModelGenerator.register(Item.fromBlock(PaleWorldBlocks.PALE_VINE));
    }

}