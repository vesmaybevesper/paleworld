package vesper.pw.effects.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class BrightnessEffect extends StatusEffect {
    public static boolean isActive;
    public BrightnessEffect() {
        super(StatusEffectCategory.HARMFUL, 16777215);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        isActive = duration > 0;
        return duration >= 0;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity){

        }
        return false;
    }
}
