package vesper.pw.entity.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
import vesper.pw.entity.VampireBat.VampireBat;
import vesper.pw.entity.VampireBat.VampireBatModel;

public class VampireBatRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<VampireBat, R> {
    public VampireBatRenderer(EntityRendererFactory.Context context) {
        super(context, new VampireBatModel(context.getPart(VampireBatModel.VAMPIRE_BAT)));
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}
