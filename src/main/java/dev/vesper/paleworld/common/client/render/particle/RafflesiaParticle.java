package dev.vesper.paleworld.common.client.render.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BaseAshSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class RafflesiaParticle extends BaseAshSmokeParticle {
	protected RafflesiaParticle(ClientLevel clientLevel, double d, double e, double f, double j, double k, double l, SpriteSet spriteSet) {
		super(clientLevel, d, e, f, 1, 1, 1, j, k, l, 1, spriteSet, 1f, 100, 0.025f, true);
	}

	public static class Factory implements ParticleProvider<SimpleParticleType>{

		private final SpriteSet spriteSet;

		public Factory(SpriteSet spriteSet){
			this.spriteSet = spriteSet;
		}

		@Override
		public @Nullable Particle createParticle(SimpleParticleType particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, RandomSource randomSource) {
			double velX = (double) randomSource.nextFloat() * -1.9 * (double) randomSource.nextFloat() * 0.1;
			double velY = (double) randomSource.nextFloat() * -0.5 * (double) randomSource.nextFloat() * 0.1 * (double) 0.5F;
			double velZ = (double) randomSource.nextFloat() * -1.9 * (double) randomSource.nextFloat() * 0.1;
			return new RafflesiaParticle(clientLevel, d, e, f, velX, velY, velZ, this.spriteSet);
		}
	}
}
