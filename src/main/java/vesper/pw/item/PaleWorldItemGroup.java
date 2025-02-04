package vesper.pw.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;
import vesper.pw.block.PaleWorldBlocks;

public class PaleWorldItemGroup {

    public static final ItemGroup PALE_WORLD = Registry.register(Registries.ITEM_GROUP, Identifier.of(PaleWorld.MOD_ID, "pale_world"), FabricItemGroup.builder()
                    .icon(() -> new ItemStack(Blocks.PALE_MOSS_BLOCK))
                    .displayName(Text.translatable("itemgroup.pale-world.pale-world"))
                    .entries(((displayContext, entries) -> {
                        entries.add(PaleWorldBlocks.PALE_VINE);
                        entries.add(PaleWorldBlocks.PETRIFIED_PALE_OAK);
                        entries.add(PaleWorldBlocks.PALE_VINE_BODY);
                        entries.add(PaleWorldItems.PALE_BERRIES);
                        entries.add(PaleWorldBlocks.DYING_AZALEA);
                        entries.add(PaleWorldBlocks.SMALL_DYING_DRIPLEAF);
                        entries.add(PaleWorldBlocks.BIG_DYING_DRIPLEAF);
                        entries.add(PaleWorldItems.PALE_AXOLOTL_BUCKET);
                        entries.add(PaleWorldItems.PALE_AXOLOTL_SPAWN_EGG);
                        entries.add(PaleWorldItems.VAMPIRE_BAT_SPAWN_EGG);

                    }))
            .build());

    public static void regItemGroups(){

    }
}
