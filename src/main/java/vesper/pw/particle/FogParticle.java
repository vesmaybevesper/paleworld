package vesper.pw.particle;

//import io.wispforest.owo.ui.core.Color;
import net.minecraft.client.particle.AscendingParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;

public class FogParticle extends AscendingParticle {
    protected FogParticle(ClientWorld world, double x, double y, double z, float randomVelocityXMultiplier, float randomVelocityYMultiplier, float randomVelocityZMultiplier, double velocityX, double velocityY, double velocityZ, float scaleMultiplier, SpriteProvider spriteProvider, float colorMultiplier, int baseMaxAge, float gravityStrength, boolean collidesWithWorld) {
        super(world, x, y, z, randomVelocityXMultiplier, randomVelocityYMultiplier, randomVelocityZMultiplier, velocityX, velocityY, velocityZ, scaleMultiplier, spriteProvider, colorMultiplier, baseMaxAge, gravityStrength, collidesWithWorld);
        /*Color color = Color.ofArgb(0xa6a5b0);
        red = color.red();
        green = color.green();
        blue = color.blue();*/
        
    }

    @Override
    public void tick() {
        super.tick();
    }


}
