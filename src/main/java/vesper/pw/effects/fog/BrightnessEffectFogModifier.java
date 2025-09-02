package vesper.pw.effects.fog;

import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.render.fog.StatusEffectFogModifier;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Unique;
import vesper.pw.effects.ModStatusEffects;

public class BrightnessEffectFogModifier extends StatusEffectFogModifier {
    @Unique
    public static float publicF;

    @Override
    public RegistryEntry<StatusEffect> getStatusEffect() {
        return ModStatusEffects.BRIGHTNESS;
    }

    @Override
    public void applyStartEndModifier(FogData data, Entity cameraEntity, BlockPos cameraPos, ClientWorld world, float viewDistance, RenderTickCounter tickCounter) {
            if (cameraEntity instanceof LivingEntity livingEntity){
                StatusEffectInstance statusEffectInstance = livingEntity.getStatusEffect(this.getStatusEffect());
                if (statusEffectInstance != null){
                    float f = MathHelper.lerp(statusEffectInstance.getFadeFactor(livingEntity, tickCounter.getTickProgress(false)), viewDistance, 15.0f);
                    publicF = f;
                    data.environmentalStart = f * 0.75f;
                    data.environmentalEnd = f;
                    data.skyEnd = f;
                    data.cloudEnd = f;
                }
            }
    }

    public float applyBrightnessModifier(LivingEntity cameraEntity, float brightness, float tickProgress){
        StatusEffectInstance statusEffectInstance = cameraEntity.getStatusEffect(this.getStatusEffect());
        return statusEffectInstance != null ? Math.max(statusEffectInstance.getFadeFactor(cameraEntity, tickProgress), brightness) : brightness;
    }

}
