package dev.vesper.paleworld.mixin.render;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.Biomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BiomeSpecialEffects.class)
public class BiomeEffectsMixin {

	@Inject(method = "getFogColor", at = @At("HEAD"), cancellable = true)
	private void forceFogColor(CallbackInfoReturnable<Integer> cir){
		Level world =  Minecraft.getInstance().level;
		assert Minecraft.getInstance().player != null;
		BlockPos pos = Minecraft.getInstance().player.getOnPos();
		assert world != null;
		Holder<Biome> biome = world.getBiome(pos);

		if (biome.is(Biomes.PALE_GARDEN) || biome.is(PaleWorldBiomes.PALE_VALLEY)){
			cir.setReturnValue(0Xe3e4e6);
		}
	}
}
