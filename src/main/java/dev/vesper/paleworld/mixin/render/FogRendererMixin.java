package dev.vesper.paleworld.mixin.render;

import com.llamalad7.mixinextras.sugar.Local;
import dev.vesper.eveningstarlib.fabric.ESLModChecks;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FogRenderer.class)
public class FogRendererMixin {

	@Unique
	private static final float FADE_SPEED = 0.002f;

	@Inject(method = "setupFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;getDevice()Lcom/mojang/blaze3d/systems/GpuDevice;", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
	private void modifySetupFog(Camera camera, int i, boolean bl, DeltaTracker deltaTracker, float f, ClientLevel clientLevel, CallbackInfoReturnable<Vector4f> cir, @Local Vector4f color, @Local(ordinal = 2) float renderDistanceBlocks, @Local Entity entity, @Local FogData fogData){
		if (!(entity instanceof Player player)) return;

		if (!ESLModChecks.isShaders()){
			BlockPos pos = player.getOnPos();
			Holder<Biome> biome = clientLevel.getBiome(pos);

			if (!biome.is(Biomes.PALE_GARDEN) && !biome.is(PaleWorldBiomes.PALE_VALLEY)) return;
			int topY = clientLevel.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ());

			if (biome.is(Biomes.PALE_GARDEN)) {
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

			if (biome.is(PaleWorldBiomes.PALE_VALLEY)){
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
