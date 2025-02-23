package vesper.pw.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Fog;
import net.minecraft.client.render.FogShape;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vesper.pw.PaleWorldConfig;

import static vesper.pw.utils.FogStateManager.fogFade;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    private static final float FADE_SPEED = 0.002f;
    private static float fogStart;
    private static float fogEnd;

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
        } else {
            fogStart = (viewDistance * 0.8F) + fogFade * (0.5F - (viewDistance * 0.8F));
            fogEnd = (viewDistance) + fogFade * (20F - (viewDistance));
        }

        float fogRed = color.x + fogFade * (1 - color.x);
        float fogGreen = color.y + fogFade * (1 - color.y);
        float fogBlue = color.z + fogFade * (1 - color.z);
        float fogAlpha = color.w + fogFade * (1 - color.w);

        PALE_GARDEN_FOG = new Fog(fogStart, fogEnd, FogShape.SPHERE, fogRed, fogGreen, fogBlue, fogAlpha);
        cir.setReturnValue(PALE_GARDEN_FOG);
    }
}