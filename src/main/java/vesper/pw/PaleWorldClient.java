package vesper.pw;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import vesper.pw.block.PaleWorldBlocks;
import vesper.pw.entity.Entities;
import vesper.pw.entity.renderers.PaleAxolotlRenderer;

public class PaleWorldClient implements ClientModInitializer {
	/*public static void registerRenderers(BiConsumer<EntityType<? extends Entity>, EntityRenderer> entityRenderer, BiConsumer<BlockEntityType<? extends BlockEntity>, BlockEntityRenderer> blockEntityRenderer){
		entityRenderer.accept(EntityRegistry.PALE_AXOLOTL.get(), PaleAxolotlRenderer::new);
	}*/

	@Override
	public void onInitializeClient() {
		//registerRenderers(EntityRendererRegistry::register, BlockEntityRenderers::register);
		EntityRendererRegistry.register(Entities.PALE_AXOLOTL, PaleAxolotlRenderer::new);
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.PALE_VINE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.PALE_VINE_BODY, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.DYING_AZALEA, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.SMALL_DYING_DRIPLEAF, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.BIG_DYING_DRIPLEAF, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.BIG_DYING_DRIPLEAF_STEM, RenderLayer.getCutout());
	}
}