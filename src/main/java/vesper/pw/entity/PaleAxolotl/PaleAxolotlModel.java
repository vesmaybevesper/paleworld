package vesper.pw.entity.PaleAxolotl;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;

public class PaleAxolotlModel extends EntityModel<PaleAxolotlRenderState> {

    public static final EntityModelLayer PALE_AXOLOTL = new EntityModelLayer(Identifier.of(PaleWorld.MOD_ID, "pale_axolotl"), "main");

    private final ModelPart head;
    private final ModelPart bone10;
    private final ModelPart bone9;
    private final ModelPart bone8;
    private final ModelPart bone7;
    private final ModelPart bone6;
    private final ModelPart bone5;
    private final ModelPart bone4;
    private final ModelPart bone3;
    private final ModelPart bone2;
    private final ModelPart bone;
    private final ModelPart Tail;
    private final ModelPart Body;

    public PaleAxolotlModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.bone10 = root.getChild("bone10");
        this.bone9 = root.getChild("bone9");
        this.bone8 = root.getChild("bone8");
        this.bone7 = root.getChild("bone7");
        this.bone6 = root.getChild("bone6");
        this.bone5 = root.getChild("bone5");
        this.bone4 = root.getChild("bone4");
        this.bone3 = root.getChild("bone3");
        this.bone2 = root.getChild("bone2");
        this.bone = root.getChild("bone");
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

        ModelPartData bone6 = modelPartData.addChild("bone6", ModelPartBuilder.create().uv(12, 45).cuboid(1.0F, -2.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(11, 46).cuboid(0.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 24.0F, -4.0F));

        ModelPartData bone5 = modelPartData.addChild("bone5", ModelPartBuilder.create().uv(0, 44).cuboid(-3.1816F, -3.0F, -0.5277F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(1, 46).cuboid(-2.1816F, -1.0F, -0.5277F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 45).cuboid(-3.1816F, -2.0F, -0.5277F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 24.0F, -4.0F, 0.0F, -0.2182F, 0.0F));

        ModelPartData bone4 = modelPartData.addChild("bone4", ModelPartBuilder.create().uv(13, 40).cuboid(2.0F, -4.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(10, 42).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(12, 41).cuboid(1.0F, -3.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 21.0F, -4.0F));

        ModelPartData bone3 = modelPartData.addChild("bone3", ModelPartBuilder.create().uv(10, 37).cuboid(2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(8, 39).cuboid(0.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(9, 38).cuboid(1.0F, -2.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 19.0F, -4.0F));

        ModelPartData bone2 = modelPartData.addChild("bone2", ModelPartBuilder.create().uv(4, 39).cuboid(-2.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(3, 38).cuboid(-3.0F, -2.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(3, 37).cuboid(-3.0F, -3.0F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 19.0F, -4.0F));

        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(2, 42).cuboid(-1.0126F, -2.0F, 0.0157F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(1, 41).cuboid(-2.0126F, -3.0F, 0.0157F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 21.0F, -5.0F, 0.0F, -0.2182F, 0.0F));

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


    }


}