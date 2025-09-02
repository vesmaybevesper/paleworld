package vesper.pw;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.BlockRenderLayer;
import vesper.pw.block.PaleWorldBlocks;
import vesper.pw.client.render.particle.*;
import vesper.pw.entity.Entities;
import vesper.pw.entity.PaleAxolotl.PaleAxolotlModel;
import vesper.pw.entity.VampireBat.VampireBatModel;
import vesper.pw.entity.renderers.LostSoulRenderer;
import vesper.pw.entity.renderers.PaleAxolotlRenderer;
import vesper.pw.entity.renderers.VampireBatRenderer;

public class PaleWorldClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(PaleAxolotlModel.PALE_AXOLOTL, PaleAxolotlModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(VampireBatModel.VAMPIRE_BAT, VampireBatModel::getTexturedModelData);
		EntityRendererRegistry.register(Entities.PALE_AXOLOTL, PaleAxolotlRenderer::new);
		EntityRendererRegistry.register(Entities.VAMPIRE_BAT, VampireBatRenderer::new);
		EntityRendererRegistry.register(Entities.LOST_SOUL, LostSoulRenderer::new);
		PaleWorld.LOGGER.info("Client: Mob Renderers Registered");
		BlockRenderLayerMap.putBlock(PaleWorldBlocks.PALE_VINE, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(PaleWorldBlocks.PALE_VINE_BODY, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(PaleWorldBlocks.DYING_AZALEA, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(PaleWorldBlocks.SMALL_DYING_DRIPLEAF, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(PaleWorldBlocks.BIG_DYING_DRIPLEAF, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(PaleWorldBlocks.BIG_DYING_DRIPLEAF_STEM, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(PaleWorldBlocks.CHRYSANTHEMUM, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(PaleWorldBlocks.ASPHODEL, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(PaleWorldBlocks.RAFFLESIA, BlockRenderLayer.CUTOUT);
		PaleWorld.LOGGER.info("Client: Block Textures Registered");
		ParticleFactoryRegistry.getInstance().register(ParticleTypes.MOSS_PARTICLE, MossParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ParticleTypes.FOG_PARTICLE, FogParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ParticleTypes.RAFFLESIA_PARTICLE, RafflesiaParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ParticleTypes.LOST_SOUL_AURA, LostSoulParticle.Factory::new);
        PaleWorld.LOGGER.info("Client: Particles Registered");
	}
}