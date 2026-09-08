package dev.vesper.paleworld.mixin.render;

import com.llamalad7.mixinextras.sugar.Local;
import dev.vesper.eveningstarlib.common.ESLModChecks;
import dev.vesper.paleworld.common.config.PaleWorldConfig;
import dev.vesper.paleworld.common.util.FogCode;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FogRenderer.class)
public class FogRendererMixin {

	@Unique
	private static float fogAlphaBase;
	@Unique
	private static Entity capturedEntity;
	@Unique
	private static float renderBlocks;
	@Unique
	private static Vector4f capturedColor;
	@Unique
	private static FogData capturedFog;

	@Inject(method = "setupFog", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/fog/environment/FogEnvironment;setupFog(Lnet/minecraft/client/renderer/fog/FogData;Lnet/minecraft/client/Camera;Lnet/minecraft/client/multiplayer/ClientLevel;FLnet/minecraft/client/DeltaTracker;)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
	private static void onFogStart(Camera camera, int renderDistanceInChunks, DeltaTracker deltaTracker, float darkenWorldAmount, ClientLevel level, CallbackInfoReturnable<FogData> cir, @Local(name = "entity") Entity entity) {
		capturedEntity = entity;
		renderBlocks = renderDistanceInChunks * 16;
	}

	@Inject(method = "updateBuffer(Lnet/minecraft/client/renderer/fog/FogData;)V", at = @At("HEAD"))
	private void updateBuffer(FogData fog, CallbackInfo ci) {
		if (!ESLModChecks.isShaders()) {
			if (capturedEntity instanceof Player player) {
				if (PaleWorldConfig.fogType == PaleWorldConfig.FogType.VANILLA) {
					if (PaleWorldConfig.gamemodeFog){
						if (!player.isCreative() && !player.isSpectator()){
							FogCode.setFogBuffer(renderBlocks, fog, fogAlphaBase, player);
						}
					} else {
						FogCode.setFogBuffer(renderBlocks, fog, fogAlphaBase, player);
					}
				} else if (PaleWorldConfig.fogType == PaleWorldConfig.FogType.SHADER) {
					//this type is intended for a future custom fog shader option so it shouldn't do anything RN
				}
			}
		}
	}
}
