package vesper.pw.entity.renderers;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.cache.texture.AutoGlowingTexture;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
import vesper.pw.PaleWorld;
import vesper.pw.entity.PaleAxolotl.PaleAxolotl;
import vesper.pw.entity.PaleAxolotl.PaleAxolotlModel;

public class PaleAxolotlRenderer extends GeoEntityRenderer<PaleAxolotl>  {
    public PaleAxolotlRenderer(EntityRendererFactory.Context context) {
        super(context, new PaleAxolotlModel());
        /*addRenderLayer(new AutoGlowingGeoLayer<>(this,
                entity -> getGeoModel().getTextureResource(PaleAxolotl),
                entity -> getGeoModel().getModelResource(PaleAxolotl)));*/
    }

    @Override
    public Identifier getTextureLocation(PaleAxolotl animatable) {
        return Identifier.of(PaleWorld.MOD_ID, "textures/entity/pale_axolotl.png");
    }

    @Override
    public void render(EntityRenderState entityRenderState, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        super.render(entityRenderState, poseStack, bufferSource, packedLight);
    }
}

