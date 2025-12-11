package dev.vesper.paleworld.common.properties;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

import static net.minecraft.world.item.component.Consumables.defaultFood;

public class PaleWorldConsumables {
	public static final Consumable PALE_BERRIES = defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.BLINDNESS, 10, 1))).build();
	public static final Consumable PALE_APPLE = defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 0), new MobEffectInstance(MobEffects.REGENERATION, 300, 0), new MobEffectInstance(MobEffects.NAUSEA, 100, 1)))).build();
}
