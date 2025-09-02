package vesper.pw.entity.VampireBat;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.BatAnimations;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.DefaultedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
import vesper.pw.PaleWorld;

public class VampireBatModel extends GeoModel<VampireBat> {

    public static final EntityModelLayer VAMPIRE_BAT = new EntityModelLayer(Identifier.of(PaleWorld.MOD_ID, ""), "vampire_bat");

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart rightWingTip;
    private final ModelPart leftWingTip;
    private final ModelPart feet;
    private final Animation flyinganimation;
    private final Animation roostanimation;

    public VampireBatModel(ModelPart modelPart) {
        super();
        this.body = modelPart.getChild("body");
        this.head = modelPart.getChild("head");
        this.rightWing = this.body.getChild("right_wing");
        this.rightWingTip = this.rightWing.getChild("right_wing_tip");
        this.leftWing = this.body.getChild("left_wing");
        this.leftWingTip = this.leftWing.getChild("left_wing_tip");
        this.feet = this.body.getChild("feet");
        this.flyinganimation = VampireBatAnimations.FLYING.createAnimation(modelPart);
        this.roostanimation = VampireBatAnimations.ROOSTING.createAnimation(modelPart);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData2 = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F), ModelTransform.origin(0.0F, 17.0F, 0.0F));
        ModelPartData modelPartData3 = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 7).cuboid(-2.0F, -3.0F, -1.0F, 4.0F, 3.0F, 2.0F), ModelTransform.origin(0.0F, 17.0F, 0.0F));
        modelPartData3.addChild("right_ear", ModelPartBuilder.create().uv(1, 15).cuboid(-2.5F, -4.0F, 0.0F, 3.0F, 5.0F, 0.0F), ModelTransform.origin(-1.5F, -2.0F, 0.0F));
        modelPartData3.addChild("left_ear", ModelPartBuilder.create().uv(8, 15).cuboid(-0.1F, -3.0F, 0.0F, 3.0F, 5.0F, 0.0F), ModelTransform.origin(1.1F, -3.0F, 0.0F));
        ModelPartData modelPartData4 = modelPartData2.addChild("right_wing", ModelPartBuilder.create().uv(12, 0).cuboid(-2.0F, -2.0F, 0.0F, 2.0F, 7.0F, 0.0F), ModelTransform.origin(-1.5F, 0.0F, 0.0F));
        modelPartData4.addChild("right_wing_tip", ModelPartBuilder.create().uv(16, 0).cuboid(-6.0F, -2.0F, 0.0F, 6.0F, 8.0F, 0.0F), ModelTransform.origin(-2.0F, 0.0F, 0.0F));
        ModelPartData modelPartData5 = modelPartData2.addChild("left_wing", ModelPartBuilder.create().uv(12, 7).cuboid(0.0F, -2.0F, 0.0F, 2.0F, 7.0F, 0.0F), ModelTransform.origin(1.5F, 0.0F, 0.0F));
        modelPartData5.addChild("left_wing_tip", ModelPartBuilder.create().uv(16, 8).cuboid(0.0F, -2.0F, 0.0F, 6.0F, 8.0F, 0.0F), ModelTransform.origin(2.0F, 0.0F, 0.0F));
        modelPartData2.addChild("feet", ModelPartBuilder.create().uv(16, 16).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F), ModelTransform.origin(0.0F, 5.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.of(PaleWorld.MOD_ID, "geckolib/models/vampire_bat");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.of(PaleWorld.MOD_ID, "textures/entity/vampire_bat.png");
    }


    @Override
    public Identifier getAnimationResource(VampireBat animatable) {
        return Identifier.of(PaleWorld.MOD_ID, "geckolib/animations/vampire_bat");
    }

    /*public void setAngles(VampireBatRenderState<> entity) {
        super.setAngles(entity);
        float f = entity.wingFlapProgress * 7.448451F * ((float)Math.PI / 10F);
        this.leftWingTip.yaw = MathHelper.cos(f) * 16.0F * ((float)Math.PI / 80F);
        this.rightWingTip.yaw = -this.leftWingTip.yaw;
        this.leftWing.yaw = MathHelper.cos(f) * 16.0F * ((float)Math.PI / 80F);
        this.rightWing.yaw = -this.leftWing.yaw;
        this.feet.pitch = -(5.0F - MathHelper.cos(f * 2.0F) * 5.0F) * ((float)Math.PI / 180F);
        this.body.pitch = MathHelper.cos(-100) * 16.0F * ((float)Math.PI / 100F);
        this.head.pitch = MathHelper.cos(0.0005F*f) * 5.0F * ((float)Math.PI / 100F);
        if (entity.roosting) {
            this.setRoostingHeadAngles(entity.relativeHeadYaw);
        }
        this.flyinganimation.apply(VampireBatRenderState.flyingAnimationState, entity.age, 1.0F);
        this.roostanimation.apply(VampireBatRenderState.roostingAnimationState, entity.age, 1.0F);
    }
    private void setRoostingHeadAngles(float yaw) {
        this.head.yaw = yaw * ((float)Math.PI / 180F);
    }*/
}


