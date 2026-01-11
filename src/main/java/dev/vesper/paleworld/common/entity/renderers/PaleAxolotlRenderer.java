package dev.vesper.paleworld.common.entity.renderers;

import dev.vesper.paleworld.common.entity.PaleAxolotl.PaleAxolotl;
import dev.vesper.paleworld.common.entity.PaleAxolotl.PaleAxolotlModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
//? <1.21.11{
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
//?}
//? 1.21.11{
/*import software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer;
*///?}

//? >=1.21.6{
public class PaleAxolotlRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<PaleAxolotl, R> {
	//?}
	//? 1.21.4{
	/*public class PaleAxolotlRenderer extends GeoEntityRenderer<PaleAxolotl> {
	*///?}
	public PaleAxolotlRenderer(EntityRendererProvider.Context context) {
		super(context, new PaleAxolotlModel(context.bakeLayer(PaleAxolotlModel.PALE_AXOLOTL)));
		//? >= 1.21.9{
		withRenderLayer(new AutoGlowingGeoLayer<>(this));
		//?}
		//? <=1.21.8{
		/*addRenderLayer(new AutoGlowingGeoLayer<>(this));
		*///?}
	}
}
