package vesper.pw;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import vesper.pw.block.PaleWorldBlocks;

public class PaleWorldClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.PALE_VINE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.PALE_VINE_BODY, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.DYING_AZALEA, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PaleWorldBlocks.SMALL_DYING_DRIPLEAF, RenderLayer.getCutout());
	}
}