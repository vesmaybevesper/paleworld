package vesper.pw.entity.PaleAxolotl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.passive.AxolotlEntity;

@Environment(EnvType.CLIENT)
public class PaleAxolotlRenderState extends LivingEntityRenderState {

    public  AxolotlEntity.Variant variant;
    public float playingDeadValue;
    public float isMovingValue;
    public float inWaterValue;
    public float onGroundValue;

    public PaleAxolotlRenderState() {
        this.variant = AxolotlEntity.Variant.WILD;
        this.inWaterValue = 1.0F;
    }
}
