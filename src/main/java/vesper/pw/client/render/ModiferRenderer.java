/*
package vesper.pw.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.MathHelper;

public class ModiferRenderer {

    @Environment(EnvType.CLIENT)
    static class BrightnessFogModifier implements StatusEffectFogModifier {
        BrightnessFogModifier() {
        }

        public RegistryEntry<StatusEffect> getStatusEffect() {
            return StatusEffects.BLINDNESS;
        }

        public void applyStartEndModifier(FogData fogData, LivingEntity entity, StatusEffectInstance effect, float viewDistance, float tickDelta) {
            float f = effect.isInfinite() ? 5.0F : MathHelper.lerp(Math.min(1.0F, (float)effect.getDuration() / 20.0F), viewDistance, 5.0F);
            if (fogData.fogType == BackgroundRenderer.FogType.FOG_SKY) {
                fogData.fogStart = 0.0F;
                fogData.fogEnd = f * 0.8F;
            } else if (fogData.fogType == BackgroundRenderer.FogType.FOG_TERRAIN) {
                fogData.fogStart = f * 0.25F;
                fogData.fogEnd = f;
            }

        }
    }

    @Environment(EnvType.CLIENT)
    static class FogData {
        public final FogRenderer.FogType fogType;
        public float fogStart;
        public float fogEnd;
        public FogShape fogShape;

        public FogData(BackgroundRenderer.FogType fogType) {
            this.fogShape = FogShape.SPHERE;
            this.fogType = fogType;
        }
    }

    @Environment(EnvType.CLIENT)
    interface StatusEffectFogModifier {
        RegistryEntry<StatusEffect> getStatusEffect();

        void applyStartEndModifier(FogData fogData, LivingEntity entity, StatusEffectInstance effect, float viewDistance, float tickDelta);

        default boolean shouldApply(LivingEntity entity, float tickDelta) {
            return entity.hasStatusEffect(this.getStatusEffect());
        }

        default float applyColorModifier(LivingEntity entity, StatusEffectInstance effect, float defaultModifier, float tickDelta) {
            StatusEffectInstance statusEffectInstance = entity.getStatusEffect(this.getStatusEffect());
            if (statusEffectInstance != null) {
                if (statusEffectInstance.isDurationBelow(19)) {
                    defaultModifier = 1.0F - (float)statusEffectInstance.getDuration() / 20.0F;
                } else {
                    defaultModifier = 0.0F;
                }
            }

            return defaultModifier;
        }
    }
}
*/
