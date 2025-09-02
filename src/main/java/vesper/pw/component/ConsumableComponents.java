package vesper.pw.component;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import vesper.pw.effects.ModStatusEffects;

import java.util.List;

import static net.minecraft.component.type.ConsumableComponents.food;

public class ConsumableComponents {

    public static final ConsumableComponent PALE_BERRIES = food().consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(ModStatusEffects.BRIGHTNESS, 200, 0))).build();
    public static final ConsumableComponent PALE_APPLE = food().consumeEffect(new ApplyEffectsConsumeEffect(List.of(new StatusEffectInstance(StatusEffects.ABSORPTION, 1200, 0), new StatusEffectInstance(StatusEffects.REGENERATION, 300, 0), new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1)))).build();
}
