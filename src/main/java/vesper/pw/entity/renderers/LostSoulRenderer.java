package vesper.pw.entity.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
import vesper.pw.entity.LostSoul.LostSoul;
import vesper.pw.entity.LostSoul.LostSoulModel;

public class LostSoulRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<LostSoul, R> {
    public LostSoulRenderer(EntityRendererFactory.Context context) {
        super(context, new LostSoulModel());
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}
