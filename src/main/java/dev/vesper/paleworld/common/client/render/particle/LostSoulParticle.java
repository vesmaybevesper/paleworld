package dev.vesper.paleworld.common.client.render.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class LostSoulParticle extends SingleQuadParticle {
	protected LostSoulParticle(ClientLevel clientLevel, double d, double e, double f, TextureAtlasSprite textureAtlasSprite) {
		super(clientLevel, d, e, f, textureAtlasSprite);
	}

	@Override
	protected Layer getLayer() {
		return Layer.OPAQUE;
	}

	public static class Factory implements ParticleProvider<SimpleParticleType>{

		private final SpriteSet spriteSet;

		public Factory(SpriteSet spriteSet){
			this.spriteSet = spriteSet;
		}

		@Override
		public @Nullable Particle createParticle(SimpleParticleType particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, RandomSource randomSource) {
			return new LostSoulParticle(clientLevel, d,e,f, this.spriteSet.get(randomSource));
		}
	}
}
