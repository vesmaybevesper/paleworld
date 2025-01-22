package vesper.pw.entity.PaleAxolotl;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import vesper.pw.PaleWorld;

public class PaleAxolotlModel extends EntityModel<PaleAxolotlRenderState> {

    public static final EntityModelLayer PALE_AXOLOTL = new EntityModelLayer(Identifier.of(PaleWorld.MOD_ID, "pale_axolotl"), "main");

    private final ModelPart head;
    private final ModelPart bone10;
    private final ModelPart bone9;
    private final ModelPart bone8;
    private final ModelPart bone7;
    private final ModelPart leftGills;
    private final ModelPart topGills;
    private final ModelPart rightGills;
    private final ModelPart Tail;
    private final ModelPart Body;

    public PaleAxolotlModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.bone10 = root.getChild("bone10");
        this.bone9 = root.getChild("bone9");
        this.bone8 = root.getChild("bone8");
        this.bone7 = root.getChild("bone7");
        this.leftGills = root.getChild("leftGills");
        this.topGills = root.getChild("topGills");
        this.rightGills = root.getChild("rightGills");
        this.Tail = root.getChild("Tail");
        this.Body = root.getChild("Body");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 1).cuboid(-4.0F, -3.0F, 0.0F, 8.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, -8.0F));

        ModelPartData bone10 = modelPartData.addChild("bone10", ModelPartBuilder.create(), ModelTransform.of(4.0F, 24.0F, 4.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r1 = bone10.addChild("cube_r1", ModelPartBuilder.create().uv(0, 16).cuboid(-7.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, -6.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r2 = bone10.addChild("cube_r2", ModelPartBuilder.create().uv(2, 13).cuboid(-7.0F, 0.0F, -1.0F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 6.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone9 = modelPartData.addChild("bone9", ModelPartBuilder.create(), ModelTransform.of(4.0F, 24.0F, -2.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r3 = bone9.addChild("cube_r3", ModelPartBuilder.create().uv(0, 16).cuboid(-7.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, -6.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r4 = bone9.addChild("cube_r4", ModelPartBuilder.create().uv(2, 13).cuboid(-7.0F, 0.0F, -1.0F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 6.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone8 = modelPartData.addChild("bone8", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 24.0F, 4.0F));

        ModelPartData cube_r5 = bone8.addChild("cube_r5", ModelPartBuilder.create().uv(0, 16).cuboid(-7.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, -5.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r6 = bone8.addChild("cube_r6", ModelPartBuilder.create().uv(2, 13).cuboid(-7.0F, 0.0F, -1.0F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 7.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone7 = modelPartData.addChild("bone7", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 24.0F, -1.0F));

        ModelPartData cube_r7 = bone7.addChild("cube_r7", ModelPartBuilder.create().uv(0, 16).cuboid(-7.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, -5.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r8 = bone7.addChild("cube_r8", ModelPartBuilder.create().uv(2, 13).cuboid(-7.0F, 0.0F, -1.0F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 7.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData leftGills = modelPartData.addChild("leftGills", ModelPartBuilder.create().uv(12, 41).cuboid(1.0F, -6.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(10, 42).cuboid(0.0F, -5.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(13, 40).cuboid(2.0F, -7.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(12, 45).cuboid(1.0F, -2.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(11, 46).cuboid(0.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 24.0F, -4.0F));

        ModelPartData topGills = modelPartData.addChild("topGills", ModelPartBuilder.create().uv(4, 39).cuboid(-4.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(3, 37).cuboid(-5.0F, -3.0F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(10, 37).cuboid(2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(8, 39).cuboid(0.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(9, 38).cuboid(1.0F, -2.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(3, 38).cuboid(-5.0F, -2.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 19.0F, -4.0F));

        ModelPartData rightGills = modelPartData.addChild("rightGills", ModelPartBuilder.create().uv(2, 42).cuboid(-1.0126F, -5.0F, -0.9843F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(1, 41).cuboid(-2.0126F, -6.0F, -0.9843F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 44).cuboid(-3.1816F, -3.0F, -0.5277F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(1, 46).cuboid(-2.1816F, -1.0F, -0.5277F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 45).cuboid(-3.1816F, -2.0F, -0.5277F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 24.0F, -4.0F, 0.0F, -0.2182F, 0.0F));

        ModelPartData Tail = modelPartData.addChild("Tail", ModelPartBuilder.create().uv(10, 27).cuboid(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 4.0F, new Dilation(0.0F))
                .uv(8, 29).cuboid(0.0F, -3.0F, 4.0F, 0.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(3, 29).cuboid(0.0F, -3.0F, 6.0F, 0.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(2, 30).cuboid(0.0F, -3.0F, 9.0F, 0.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(3, 30).cuboid(0.0F, -3.0F, 8.0F, 0.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, 7.0F));

        ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(0, 11).cuboid(-4.0F, -4.0F, -10.0F, 8.0F, 4.0F, 10.0F, new Dilation(0.0F))
                .uv(2, 21).cuboid(0.0F, -5.0F, -5.0F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F))
                .uv(6, 21).cuboid(0.0F, -5.0F, -10.0F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 7.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public void setAngles(PaleAxolotlRenderState paleAxolotlRenderState) {
        super.setAngles(paleAxolotlRenderState);
        float f = paleAxolotlRenderState.playingDeadValue;
        float g = paleAxolotlRenderState.inWaterValue;
        float h = paleAxolotlRenderState.onGroundValue;
        float i = paleAxolotlRenderState.isMovingValue;
        float j = 1.0F - i;
        float k = 1.0F - Math.min(h, i);
        Body.yaw += paleAxolotlRenderState.yawDegrees * ((float)Math.PI / 180F);
        this.setMovingInWaterAngles(paleAxolotlRenderState.age, paleAxolotlRenderState.pitch, Math.min(i, g));
        this.setStandingInWaterAngles(paleAxolotlRenderState.age, Math.min(j, g));
        this.setMovingOnGroundAngles(paleAxolotlRenderState.age, Math.min(i, h));
        this.setStandingOnGroundAngles(paleAxolotlRenderState.age, Math.min(j, h));
        this.setPlayingDeadAngles(f);
        this.copyLegAngles(k);
    }

    private void setMovingInWaterAngles(float f, float headPitch, float g) {
        if (!(g <= 1.0E-5F)) {
            float h = f * 0.33F;
            float i = MathHelper.sin(h);
            float j = MathHelper.cos(h);
            float k = 0.13F * i;
            ModelPart var10000 = this.Body;
            var10000.pitch += (headPitch * ((float)Math.PI / 180F) + k) * g;
            var10000 = this.head;
            var10000.pitch -= k * 1.8F * g;
            var10000 = this.Body;
            var10000.pivotY -= 0.45F * j * g;
            var10000 = this.topGills;
            var10000.pitch += (-0.5F * i - 0.8F) * g;
            float l = (0.3F * i + 0.9F) * g;
            var10000 = this.leftGills;
            var10000.yaw += l;
            var10000 = this.rightGills;
            var10000.yaw -= l;
            var10000 = this.Tail;
            var10000.yaw += 0.3F * MathHelper.cos(h * 0.9F) * g;
            var10000 = this.bone10;
            var10000.pitch += 1.8849558F * g;
            var10000 = this.bone10;
            var10000.yaw += -0.4F * i * g;
            var10000 = this.bone10;
            var10000.roll += ((float)Math.PI / 2F) * g;
            var10000 = this.bone9;
            var10000.pitch += 1.8849558F * g;
            var10000 = this.bone9;
            var10000.yaw += (-0.2F * j - 0.1F) * g;
            var10000 = this.bone9;
            var10000.roll += ((float)Math.PI / 2F) * g;
        }
    }

    private void setStandingInWaterAngles(float f, float g) {
        if (!(g <= 1.0E-5F)) {
            float h = f * 0.075F;
            float i = MathHelper.cos(h);
            float j = MathHelper.sin(h) * 0.15F;
            float k = (-0.15F + 0.075F * i) * g;
            ModelPart var10000 = this.Body;
            var10000.pitch += k;
            var10000 = this.Body;
            var10000.pivotY -= j * g;
            var10000 = this.head;
            var10000.pitch -= k;
            var10000 = this.topGills;
            var10000.pitch += 0.2F * i * g;
            float l = (-0.3F * i - 0.19F) * g;
            var10000 = this.leftGills;
            var10000.yaw += l;
            var10000 = this.rightGills;
            var10000.yaw -= l;
            var10000 = this.bone10;
            var10000.pitch += (2.3561945F - i * 0.11F) * g;
            var10000 = this.bone10;
            var10000.yaw += 0.47123894F * g;
            var10000 = this.bone10;
            var10000.roll += 1.7278761F * g;
            var10000 = this.bone9;
            var10000.pitch += (((float)Math.PI / 4F) - i * 0.2F) * g;
            var10000 = this.bone9;
            var10000.yaw += 2.042035F * g;
            var10000 = this.Tail;
            var10000.yaw += 0.5F * i * g;
        }
    }

    private void setMovingOnGroundAngles(float animationProgress, float headYaw) {
        if (!(headYaw <= 1.0E-5F)) {
            float f = animationProgress * 0.11F;
            float g = MathHelper.cos(f);
            float h = (g * g - 2.0F * g) / 5.0F;
            float i = 0.7F * g;
            float j = 0.09F * g * headYaw;
            ModelPart var10000 = this.head;
            var10000.yaw += j;
            var10000 = this.Tail;
            var10000.yaw += j;
            float k = (0.6F - 0.08F * (g * g + 2.0F * MathHelper.sin(f))) * headYaw;
            var10000 = this.topGills;
            var10000.pitch += k;
            var10000 = this.leftGills;
            var10000.yaw -= k;
            var10000 = this.rightGills;
            var10000.yaw += k;
            float l = 0.9424779F * headYaw;
            float m = 1.0995574F * headYaw;
            var10000 = this.bone10;
            var10000.pitch += l;
            var10000 = this.bone10;
            var10000.yaw += (1.5F - h) * headYaw;
            var10000 = this.bone10;
            var10000.roll += -0.1F * headYaw;
            var10000 = this.bone9;
            var10000.pitch += m;
            var10000 = this.bone9;
            var10000.yaw += (((float)Math.PI / 2F) - i) * headYaw;
            var10000 = this.bone9;
            var10000.pitch += l;
            var10000 = this.bone8;
            var10000.yaw += (-1.0F - h) * headYaw;
            var10000 = this.bone7;
            var10000.pitch += m;
            var10000 = this.bone7;
            var10000.yaw += ((-(float)Math.PI / 2F) - i) * headYaw;
        }
    }

    private void setStandingOnGroundAngles(float animationProgress, float headYaw) {
        if (!(headYaw <= 1.0E-5F)) {
            float f = animationProgress * 0.09F;
            float g = MathHelper.sin(f);
            float h = MathHelper.cos(f);
            float i = g * g - 2.0F * g;
            float j = h * h - 3.0F * g;
            ModelPart var10000 = this.head;
            var10000.pitch += -0.09F * i * headYaw;
            var10000 = this.head;
            var10000.roll += -0.2F * headYaw;
            var10000 = this.Tail;
            var10000.yaw += (-0.1F + 0.1F * i) * headYaw;
            float k = (0.6F + 0.05F * j) * headYaw;
            var10000 = this.topGills;
            var10000.pitch += k;
            var10000 = this.leftGills;
            var10000.yaw -= k;
            var10000 = this.rightGills;
            var10000.yaw += k;
            var10000 = this.bone10;
            var10000.pitch += 1.1F * headYaw;
            var10000 = this.bone10;
            var10000.yaw += 1.0F * headYaw;
            var10000 = this.bone9;
            var10000.pitch += 0.8F * headYaw;
            var10000 = this.bone9;
            var10000.yaw += 2.3F * headYaw;
            var10000 = this.bone9;
            var10000.roll -= 0.5F * headYaw;
        }
    }

    private void setPlayingDeadAngles(float headYaw) {
        if (!(headYaw <= 1.0E-5F)) {
            ModelPart var10000 = this.bone10;
            var10000.pitch += 1.4137167F * headYaw;
            var10000 = this.bone10;
            var10000.yaw += 1.0995574F * headYaw;
            var10000 = this.bone10;
            var10000.roll += ((float)Math.PI / 4F) * headYaw;
            var10000 = this.bone9;
            var10000.pitch += ((float)Math.PI / 4F) * headYaw;
            var10000 = this.bone9;
            var10000.yaw += 2.042035F * headYaw;
            var10000 = this.Body;
            var10000.pitch += -0.15F * headYaw;
            var10000 = this.Body;
            var10000.roll += 0.35F * headYaw;
        }
    }

    private void copyLegAngles(float f) {
        if (!(f <= 1.0E-5F)) {
            ModelPart var10000 = this.bone8;
            var10000.pitch += this.bone10.pitch * f;
            ModelPart var2 = this.bone8;
            var2.yaw += -this.bone10.yaw * f;
            var2 = this.bone8;
            var2.roll += -this.bone10.roll * f;
            var10000 = this.bone7;
            var10000.pitch += this.bone9.pitch * f;
            var2 = this.bone7;
            var2.yaw += -this.bone9.yaw * f;
            var2 = this.bone7;
            var2.roll += -this.bone9.roll * f;
        }
    }


}