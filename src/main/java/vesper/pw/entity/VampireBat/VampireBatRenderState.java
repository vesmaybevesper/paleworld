package vesper.pw.entity.VampireBat;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

@Environment(EnvType.CLIENT)
public class VampireBatRenderState extends LivingEntityRenderState {
    public boolean roosting;
    public float wingFlapProgress;
    public final AnimationState flyingAnimationState = new AnimationState();
    public final AnimationState roostingAnimationState = new AnimationState();

    public VampireBatRenderState(){

    }

}
