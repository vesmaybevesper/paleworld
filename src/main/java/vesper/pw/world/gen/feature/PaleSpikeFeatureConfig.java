package vesper.pw.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.FeatureConfig;

public class PaleSpikeFeatureConfig implements FeatureConfig {
    public static final PaleSpikeFeatureConfig INSTANCE = new PaleSpikeFeatureConfig();
    public static final Codec<PaleSpikeFeatureConfig> CODEC = Codec.unit(() -> INSTANCE);


    public PaleSpikeFeatureConfig(){
    }
}
