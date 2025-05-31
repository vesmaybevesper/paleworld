package vesper.pw.enchanting;

import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import vesper.pw.registry.tags.EntityTypeTags;

public class Enchantments {
    public static final RegistryKey<Enchantment> PRISMATIC = of("prismatic");

    public static void bootstrap(Registerable<Enchantment> registerable){
        RegistryEntryLookup<Item> registryEntryLookup = registerable.getRegistryLookup(RegistryKeys.ITEM);
        RegistryEntryLookup<Enchantment> registryEntryLookup2 = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<EntityType<?>> registryEntryLookup3 = registerable.getRegistryLookup(RegistryKeys.ENTITY_TYPE);
        register(registerable, PRISMATIC, Enchantment.builder(Enchantment.definition(registryEntryLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                5, 5, Enchantment.leveledCost(5, 8),
                Enchantment.leveledCost(25, 8), 2,
                new AttributeModifierSlot[]{AttributeModifierSlot.MAINHAND}))
                .exclusiveSet(registryEntryLookup2.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
                .addEffect(EnchantmentEffectComponentTypes.DAMAGE, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(2.5F)),
                        EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.create().type(EntityTypePredicate.create(registryEntryLookup3, EntityTypeTags.PRISMATIC)))));
    }


    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }

    private static RegistryKey<Enchantment> of(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(id));
    }
}
