package vesper.pw;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import vesper.pw.block.PaleWorldBlocks;
import vesper.pw.client.render.particle.RafflesiaParticle;
import vesper.pw.entity.Entities;
import vesper.pw.entity.PaleAxolotl.PaleAxolotlModel;
import vesper.pw.entity.VampireBat.VampireBatModel;
import vesper.pw.entity.renderers.PaleAxolotlRenderer;
import vesper.pw.entity.renderers.VampireBatRenderer;
import vesper.pw.client.render.particle.FogParticle;
import vesper.pw.client.render.particle.MossParticle;
import vesper.pw.client.render.particle.ParticleTypes;

public class PaleWorldClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(PaleAxolotlModel.PALE_AXOLOTL, PaleAxolotlModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(VampireBatModel.VAMPIRE_BAT, VampireBatModel::getTexturedModelData);
		EntityRendererRegistry.register(Entities.PALE_AXOLOTL, PaleAxolotlRenderer::new);
		EntityRendererRegistry.register(Entities.VAMPIRE_BAT, VampireBatRenderer::new);
		PaleWorld.LOGGER.info("Client: Mob Renderers Registered");
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.PALE_VINE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.PALE_VINE_BODY, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.DYING_AZALEA, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.SMALL_DYING_DRIPLEAF, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.BIG_DYING_DRIPLEAF, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.BIG_DYING_DRIPLEAF_STEM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.CHRYSANTHEMUM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.ASPHODEL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.RAFFLESIA, RenderLayer.getCutout());
		PaleWorld.LOGGER.info("Client: Block Textures Registered");
		ParticleFactoryRegistry.getInstance().register(ParticleTypes.MOSS_PARTICLE, MossParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ParticleTypes.FOG_PARTICLE, FogParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ParticleTypes.RAFFLESIA_PARTICLE, RafflesiaParticle.Factory::new);
	}
}