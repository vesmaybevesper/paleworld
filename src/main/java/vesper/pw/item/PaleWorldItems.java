package vesper.pw.item;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;
import vesper.pw.entity.Entities;

public class PaleWorldItems {
    public static final RegistryKey<Item> PALE_AXOLOTL_SPAWN_EGG_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, "pale_axolotl_egg"));
    public static Item PALE_AXOLOTL_SPAWN_EGG = registerItems(new SpawnEggItem(Entities.PALE_AXOLOTL, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, "pale_axolotl_egg")))), PALE_AXOLOTL_SPAWN_EGG_KEY);

    public static Item registerItems(Item item, RegistryKey<Item> registryKey) {
        Item registeredItem = Registry.register(Registries.ITEM, registryKey.getValue(), item);
        return registeredItem;
    }

    public static void regModItems(){

    }
}
