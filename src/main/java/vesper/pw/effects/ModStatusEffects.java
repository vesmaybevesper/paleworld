package vesper.pw.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static final RegistryEntry<StatusEffect> BRIGHTNESS;

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect){
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(id), statusEffect);
    }

    static {
        BRIGHTNESS = register("brightness", (new StatusEffect(StatusEffectCategory.HARMFUL, 16777215)).fadeTicks(22));
    }
}
