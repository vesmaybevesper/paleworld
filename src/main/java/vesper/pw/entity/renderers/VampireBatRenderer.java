package vesper.pw.entity.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import vesper.pw.PaleWorld;
import vesper.pw.entity.VampireBat.VampireBat;
import vesper.pw.entity.VampireBat.VampireBatModel;
import vesper.pw.entity.VampireBat.VampireBatRenderState;

public class VampireBatRenderer extends MobEntityRenderer<VampireBat, VampireBatRenderState, VampireBatModel> {
    public VampireBatRenderer(EntityRendererFactory.Context context) {
        super(context, new VampireBatModel(context.getPart(VampireBatModel.VAMPIRE_BAT)), 0.25F);
    }

    @Override
    public VampireBatRenderState createRenderState() {
        return new VampireBatRenderState();
    }

    @Override
    public Identifier getTexture(VampireBatRenderState state) {
        return Identifier.of(PaleWorld.MOD_ID, "textures/entity/vampire_bat.png");
    }


    @Override
    public void updateRenderState(VampireBat livingEntity, VampireBatRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.wingFlapProgress = (float) livingEntity.getWingFlapTickOffset() + livingEntityRenderState.age;
        livingEntityRenderState.flyingAnimationState.copyFrom(livingEntityRenderState.flyingAnimationState);
        livingEntityRenderState.roostingAnimationState.copyFrom(livingEntityRenderState.roostingAnimationState);
        livingEntityRenderState.roosting = livingEntity.isRoosting();
    }

    @Override
    protected void setupTransforms(VampireBatRenderState state, MatrixStack matrices, float bodyYaw, float baseHeight) {
        super.setupTransforms(state, matrices, bodyYaw, baseHeight);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(state.pitch));
    }
}
