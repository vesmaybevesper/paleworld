package vesper.pw.client.render.particle;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.joml.Math;

public abstract class BaseParticle extends SpriteBillboardParticle {
    protected BlockPos.Mutable pos;
    int particleCount = 0;


    protected BaseParticle(ClientWorld clientWorld, double d, double e, double f) {
        super(clientWorld, d, e, f);
        this.setBoundingBoxSpacing(1, 1);
        this.maxAge = 1000;
        this.alpha = 0.0F;
        this.pos = new BlockPos.Mutable(d, e, f);
        particleCount++;
    }

    @Override
    public void tick() {
        super.tick();
        this.pos.set(this.x, this.y - 0.2f, this.z);
        this.cull();

    }

    @Override
    public void markDead() {
        if (this.isAlive()) {particleCount--;}
        super.markDead();
    }

    void cull() {
        Entity camera = MinecraftClient.getInstance().getCameraEntity();
        if (camera == null || camera.squaredDistanceTo(this.x, this.y, this.z) > Math.sqrt(10)) {
            markDead();
        }
    }
}
