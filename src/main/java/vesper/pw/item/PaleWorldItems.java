package vesper.pw.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;

public class PaleWorldItems {

       // public static final Item PALE_VINE = regItems("pale_vine", new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, "pale_vine")))));

    public static Item regItems(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(PaleWorld.MOD_ID, name).toTranslationKey(), item);
    }

    public static void regModItems(){
        /*ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(PALE_VINE);
        });*/
    }
}
