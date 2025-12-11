package dev.vesper.paleworld.common.items;

import dev.vesper.paleworld.PaleWorld;
import dev.vesper.paleworld.common.items.custom.PaleBerriesItem;
import dev.vesper.paleworld.common.properties.PaleWorldConsumables;
import dev.vesper.paleworld.common.properties.PaleWorldFoodProperties;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.CustomData;
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
	public static Item PALE_BERRIES = registerItems(new PaleBerriesItem(new Item.Properties().food(PaleWorldFoodProperties.PALE_BERRIES, PaleWorldConsumables.PALE_BERRIES).setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, "pale_berries")))), PALE_BERRIES_KEY);
	public static Item PALE_APPLE = registerItems("pale_apple", new Item.Properties().food(PaleWorldFoodProperties.PALE_APPLE, PaleWorldConsumables.PALE_APPLE));

	public static Item PALE_AXOLOTL_BUCKET = registerItems(new MobBucketItem(Entities.PALE_AXOLOTL, Fluids.WATER, SoundEvents.BUCKET_EMPTY_AXOLOTL, new Item.Properties().stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY).setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID,"pale_axolotl_bucket")))),PALE_AXOLOTL_BUCKET_KEY);
	public static Item DRAINED_CRYSTAL_FRAGMENT = registerItems("drained_crystal_fragment");
	// public static Item PALE_COMPASS = registerItems("pale_compass", (new Item.Settings().rarity(Rarity.UNCOMMON).registryKey(PALE_COMPASS_KEY)));

	private static ResourceKey<Item> keyOf(String id) {
		return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(PaleWorld.MOD_ID, id));
	}

	private static ResourceKey<Item> keyOf(ResourceKey<Block> blockKey) {
		return ResourceKey.create(Registries.ITEM, blockKey.location());
	}

	public static Item registerItems(Item item, ResourceKey<Item> registryKey) {
		return Registry.register(BuiltInRegistries.ITEM, registryKey.location(), item);
	}

	public static Item registerItems(String id, Item.Properties settings) {
		return registerItems(keyOf(id), Item::new, settings);
	}

	public static Item registerItems(String id) {
		return registerItems(keyOf(id), Item::new, new Item.Properties());
	}

	public static Item registerItems(ResourceKey<Item> key, Function<Item.Properties, Item> factory, Item.Properties settings) {
		Item item = (Item) factory.apply(settings.setId(key));
		if (item instanceof BlockItem blockItem) {
			blockItem.registerBlocks(Item.BY_BLOCK, item);
		}
		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}

	public static void regModItems(){}
}
