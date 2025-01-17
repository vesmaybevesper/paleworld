package vesper.pw.entity.PaleAxolotl;


import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoRenderer;
import vesper.pw.PaleWorld;


public class PaleAxolotlModel extends GeoModel<PaleAxolotl> {


    @Override
    public Identifier getModelResource(PaleAxolotl paleAxolotl, @Nullable GeoRenderer<PaleAxolotl> geoRenderer) {
        return Identifier.of(PaleWorld.MOD_ID, "geo/pale_axolotl.geo.json");
    }

    @Override
    public Identifier getTextureResource(PaleAxolotl paleAxolotl, @Nullable GeoRenderer<PaleAxolotl> geoRenderer) {
        return Identifier.of(PaleWorld.MOD_ID, "textures/entity/pale_axolotl.png");
    }

    @Override
    public Identifier getAnimationResource(PaleAxolotl paleAxolotl) {
        return null;
    }

    @Override
    public void setCustomAnimations(PaleAxolotl animatable, long instanceId, AnimationState<PaleAxolotl> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null){
            EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityModelData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityModelData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
