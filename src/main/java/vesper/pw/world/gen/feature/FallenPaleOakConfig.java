package vesper.pw.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;

import java.util.ArrayList;
import java.util.List;

public class FallenPaleOakConfig implements FeatureConfig {
    public static final Codec<FallenPaleOakConfig> CODEC = RecordCodecBuilder.create(fallenPaleOakConfigInstance -> fallenPaleOakConfigInstance.group(
            BlockStateProvider.TYPE_CODEC.fieldOf("trunk_provider").forGetter((featureConfig) -> featureConfig.trunkProvider),
            IntProvider.createValidatingCodec(0, 16).fieldOf("log_length").forGetter((featureConfig) -> featureConfig.logLength),
            TreeDecorator.TYPE_CODEC.listOf().fieldOf("stump_decorators").forGetter((featureConfig) -> featureConfig.stumpDecorators),
            TreeDecorator.TYPE_CODEC.listOf().fieldOf("log_decorators").forGetter((featureConfig) -> featureConfig.logDecorators)).apply(fallenPaleOakConfigInstance, FallenPaleOakConfig::new));

    public static BlockStateProvider trunkProvider;
    public static IntProvider logLength;
    public static List<TreeDecorator> stumpDecorators;
    public static List<TreeDecorator> logDecorators;

    protected FallenPaleOakConfig(BlockStateProvider trunkProvider, IntProvider logLength, List<TreeDecorator> stumpDecorators, List<TreeDecorator> logDecorators){
            FallenPaleOakConfig.trunkProvider = trunkProvider;
            FallenPaleOakConfig.logLength = logLength;
            FallenPaleOakConfig.stumpDecorators = stumpDecorators;
            FallenPaleOakConfig.logDecorators = logDecorators;
    }

    public static class Builder {
            private final BlockStateProvider trunkProvider;
            private final IntProvider logLength;
            private List<TreeDecorator> stumpDecorators = new ArrayList();
            private List<TreeDecorator> logDecorators = new ArrayList();

            public Builder(BlockStateProvider trunkProvider, IntProvider logLength){
                this.trunkProvider = trunkProvider;
                this.logLength = logLength;
            }

            public Builder stumpDecorators(List<TreeDecorator> stumpDecorators) {
                this.stumpDecorators = stumpDecorators;
                return this;
            }
        public Builder logDecorators(List<TreeDecorator> logDecorators) {
            this.logDecorators = logDecorators;
            return this;
        }

        public FallenPaleOakConfig build() {
            return new FallenPaleOakConfig(this.trunkProvider, this.logLength, this.stumpDecorators, this.logDecorators);
        }
    }
}
