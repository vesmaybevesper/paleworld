package vesper.pw.mixin.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vesper.pw.biomes.PaleWorldBiomes;

@Mixin(BiomeEffects.class)
public class BiomeEffectsMixin {


	@Inject(at = @At("HEAD"), method = "getFogColor", cancellable = true)
	private void init(CallbackInfoReturnable<Integer> cir) {
        assert MinecraftClient.getInstance().player != null;
		ClientWorld world =  MinecraftClient.getInstance().world;
        BlockPos pos = MinecraftClient.getInstance().player.getBlockPos();
        assert world != null;
        RegistryEntry<Biome> biome = world.getBiome(pos);
		if (biome.matchesKey(BiomeKeys.PALE_GARDEN) || biome.matchesKey(PaleWorldBiomes.PALE_VALLEY)){
			cir.setReturnValue(0Xe3e4e6);
		}

	}
}