package dev.vesper.paleworld.common.entity.PaleAxolotl;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.animal.axolotl.Axolotl;

public class PaleAxolotlRenderState extends LivingEntityRenderState {
	public  Axolotl.Variant variant;
	public float playingDeadValue;
	public float isMovingValue;
	public float inWaterValue;
	public float onGroundValue;

	public PaleAxolotlRenderState() {
		this.variant = Axolotl.Variant.WILD;
		this.inWaterValue = 1.0F;
	}
}
