package dev.vesper.paleworld.common.entity.PaleAxolotl;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import static dev.vesper.paleworld.PaleWorld.MOD_ID;

public class PaleAxolotlModel extends GeoModel<PaleAxolotl> {

	public static final ModelLayerLocation PALE_AXOLOTL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MOD_ID, "pale_axolotl"), "main");

	public static final float MOVING_IN_WATER_LEG_PITCH = 1.8849558F;
	public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.5F);
	private final ModelPart tail;
	private final ModelPart leftHindLeg;
	private final ModelPart rightHindLeg;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart topGills;
	private final ModelPart leftGills;
	private final ModelPart rightGills;

	public PaleAxolotlModel(ModelPart root) {
		super();
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.rightHindLeg = this.body.getChild("right_hind_leg");
		this.leftHindLeg = this.body.getChild("left_hind_leg");
		this.rightFrontLeg = this.body.getChild("right_front_leg");
		this.leftFrontLeg = this.body.getChild("left_front_leg");
		this.tail = this.body.getChild("tail");
		this.topGills = this.head.getChild("top_gills");
		this.leftGills = this.head.getChild("left_gills");
		this.rightGills = this.head.getChild("right_gills");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition modelPartData2 = modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 11).addBox(-4.0F, -2.0F, -9.0F, 8.0F, 4.0F, 10.0F).texOffs(2, 17).addBox(0.0F, -3.0F, -8.0F, 0.0F, 5.0F, 9.0F), PartPose.offset(0.0F, 20.0F, 5.0F));
		CubeDeformation dilation = new CubeDeformation(0.001F);
		PartDefinition modelPartData3 = modelPartData2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 1).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 5.0F, 5.0F, dilation), PartPose.offset(0.0F, 0.0F, -9.0F));
		CubeListBuilder modelPartBuilder = CubeListBuilder.create().texOffs(3, 37).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 3.0F, 0.0F, dilation);
		CubeListBuilder modelPartBuilder2 = CubeListBuilder.create().texOffs(0, 40).addBox(-3.0F, -5.0F, 0.0F, 3.0F, 7.0F, 0.0F, dilation);
		CubeListBuilder modelPartBuilder3 = CubeListBuilder.create().texOffs(11, 40).addBox(0.0F, -5.0F, 0.0F, 3.0F, 7.0F, 0.0F, dilation);
		modelPartData3.addOrReplaceChild("top_gills", modelPartBuilder, PartPose.offset(0.0F, -3.0F, -1.0F));
		modelPartData3.addOrReplaceChild("left_gills", modelPartBuilder2, PartPose.offset(-4.0F, 0.0F, -1.0F));
		modelPartData3.addOrReplaceChild("right_gills", modelPartBuilder3, PartPose.offset(4.0F, 0.0F, -1.0F));
		CubeListBuilder modelPartBuilder4 = CubeListBuilder.create().texOffs(2, 13).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, dilation);
		CubeListBuilder modelPartBuilder5 = CubeListBuilder.create().texOffs(2, 13).addBox(-2.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, dilation);
		modelPartData2.addOrReplaceChild("right_hind_leg", modelPartBuilder5, PartPose.offset(-3.5F, 1.0F, -1.0F));
		modelPartData2.addOrReplaceChild("left_hind_leg", modelPartBuilder4, PartPose.offset(3.5F, 1.0F, -1.0F));
		modelPartData2.addOrReplaceChild("right_front_leg", modelPartBuilder5, PartPose.offset(-3.5F, 1.0F, -8.0F));
		modelPartData2.addOrReplaceChild("left_front_leg", modelPartBuilder4, PartPose.offset(3.5F, 1.0F, -8.0F));
		modelPartData2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(2, 19).addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 12.0F), PartPose.offset(0.0F, 0.0F, 1.0F));
		return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	public ResourceLocation getModelResource(GeoRenderState renderState) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, "geckolib/models/pale_axolotl");
	}

	@Override
	public ResourceLocation getTextureResource(GeoRenderState renderState) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/entity/pale_axolotl.png");
	}

	@Override
	public ResourceLocation getAnimationResource(PaleAxolotl animatable) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, "geckolib/animations/pale_axolotl");
	}
}
