package vesper.pw.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.*;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;
import vesper.pw.biomes.PaleWorldBiomes;
import vesper.pw.item.PaleWorldItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


public class Advancements extends FabricAdvancementProvider {
    public Advancements(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry root = Advancement.Builder
                .create()
                .display(
                        Items.PALE_MOSS_BLOCK,
                        Text.translatable("Pale World"),
                        Text.translatable("Load into a world with Pale World"),
                        Identifier.of("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false)
                .criterion("pale_world_load", TickCriterion.Conditions.createTick())
                .build(consumer, PaleWorld.MOD_ID + "/root");

        AdvancementEntry paleCaveAdvance = Advancement.Builder.create()
                .parent(root)
                .display(
                        Items.DEAD_BUSH,
                        Text.literal("Clinging To Life"),
                        Text.literal("Step foot in the Pale Caves"),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .criterion("pale_cave_entered", TickCriterion.Conditions.createLocation(LocationPredicate.Builder.createBiome(wrapperLookup.getOrThrow(RegistryKeys.BIOME).getOrThrow(PaleWorldBiomes.PALE_CAVE))))
                .build(consumer, PaleWorld.MOD_ID + "/pale_cave_entered");


        AdvancementEntry paleAxolotlBucket = Advancement.Builder.create()
                .parent(paleCaveAdvance)
                .display(
                        PaleWorldItems.PALE_AXOLOTL_BUCKET,
                        Text.translatable("You're My Friend Now"),
                        Text.translatable("Bucket a Pale Axolotl"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("pale_axolotl_bucketed", InventoryChangedCriterion.Conditions.items(PaleWorldItems.PALE_AXOLOTL_BUCKET))
                .build(consumer, PaleWorld.MOD_ID + "/bucket_pale_axolotl");

        AdvancementEntry eatPaleBerries = Advancement.Builder.create()
                .parent(root)
                .display(
                        PaleWorldItems.PALE_BERRIES,
                        Text.translatable("That Can't Be Healthy"),
                        Text.translatable("Eat all the Pale World food items"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("pale_berry_eaten", ConsumeItemCriterion.Conditions.item(wrapperLookup.getOrThrow(RegistryKeys.ITEM), PaleWorldItems.PALE_BERRIES))
                .build(consumer, PaleWorld.MOD_ID + "/eat_pale_berry");


    }
}



