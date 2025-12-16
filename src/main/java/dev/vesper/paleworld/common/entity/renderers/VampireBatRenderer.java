package dev.vesper.paleworld.common.entity.renderers;

import dev.vesper.paleworld.common.entity.VampireBat.VampireBat;
import dev.vesper.paleworld.common.entity.VampireBat.VampireBatModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class VampireBatRenderer <R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<VampireBat, R> {
	public VampireBatRenderer(EntityRendererProvider.Context context) {
		super(context, new VampireBatModel(context.bakeLayer(VampireBatModel.VAMPIRE_BAT)));
		withRenderLayer(new AutoGlowingGeoLayer<>(this));
	}
}
