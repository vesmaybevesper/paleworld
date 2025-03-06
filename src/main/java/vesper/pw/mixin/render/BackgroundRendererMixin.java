package vesper.pw.mixin.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.world.ClientWorld;
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
import vesper.pw.PaleWorldConfig;
import static vesper.pw.utils.FogStateManager.fogFade;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @Unique
    private static final float FADE_SPEED = 0.002f;
    @Unique
    private static float fogStart;
    @Unique
    private static float fogEnd;
    @Unique
    private static float fogAlphaBase;
    @Unique
    private static FogShape fogShape;

    @Inject(method = "applyFog", at = @At("TAIL"), cancellable = true)
    private static void modifyFogSettings(Camera camera, BackgroundRenderer.FogType fogType, Vector4f color, float viewDistance, boolean thickenFog, float tickDelta, CallbackInfoReturnable<Fog> cir) {
        Fog PALE_GARDEN_FOG;

        assert MinecraftClient.getInstance().player != null;
        ClientWorld world = MinecraftClient.getInstance().world;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (world == null || camera == null) {
            return;
        }
        BlockPos pos = MinecraftClient.getInstance().player.getBlockPos();
        RegistryEntry<Biome> biome = world.getBiome(pos);

        if (biome.matchesKey(BiomeKeys.PALE_GARDEN) && player.getY() <= (world.getTopY(Heightmap.Type.WORLD_SURFACE, pos.getX(), pos.getZ())) + 15
                && !(player.getY() < (world.getTopY(Heightmap.Type.WORLD_SURFACE, pos.getX(), pos.getZ())) - 15)
                && !player.isCreative() && !player.isSpectator()) {
            fogFade = Math.min(fogFade + FADE_SPEED, 1.0F);
        } else if (fogFade > 0) {
            fogFade = Math.max(fogFade - FADE_SPEED, 0);
        } else {
            return;
        }

        PaleWorldConfig.horrorMode = PaleWorldConfig.horrorModeSelect == PaleWorldConfig.horrorVals.TRUE;

        if (PaleWorldConfig.horrorMode) {
            fogStart = (viewDistance * 0.8F) + fogFade * (0.1F - (viewDistance * 0.8F));
            fogEnd = (viewDistance) + fogFade * (8F - (viewDistance));
            fogAlphaBase = 0.99F;
            fogShape = FogShape.SPHERE;
        } else {
            fogStart = (viewDistance * 0.8F) + fogFade * (PaleWorldConfig.fogStart - (viewDistance * 0.8F));
            fogEnd = (viewDistance) + fogFade * (PaleWorldConfig.fogEnd - (viewDistance));
            fogAlphaBase = PaleWorldConfig.fogTransparency;
            fogShape = FogShape.CYLINDER;
        }

        float fogRed = color.x + fogFade * (0.8F - color.x);
        float fogGreen = color.y + fogFade * (0.8F - color.y);
        float fogBlue = color.z + fogFade * (0.85F - color.z);
        float fogAlpha = color.w + fogFade * (fogAlphaBase - color.w);

        PALE_GARDEN_FOG = new Fog(fogStart, fogEnd, fogShape, fogRed, fogGreen, fogBlue, fogAlpha);
        cir.setReturnValue(PALE_GARDEN_FOG);

    }

}