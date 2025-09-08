package vesper.pw.mixin.render;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vesper.esl.ESLModChecks;
import vesper.pw.PaleWorldConfig;
import vesper.pw.biomes.PaleWorldBiomes;
import vesper.pw.utils.varHolder;

import static vesper.pw.utils.varHolder.fogFade;


@Mixin(FogRenderer.class)
public abstract class FogRendererMixin {
    protected FogRendererMixin() {
    }

    @Unique
    private static final float FADE_SPEED = 0.002f;

    @Inject(method = "applyFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;getDevice()Lcom/mojang/blaze3d/systems/GpuDevice;", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void modifyFogSettings(Camera camera, int viewDistance, boolean thick, RenderTickCounter tickCounter, float skyDarkness, ClientWorld world, CallbackInfoReturnable<Vector4f> cir, @Local Vector4f color, @Local(ordinal = 2) float renderDistanceBlocks, @Local Entity entity, @Local FogData fogData) {
        if (!(entity instanceof ClientPlayerEntity player)) return;

        if (!ESLModChecks.isShaders()) {
            BlockPos pos = player.getBlockPos();
            RegistryEntry<Biome> biome = world.getBiome(pos);
            if (!biome.matchesKey(BiomeKeys.PALE_GARDEN) || !biome.matchesKey(PaleWorldBiomes.PALE_VALLEY)) return;
            int topY = world.getTopY(Heightmap.Type.WORLD_SURFACE, pos.getX(), pos.getZ());

            if (biome.matchesKey(BiomeKeys.PALE_GARDEN)) {
                if (player.getY() <= topY + 15 && player.getY() >= 15 && !player.isCreative() && !player.isSpectator()) {
                    fogFade = Math.min(fogFade + FADE_SPEED, 1f);
                } else if (fogFade > 0) {
                    fogFade = Math.min(fogFade - FADE_SPEED, 0);
                } else {
                    return;
                }

                PaleWorldConfig.horrorMode = PaleWorldConfig.horrorModeSelect == PaleWorldConfig.horrorVals.TRUE;

                float fogAlphaBase;
                if (PaleWorldConfig.horrorMode) {
                    fogData.environmentalStart = (renderDistanceBlocks * 0.8F) + fogFade * (0.1F - (renderDistanceBlocks * 0.8F));
                    fogData.environmentalEnd = (renderDistanceBlocks + fogFade * (8F - (renderDistanceBlocks)));
                    fogAlphaBase = 0.99F;
                    varHolder.fogEnvEnd = fogData.environmentalEnd;
                } else {
                    fogData.environmentalStart = (renderDistanceBlocks * 0.8F) + fogFade * (PaleWorldConfig.fogStart - (renderDistanceBlocks * 0.8F));
                    fogData.environmentalEnd = (renderDistanceBlocks + fogFade * (PaleWorldConfig.fogEnd - (renderDistanceBlocks)));
                    fogAlphaBase = PaleWorldConfig.fogTransparency;
                    varHolder.fogEnvEnd = fogData.environmentalEnd;
                }
                fogData.skyEnd = fogData.environmentalEnd;
                fogData.cloudEnd = fogData.environmentalEnd;

                color.x = color.x + fogFade * (0.8F - color.x);
                color.y = color.y + fogFade * (0.8F - color.y);
                color.z = color.z + fogFade * (0.85F - color.z);
                color.w = color.w + fogFade * (fogAlphaBase - color.w);
            }

            if (biome.matchesKey(PaleWorldBiomes.PALE_VALLEY)){
                if (player.getY() <= topY + 15 && player.getY() >= 15 && !player.isCreative() && !player.isSpectator()) {
                    fogFade = Math.min(fogFade + FADE_SPEED, 1f);
                } else if (fogFade > 0) {
                    fogFade = Math.min(fogFade - FADE_SPEED, 0);
                } else {
                    return;
                }

                fogData.environmentalStart = (renderDistanceBlocks * 0.8F) + fogFade * (PaleWorldConfig.fogStart - (renderDistanceBlocks * 0.8F));
                fogData.environmentalEnd = (renderDistanceBlocks + fogFade * (32 - (renderDistanceBlocks)));
                varHolder.fogEnvEnd = fogData.environmentalEnd;
                fogData.skyEnd = fogData.environmentalEnd;
                fogData.cloudEnd = fogData.environmentalEnd;
                color.x = color.x + fogFade * (0.8F - color.x);
                color.y = color.y + fogFade * (0.8F - color.y);
                color.z = color.z + fogFade * (0.85F - color.z);
                color.w = color.w + fogFade * (0.3f - color.w);
            }
        }
    }
}