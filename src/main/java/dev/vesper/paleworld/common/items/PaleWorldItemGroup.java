package dev.vesper.paleworld.common.items;

import dev.vesper.paleworld.common.blocks.PaleWorldBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import static dev.vesper.paleworld.PaleWorld.MOD_ID;

public class PaleWorldItemGroup {
	//? fabric{
	public static final CreativeModeTab PALE_WORLD = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MOD_ID, "paleworld"), FabricItemGroup.builder()
			.icon(() -> new ItemStack(Blocks.PALE_MOSS_BLOCK))
			.title(Component.translatable("itemgroup.paleworld"))
			.displayItems((((itemDisplayParameters, output) -> {
				output.accept(PaleWorldBlocks.WHITE_CRYSTAL);
				output.accept(PaleWorldBlocks.PALE_STONE);
				output.accept(PaleWorldItems.DRAINED_CRYSTAL_FRAGMENT);
				output.accept(PaleWorldItems.PALE_BERRIES);
				output.accept(PaleWorldItems.PALE_APPLE);
				output.accept(PaleWorldBlocks.CHRYSANTHEMUM);
				output.accept(PaleWorldBlocks.DYING_AZALEA);
				output.accept(PaleWorldBlocks.SMALL_DYING_DRIPLEAF);
				output.accept(PaleWorldBlocks.BIG_DYING_DRIPLEAF);
				output.accept(PaleWorldItems.PALE_AXOLOTL_BUCKET);
				output.accept(PaleWorldItems.PALE_AXOLOTL_SPAWN_EGG);
				output.accept(PaleWorldItems.VAMPIRE_BAT_SPAWN_EGG);
			}))).build());
//?}
	static void regItemGroup(){}
}
