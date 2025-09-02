package vesper.pw.client.render.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.AscendingParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;
import net.minecraft.util.math.random.Random;;

public class RafflesiaParticle extends AscendingParticle {
    protected RafflesiaParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, float scaleMultiplier, SpriteProvider spriteProvider) {
        super(world, x, y, z, 1, 1, 1, velocityX, velocityY, velocityZ, 1, spriteProvider, 1F, 100, 0.025F, true);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType>{
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider){this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            Random random1 = world.random;
            double velX = (double) random1.nextFloat() * -1.9 * (double) random1.nextFloat() * 0.1;
            double velY = (double) random1.nextFloat() * -0.5 * (double) random1.nextFloat() * 0.1 * (double) 0.5F;
            double velZ = (double) random1.nextFloat() * -1.9 * (double) random1.nextFloat() * 0.1;
            return new RafflesiaParticle(world, x, y, z, velX, velY, velZ, 1.0f, this.spriteProvider);
        }
    }
}
