package dev.vesper.paleworld.common.items;

import dev.vesper.paleworld.PaleWorld;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Function;

public class PaleWorldItems {
	public static final ResourceKey<Item> PALE_AXOLOTL_SPAWN_EGG_KEY = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, "pale_axolotl_spawn_egg"));
	public static final ResourceKey<Item> VAMPIRE_BAT_SPAWN_EGG_KEY = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, "vampire_bat_spawn_egg"));
	public static final ResourceKey<Item> PALE_AXOLOTL_BUCKET_KEY = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, "pale_axolotl_bucket"));
	public static final ResourceKey<Item> PALE_BERRIES_KEY = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, "pale_berries"));
	public static final ResourceKey<Item> PALE_COMPASS_KEY = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, "pale_compass"));


	//public static Item PALE_AXOLOTL_SPAWN_EGG = registerItems(new SpawnEggItem(Entities.PALE_AXOLOTL, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, "pale_axolotl_spawn_egg")))), PALE_AXOLOTL_SPAWN_EGG_KEY);
	public static Item PALE_AXOLOTL_SPAWN_EGG = registerItems(ResourceKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("pale_axolotl_spawn_egg")), SpawnEggItem::new, (new Item.Properties().spawnEgg(Entities.PALE_AXOLOTL)));
	//public static Item VAMPIRE_BAT_SPAWN_EGG = registerItems(new SpawnEggItem(Entities.VAMPIRE_BAT, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, "vampire_bat_spawn_egg")))), VAMPIRE_BAT_SPAWN_EGG_KEY);
	public static Item VAMPIRE_BAT_SPAWN_EGG = registerItems(ResourceKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("vampire_bat_spawn_egg")), SpawnEggItem::new, (new Item.Properties().spawnEgg(Entities.VAMPIRE_BAT)));
	public static Item PALE_BERRIES = registerItems(new PaleBerriesItem(new Item.Properties().food(FoodComponents.PALE_BERRIES, ConsumableComponents.PALE_BERRIES).registryKey(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, "pale_berries")))), PALE_BERRIES_KEY);
	public static Item PALE_APPLE = registerItems("pale_apple", new Item.Properties().food(FoodComponents.PALE_APPLE, ConsumableComponents.PALE_APPLE));

	public static Item PALE_AXOLOTL_BUCKET = registerItems(new MobBucketItem(Entities.PALE_AXOLOTL, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_AXOLOTL, new Item.Properties().maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT).registryKey(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID,"pale_axolotl_bucket")))),PALE_AXOLOTL_BUCKET_KEY);
	public static Item DRAINED_CRYSTAL_FRAGMENT = registerItems("drained_crystal_fragment");
	// public static Item PALE_COMPASS = registerItems("pale_compass", (new Item.Settings().rarity(Rarity.UNCOMMON).registryKey(PALE_COMPASS_KEY)));

	private static ResourceKey<Item> keyOf(String id) {
		return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, id));
	}

	private static ResourceKey<Item> keyOf(ResourceKey<Block> blockKey) {
		return ResourceKey.create(Registries.ITEM, blockKey.getValue());
	}

	public static Item registerItems(Item item, ResourceKey<Item> registryKey) {
		return Registry.register(Registries.ITEM, registryKey.getValue(), item);
	}

	public static Item registerItems(String id, Item.Settings settings) {
		return registerItems(keyOf(id), Item::new, settings);
	}

	public static Item registerItems(String id) {
		return registerItems(keyOf(id), Item::new, new Item.Properties());
	}

	public static Item registerItems(ResourceKey<Item> key, Function<Item.Settings, Item> factory, Item.Settings settings) {
		Item item = (Item) factory.apply(settings.registryKey(key));
		if (item instanceof BlockItem blockItem) {
			blockItem.appendBlocks(Item.BY_BLOCK, item);
		}
		return Registry.register(Registries.ITEM, key, item);
	}

	public static void regModItems(){}
}
