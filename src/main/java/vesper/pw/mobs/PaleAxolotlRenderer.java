package vesper.pw.mobs;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PaleAxolotlRenderer extends GeoEntityRenderer<PaleAxolotl> {
    public PaleAxolotlRenderer(EntityRendererFactory.Context context, GeoModel<PaleAxolotl> model) {
        super(context, model);
    }
}
