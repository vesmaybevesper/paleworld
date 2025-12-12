package dev.vesper.paleworld.platform.fabric.datagen.genFrom;

import dev.vesper.paleworld.common.blocks.PaleWorldBlocks;
import dev.vesper.paleworld.common.items.PaleWorldItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;

//? fabric{
public class ModelProvider extends FabricModelProvider {

	public ModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
		blockStateModelGenerator.createTrivialCube(PaleWorldBlocks.PALE_STONE);
		blockStateModelGenerator.createTrivialCube(PaleWorldBlocks.WHITE_CRYSTAL);
		blockStateModelGenerator.createCrossBlockWithDefaultItem(PaleWorldBlocks.CHRYSANTHEMUM, BlockModelGenerators.PlantType.NOT_TINTED);
		blockStateModelGenerator.createCrossBlockWithDefaultItem(PaleWorldBlocks.RAFFLESIA, BlockModelGenerators.PlantType.NOT_TINTED);
		blockStateModelGenerator.createCrossBlockWithDefaultItem(PaleWorldBlocks.ASPHODEL, BlockModelGenerators.PlantType.NOT_TINTED);
	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		itemModelGenerator.createCompassModels(PaleWorldItems.PALE_AXOLOTL_SPAWN_EGG);
		itemModelGenerator.createCompassModels(PaleWorldItems.VAMPIRE_BAT_SPAWN_EGG);
		itemModelGenerator.createCompassModels(Item.byBlock(PaleWorldBlocks.BIG_DYING_DRIPLEAF));
		itemModelGenerator.createCompassModels(Item.byBlock(PaleWorldBlocks.SMALL_DYING_DRIPLEAF));
		itemModelGenerator.createCompassModels(Item.byBlock(PaleWorldBlocks.DYING_AZALEA));
		itemModelGenerator.createCompassModels(Item.byBlock(PaleWorldBlocks.PETRIFIED_PALE_OAK));
		itemModelGenerator.createCompassModels(Item.byBlock(PaleWorldBlocks.PALE_VINE));
	}
}
//?}
