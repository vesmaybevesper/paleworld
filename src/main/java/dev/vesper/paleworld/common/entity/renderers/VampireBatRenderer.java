package dev.vesper.paleworld.common.entity.renderers;

import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer;
import dev.vesper.paleworld.common.entity.VampireBat.VampireBat;
import dev.vesper.paleworld.common.entity.VampireBat.VampireBatModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.jspecify.annotations.NonNull;

public class VampireBatRenderer <R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<VampireBat, @NonNull R> {
	public VampireBatRenderer(EntityRendererProvider.Context context) {
		super(context, new VampireBatModel(context.bakeLayer(VampireBatModel.VAMPIRE_BAT)));
		withRenderLayer(new AutoGlowingGeoLayer<>(this));
	}
}
