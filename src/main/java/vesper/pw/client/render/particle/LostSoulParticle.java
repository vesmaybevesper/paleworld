package vesper.pw.client.render.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class LostSoulParticle extends BillboardParticle {

    protected LostSoulParticle(ClientWorld clientWorld, double d, double e, double f, Sprite sprite) {
        super(clientWorld, d, e, f, sprite);
    }

    @Override
    protected RenderType getRenderType() {
        return RenderType.PARTICLE_ATLAS_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType>{

        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider){
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Random random) {
            return new LostSoulParticle(world, x, y, z, this.spriteProvider.getSprite(random));
        }
    }
}
