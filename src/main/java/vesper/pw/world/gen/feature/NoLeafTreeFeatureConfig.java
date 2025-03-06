package vesper.pw.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.FeatureSize;
import net.minecraft.world.gen.root.RootPlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunk.TrunkPlacer;

import java.util.List;
import java.util.Optional;

public class NoLeafTreeFeatureConfig implements FeatureConfig {
    public static final Codec<NoLeafTreeFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("trunk_provider").forGetter((config) -> config.trunkProvider), TrunkPlacer.TYPE_CODEC.fieldOf("trunk_placer").forGetter((config) -> config.trunkPlacer), RootPlacer.TYPE_CODEC.optionalFieldOf("root_placer").forGetter((config) -> config.rootPlacer), BlockStateProvider.TYPE_CODEC.fieldOf("dirt_provider").forGetter((config) -> config.dirtProvider), FeatureSize.TYPE_CODEC.fieldOf("minimum_size").forGetter((config) -> config.minimumSize), TreeDecorator.TYPE_CODEC.listOf().fieldOf("decorators").forGetter((config) -> config.decorators), Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter((config) -> config.ignoreVines), Codec.BOOL.fieldOf("force_dirt").orElse(false).forGetter((config) -> config.forceDirt)).apply(instance, NoLeafTreeFeatureConfig::new));
    public final BlockStateProvider trunkProvider;
    public final BlockStateProvider dirtProvider;
    public final TrunkPlacer trunkPlacer;
    public final Optional<RootPlacer> rootPlacer;
    public final FeatureSize minimumSize;
    public final List<TreeDecorator> decorators;
    public final boolean ignoreVines;
    public final boolean forceDirt;

    protected NoLeafTreeFeatureConfig(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, Optional<RootPlacer> rootPlacer, BlockStateProvider dirtProvider, FeatureSize minimumSize, List<TreeDecorator> decorators, boolean ignoreVines, boolean forceDirt) {
        this.trunkProvider = trunkProvider;
        this.trunkPlacer = trunkPlacer;
        this.rootPlacer = rootPlacer;
        this.dirtProvider = dirtProvider;
        this.minimumSize = minimumSize;
        this.decorators = decorators;
        this.ignoreVines = ignoreVines;
        this.forceDirt = forceDirt;
    }

    public static class Builder {
        public final BlockStateProvider trunkProvider;
        private final TrunkPlacer trunkPlacer;
        private final Optional<RootPlacer> rootPlacer;
        private BlockStateProvider dirtProvider;
        private final FeatureSize minimumSize;
        private List<TreeDecorator> decorators;
        private boolean ignoreVines;
        private boolean forceDirt;

        public Builder(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, Optional<RootPlacer> rootPlacer, FeatureSize minimumSize) {
            this.decorators = ImmutableList.of();
            this.trunkProvider = trunkProvider;
            this.trunkPlacer = trunkPlacer;
            this.dirtProvider = BlockStateProvider.of(Blocks.DIRT);
            this.rootPlacer = rootPlacer;
            this.minimumSize = minimumSize;
        }
        public Builder(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, FeatureSize minimumSize) {
            this(trunkProvider, trunkPlacer, Optional.empty(), minimumSize);
        }

        public NoLeafTreeFeatureConfig.Builder dirtProvider(BlockStateProvider dirtProvider) {
            this.dirtProvider = dirtProvider;
            return this;
        }

        public NoLeafTreeFeatureConfig.Builder decorators(List<TreeDecorator> decorators) {
            this.decorators = decorators;
            return this;
        }

        public NoLeafTreeFeatureConfig.Builder ignoreVines() {
            this.ignoreVines = true;
            return this;
        }

        public NoLeafTreeFeatureConfig.Builder forceDirt() {
            this.forceDirt = true;
            return this;
        }

        public NoLeafTreeFeatureConfig build() {
            return new NoLeafTreeFeatureConfig(this.trunkProvider, this.trunkPlacer, this.rootPlacer, this.dirtProvider, this.minimumSize, this.decorators, this.ignoreVines, this.forceDirt);
        }
    }
}
