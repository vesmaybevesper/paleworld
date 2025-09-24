package vesper.pw.client.render.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class MossParticle extends AscendingParticle {

    public MossParticle(ClientWorld world, double x, double y, double z,  double velocityX, double velocityY, double velocityZ, float scaleMultiplier, SpriteProvider spriteProvider) {
        super(world, x, y, z, 0.1f, -0.1f, 0.1f, velocityX, velocityY, velocityZ, scaleMultiplier, spriteProvider, 1000000000000000f, 100, 0.025F, true);
    }



    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType>{
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider){
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            Random random1 = world.random;
            double velX = (double) random1.nextFloat() * -1.9 * (double) random1.nextFloat() * 0.1;
            double velY = (double) random1.nextFloat() * (double) -0.5F * (double) random1.nextFloat() * 0.1 * (double) 0.5F;
            double velZ = (double) random1.nextFloat() * -1.9 * (double) random1.nextFloat() * 0.1;
            return new MossParticle(world, x,y,z, velX, velY, velZ, 1.0f, this.spriteProvider);
        }
    }
}
