package vesper.pw.utils;

import net.fabricmc.loader.api.FabricLoader;
import net.irisshaders.iris.api.v0.IrisApi;

public class ModCompatCheckers {

    public static boolean isShaders() {
        if (FabricLoader.getInstance().isModLoaded("iris")){
            return IrisApi.getInstance().getConfig().areShadersEnabled();
        } else {
            return false;
        }
    }



}
