package dev.vesper.paleworld.common.entity.renderers;

import dev.vesper.paleworld.common.entity.PaleAxolotl.PaleAxolotl;
import dev.vesper.paleworld.common.entity.PaleAxolotl.PaleAxolotlModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class PaleAxolotlRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<PaleAxolotl, R> {
	public PaleAxolotlRenderer(EntityRendererProvider.Context context) {
		super(context, new PaleAxolotlModel(context.bakeLayer(PaleAxolotlModel.PALE_AXOLOTL)));
		withRenderLayer(new AutoGlowingGeoLayer<>(this));
	}
}
