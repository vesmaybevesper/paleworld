package dev.vesper.paleworld.platform.fabric.datagen.genFrom;
//? fabric{
import dev.vesper.paleworld.common.items.PaleWorldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static dev.vesper.paleworld.PaleWorld.MOD_ID;

public class Advancements extends FabricAdvancementProvider {
	public Advancements(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup);
	}

	@Override
	public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
		AdvancementHolder root = Advancement.Builder
				.advancement()
					.display(
							Items.PALE_MOSS_BLOCK,
							Component.translatable("advancement.root"),
							Component.translatable("advancement.rootText"),
							ResourceLocation.parse("textures/gui/advancements/backgrounds/stone.png"),
							AdvancementType.TASK,
							true,
							true,
							false)
				.addCriterion("pale_world_load", PlayerTrigger.TriggerInstance.tick())
				.save(consumer, MOD_ID + "/root");

		AdvancementHolder paleCaveAdvance = Advancement.Builder
				.advancement()
				.parent(root)
				.display(
						Items.DEAD_BUSH,
						Component.translatable("advancement.paleCaveEnter"),
						Component.translatable("advancement.paleCaveEnteredText"),
						null,
						AdvancementType.GOAL,
						true,
						true,
						false
				)
				.addCriterion("pale_cave_entered", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(registryLookup.getOrThrow(Registries.BIOME).value().getOrThrow(PaleWorldBiomes.PALE_CAVE))))
				.save(consumer, MOD_ID + "/pale_cave_entered");

		AdvancementHolder paleGardenAdvance = Advancement.Builder.advancement()
				.parent(root)
				.display(
						Items.CREAKING_HEART,
						Component.translatable("advancement.paleGardenEnter"),
						Component.translatable("advancement.paleGardenEnterText"),
						null,
						AdvancementType.GOAL,
						true,
						true,
						false
				)
				.addCriterion("pale_garden_entered", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(registryLookup.getOrThrow(Registries.BIOME).value().getOrThrow(Biomes.PALE_GARDEN))))
				.save(consumer, MOD_ID + "/pale_garden_entered");

		AdvancementHolder paleScholar = Advancement.Builder.advancement()
				.parent(paleGardenAdvance)
				.display(
						Items.PALE_MOSS_BLOCK,
						Component.translatable("advancement.paleScholar"),
						Component.translatable("advancement.paleScholarText"),
						null,
						AdvancementType.CHALLENGE,
						true,
						true,
						false
				)
				.addCriterion("pale_garden_entered", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(registryLookup.getOrThrow(Registries.BIOME).value().getOrThrow(Biomes.PALE_GARDEN))))
				.addCriterion("pale_cave_entered", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(registryLookup.getOrThrow(Registries.BIOME).value().getOrThrow(PaleWorldBiomes.PALE_CAVE))))
				.addCriterion("pale_forest_entered", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(registryLookup.getOrThrow(Registries.BIOME).value().getOrThrow(PaleWorldBiomes.PALE_VALLEY))))
				.save(consumer, MOD_ID + "/pale_scholar");


		AdvancementHolder paleAxolotlBucket = Advancement.Builder.advancement()
				.parent(paleCaveAdvance)
				.display(
						PaleWorldItems.PALE_AXOLOTL_BUCKET,
						Component.translatable("advancement.axolotlPickUp"),
						Component.translatable("advancement.axolotlPickUpText"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("pale_axolotl_bucketed", InventoryChangeTrigger.TriggerInstance.hasItems(PaleWorldItems.PALE_AXOLOTL_BUCKET))
				.save(consumer, MOD_ID + "/bucket_pale_axolotl");

		AdvancementHolder eatPaleBerries = Advancement.Builder.advancement()
				.parent(root)
				.display(
						PaleWorldItems.PALE_BERRIES,
						Component.translatable("advancement.eatAll"),
						Component.translatable("advancement.eatAllText"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("pale_berry_eaten", ConsumeItemTrigger.TriggerInstance.usedItem(registryLookup.getOrThrow(Registries.ITEM), PaleWorldItems.PALE_BERRIES))
				.addCriterion("pale_apple_eaten", ConsumeItemTrigger.TriggerInstance.usedItem(registryLookup.getOrThrow(Registries.ITEM), PaleWorldItems.PALE_APPLE))
				.save(consumer, MOD_ID + "/eat_pale_berry");
	}
}
//?}
