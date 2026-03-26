package dev.vesper.paleworld.common.items;

import dev.vesper.paleworld.common.blocks.PaleWorldBlocks;
//? fabric{
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
//?}
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
//? neoforge{
/*import net.neoforged.neoforge.registries.DeferredRegister;
*///?}

import static dev.vesper.paleworld.PaleWorld.MOD_ID;

public class PaleWorldItemGroup {
	//? neoforge{
	/*public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
	*///?}

	//? fabric{
	public static final CreativeModeTab PALE_WORLD = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(MOD_ID, "paleworld"), FabricCreativeModeTab.builder()
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

	//? neoforge{
		/*public static final Supplier<CreativeModeTab> PALE_WORLD = CREATIVE_MODE_TAB.register("paleworld", () -> CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.paleworld"))
			.icon(() -> new ItemStack(Items.PALE_MOSS_BLOCK))
			.displayItems((params, output) -> {
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
			}).build());
	*///?}
	public static void regItemGroup(){}
}
