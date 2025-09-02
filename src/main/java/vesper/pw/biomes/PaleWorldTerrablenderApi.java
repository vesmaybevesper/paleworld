package vesper.pw.biomes;

import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;
import vesper.pw.biomes.surface.PaleWorldMaterialRules;
import net.minecraft.util.Identifier;
import static vesper.pw.PaleWorld.MOD_ID;
import static vesper.pw.PaleWorldConfig.*;

public class PaleWorldTerrablenderApi implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized(){
        Regions.register(new PaleCaveRegion(Identifier.of(MOD_ID, "overworld"), palecaveweight));
        Regions.register(new PaleGardenRegion(Identifier.of(MOD_ID, "overworld_1"), 2));
        Regions.register(new PaleForestRegion(Identifier.of(MOD_ID, "overworld_2"), 1));


        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, PaleWorldMaterialRules.makeRules());
    }
}
