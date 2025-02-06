package vesper.pw.item;

import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;
import vesper.pw.block.PaleWorldBlocks;
import vesper.pw.component.ConsumableComponents;
import vesper.pw.component.FoodComponents;
import vesper.pw.entity.Entities;
import vesper.pw.item.custom.PaleBerriesItem;

import java.util.function.Function;

public class PaleWorldItems {
    public static final RegistryKey<Item> PALE_AXOLOTL_SPAWN_EGG_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, "pale_axolotl_egg"));
    public static final RegistryKey<Item> VAMPIRE_BAT_SPAWN_EGG_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, "vampire_bat_egg"));
    public static final RegistryKey<Item> PALE_AXOLOTL_BUCKET_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, "pale_axolotl_bucket"));
    public static final RegistryKey<Item> PALE_BERRIES_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, "pale_berries"));


    public static Item PALE_AXOLOTL_SPAWN_EGG = registerItems(new SpawnEggItem(Entities.PALE_AXOLOTL, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, "pale_axolotl_egg")))), PALE_AXOLOTL_SPAWN_EGG_KEY);
    public static Item VAMPIRE_BAT_SPAWN_EGG = registerItems(new SpawnEggItem(Entities.VAMPIRE_BAT, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, "vampire_bat_egg")))), VAMPIRE_BAT_SPAWN_EGG_KEY);
    public static Item PALE_BERRIES = registerItems(new PaleBerriesItem(new Item.Settings().food(FoodComponents.PALE_BERRIES, ConsumableComponents.PALE_BERRIES).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, "pale_berries")))), PALE_BERRIES_KEY);

    public static Item PALE_AXOLOTL_BUCKET = registerItems(new EntityBucketItem(Entities.PALE_AXOLOTL, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_AXOLOTL, new Item.Settings().maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID,"pale_axolotl_bucket")))),PALE_AXOLOTL_BUCKET_KEY);


    public static Item registerItems(Item item, RegistryKey<Item> registryKey) {
        return Registry.register(Registries.ITEM, registryKey.getValue(), item);
    }

    public static void regModItems(){

    }
}
