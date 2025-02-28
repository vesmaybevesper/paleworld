/*
package vesper.pw.client.render.particle;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;

import java.awt.*;

public class FogParticle extends SpriteBillboardParticle {

    private final float width;
    private final float height;
    private final Color color;
    protected final SpriteProvider provider;
    private final float unit;
    protected FogParticle(ClientWorld clientWorld, double d, double e, double f) {
        super(clientWorld, d, e, f);
        gravityStrength = 0;
        maxAge = 18;
        this.width = width;
        this.height = height;
        this.provider = provider;
        setSpriteForAge(provider);

        color = new Color(Color.GRAY.getRGB());
        unit = 2f / MinecraftClient.getInstance().particleManager;
    }

    @Override
    public void tick() {
        super.tick();
        setSpriteForAge(provider);

    }


    @Override
    protected int getBrightness(float tint) {
        return super.getBrightness(tint);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }
}
*/
