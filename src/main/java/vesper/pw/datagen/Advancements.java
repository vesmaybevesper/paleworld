package vesper.pw.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import vesper.pw.PaleWorld;
import vesper.pw.biomes.PaleWorldBiomes;
import vesper.pw.utils.EnterBiomeCriterion;
import vesper.pw.utils.ModCriteria;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class Advancements extends FabricAdvancementProvider {
    public Advancements(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {


        RegistryEntry<Biome> paleCaveBiome = wrapperLookup
                .getOrThrow(RegistryKeys.BIOME)
                .getOrThrow(PaleWorldBiomes.PALE_CAVE);

        RegistryEntryList<Biome> pCaveBiomeList = RegistryEntryList.of(paleCaveBiome);

        AdvancementEntry root = Advancement.Builder
                .create()
                .display(
                        Items.PALE_MOSS_BLOCK,
                        Text.translatable("Pale World"),
                        Text.translatable("Load into a world with Pale World"),
                        Identifier.of("textures/gui/advancements/backgrounds/adventure.png"),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false)
                .criterion("pale_world_load", TickCriterion.Conditions.createTick())
                .build(consumer, PaleWorld.MOD_ID+":pale_world_root");


           /* AdvancementEntry paleCaveAdvance = Advancement.Builder.create()
                    .display(
                            Items.PALE_MOSS_CARPET,
                            Text.literal("Clinging To Life"),
                            Text.literal("Step foot in the Pale Caves"),
                            Identifier.ofVanilla("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.GOAL,
                            true,
                            true,
                            false
                    )
                    .criterion("pale_cave_entered", ModCriteria.ENTER_BIOME.create(new EnterBiomeCriterion.Conditions(Optional.empty())))
                    .build(consumer, PaleWorld.MOD_ID+":pale_cave_entered");

        AdvancementEntry paleAxolotlEat = Advancement.Builder.create()
                .display(
                        Items.AXOLOTL_SPAWN_EGG,
                        Text.literal("The Cutest Cannibal"),
                        Text.literal("Watch a Pale Axolotl kill an Axolotl"),
                        Identifier.ofVanilla("textures/gui/advancements/backgrounds/adventure.png"),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("pale_axolotl_eat", TickCriterion.Conditions.createTick())
                .build(consumer, PaleWorld.MOD_ID+":pale_axolotl_eat");*/

    }
}



