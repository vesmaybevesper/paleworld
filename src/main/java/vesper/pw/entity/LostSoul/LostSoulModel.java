package vesper.pw.entity.LostSoul;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import vesper.pw.PaleWorld;

public class LostSoulModel extends DefaultedEntityGeoModel<LostSoul> {
        private final Identifier model = Identifier.tryParse(PaleWorld.MOD_ID, "lost_soul.geo.json");
        private final Identifier animations = Identifier.tryParse(PaleWorld.MOD_ID, "lost_soul.animation.json");
        private final Identifier texture = Identifier.tryParse(PaleWorld.MOD_ID, "textures/entity/lost_soul.png");

    public LostSoulModel() {
        super(Identifier.of(PaleWorld.MOD_ID, "lost_soul"), "bb_main");
    }

    @Override
    public Identifier getModelResource(GeoRenderState geoRenderState) {
        return this.model;
    }

    @Override
    public Identifier getTextureResource(GeoRenderState geoRenderState) {
        return this.texture;
    }
}
