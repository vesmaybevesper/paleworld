/*
package vesper.pw.world.gen.feature;

import com.mojang.serialization.MapCodec;
import net.minecraft.registry.*;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import vesper.pw.world.gen.NoLeavesDec;

public class RegisterFeatures {
    //public static final Feature<FallenPaleOakConfig>FALLEN_TREE;



private static FallenPaleOakConfig.Builder fallenPaleOak(int maxLength) {
        return fallen(Blocks.BIRCH_LOG, 5, maxLength);
    }

    private static FallenPaleOakConfig.Builder fallen(Block log, int minLength, int maxLength) {
        return (new FallenPaleOakConfig.Builder(BlockStateProvider.of(log), UniformIntProvider.create(minLength, maxLength))).logDecorators(ImmutableList.of(new AttachedToLogDecorator.AttachedToLogsTreeDecorator(0.1F, new WeightedBlockStateProvider(Pool.builder().add(Blocks.RED_MUSHROOM.getDefaultState(), 2).add(Blocks.BROWN_MUSHROOM.getDefaultState(), 1)), List.of(Direction.UP))));
    }

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return (F)(Registry.register(Registries.FEATURE, name, feature));
    }



*/
