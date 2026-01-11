package dev.vesper.paleworld.common.entity.renderers;

import dev.vesper.paleworld.common.entity.VampireBat.VampireBat;
import dev.vesper.paleworld.common.entity.VampireBat.VampireBatModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
//?<1.21.11{
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
//?}
//? 1.21.11{
/*import software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer;
*///?}



//? >=1.21.6{
public class VampireBatRenderer <R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<VampireBat, R> {
	//?}
	//? 1.21.4{
	/*public class VampireBatRenderer extends GeoEntityRenderer<VampireBat> {
	*///?}
	public VampireBatRenderer(EntityRendererProvider.Context context) {
		super(context, new VampireBatModel(context.bakeLayer(VampireBatModel.VAMPIRE_BAT)));
		//? >= 1.21.9{
		withRenderLayer(new AutoGlowingGeoLayer<>(this));
		//?}
		//? <=1.21.8{
		/*addRenderLayer(new AutoGlowingGeoLayer<>(this));
		*///?}
	}
}
