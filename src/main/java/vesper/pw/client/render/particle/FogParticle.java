package vesper.pw.client.render.particle;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class FogParticle extends BaseParticle {
    protected FogParticle(ClientWorld clientWorld, double d, double e, double f, SpriteProvider provider) {
        super(clientWorld, d, e, f);
        this.setSprite(provider.getSprite(clientWorld.random));
        this.maxAge = 125;
        assert MinecraftClient.getInstance().cameraEntity != null;
        final double distance = MinecraftClient.getInstance().cameraEntity.getPos().distanceTo(new Vec3d(x,y,z));

        Color color = new Color(0Xccccd9);

    }

    @Override
    public void tick() {
        super.tick();
        assert MinecraftClient.getInstance().cameraEntity != null;
        final double distance = MinecraftClient.getInstance().cameraEntity.getPos().distanceTo(new Vec3d(x,y,z));
        BlockPos pos = this.pos.offset(Direction.Axis.Y, 2);


        if (this.onGround){
            this.markDead();
        }
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        Vec3d cameraPos = camera.getPos();
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class FogFactory implements ParticleFactory<SimpleParticleType>{

        private SpriteProvider provider;

        public void ParticleFactory(SpriteProvider provider){
            this.provider = provider;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new FogParticle(world, x,y,z, this.provider);
        }
    }

}
