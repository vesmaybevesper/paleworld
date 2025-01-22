package vesper.pw.entity.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;
import vesper.pw.entity.PaleAxolotl.PaleAxolotl;
import vesper.pw.entity.PaleAxolotl.PaleAxolotlModel;
import vesper.pw.entity.PaleAxolotl.PaleAxolotlRenderState;

public class PaleAxolotlRenderer extends MobEntityRenderer<PaleAxolotl, PaleAxolotlRenderState, PaleAxolotlModel> {
    public PaleAxolotlRenderer(EntityRendererFactory.Context context) {
        super(context, new PaleAxolotlModel(context.getPart(PaleAxolotlModel.PALE_AXOLOTL)), 0.5f);
    }

    @Override
    public PaleAxolotlRenderState createRenderState() {
        return new PaleAxolotlRenderState();
    }


    @Override
    public Identifier getTexture(PaleAxolotlRenderState state) {
        return Identifier.of(PaleWorld.MOD_ID, "textures/entity/pale_axolotl.png");
    }

    @Override
    public void updateRenderState(PaleAxolotl livingEntity, PaleAxolotlRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.variant = livingEntity.getVariant();
        livingEntityRenderState.playingDeadValue = livingEntity.playingDeadFf.getValue(f);
        livingEntityRenderState.inWaterValue = livingEntity.inWaterFf.getValue(f);
        livingEntityRenderState.onGroundValue = livingEntity.onGroundFf.getValue(f);
        livingEntityRenderState.isMovingValue = livingEntity.isMovingFf.getValue(f);
    }
}

