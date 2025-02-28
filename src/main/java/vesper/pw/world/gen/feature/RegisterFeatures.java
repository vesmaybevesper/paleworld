package vesper.pw.world.gen.feature;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import vesper.pw.PaleWorld;

import java.util.List;

public class RegisterFeatures {
    /*public static final Feature<DefaultFeatureConfig> PALE_SPIKE_FEATURE;


    static {
        PALE_SPIKE_FEATURE = register("pale_spike", new PaleSpikeFeature(DefaultFeatureConfig.CODEC));
    }*/

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return (F)(Registry.register(Registries.FEATURE, name, feature));
    }
}
