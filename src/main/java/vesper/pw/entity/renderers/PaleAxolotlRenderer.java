package vesper.pw.entity.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
import vesper.pw.entity.PaleAxolotl.PaleAxolotl;
import vesper.pw.entity.PaleAxolotl.PaleAxolotlModel;

public class PaleAxolotlRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<PaleAxolotl, R> {
    public PaleAxolotlRenderer(EntityRendererFactory.Context context) {
        super(context, new PaleAxolotlModel(context.getPart(PaleAxolotlModel.PALE_AXOLOTL)));
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}

