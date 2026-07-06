package dev.vesper.paleworld.common.entity.renderers;

import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer;
import dev.vesper.paleworld.common.entity.PaleAxolotl.PaleAxolotl;
import dev.vesper.paleworld.common.entity.PaleAxolotl.PaleAxolotlModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.jspecify.annotations.NonNull;

public class PaleAxolotlRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<PaleAxolotl, @NonNull R> {
	public PaleAxolotlRenderer(EntityRendererProvider.Context context) {
		super(context, new PaleAxolotlModel(context.bakeLayer(PaleAxolotlModel.PALE_AXOLOTL)));
		withRenderLayer(new AutoGlowingGeoLayer<>(this));
	}
}
