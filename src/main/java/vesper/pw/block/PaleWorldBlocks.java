package vesper.pw.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;

public class PaleWorldBlocks {

    public static final Block PALE_VINE_PLACED = regBlock("pale_vine_placed", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PaleWorld.MOD_ID, "pale_vine_placed")))
            .noCollision().sounds(BlockSoundGroup.CAVE_VINES).ticksRandomly().luminance(state -> 7).breakInstantly().mapColor(MapColor.GRAY)));

    private static Block regBlock(String name, Block block){
        regBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PaleWorld.MOD_ID, name), block);
    }

    private static void regBlockItem(String item, Block block){
        Registry.register(Registries.ITEM, Identifier.of(PaleWorld.MOD_ID, item), new BlockItem(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, item))).useBlockPrefixedTranslationKey()));
    }

    public static void regModBlocks(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(PALE_VINE_PLACED);
    });
}
}

