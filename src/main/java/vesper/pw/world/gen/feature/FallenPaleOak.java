package vesper.pw.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.treedecorator.TreeDecorator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class FallenPaleOak extends Feature<FallenPaleOakConfig> {
    public FallenPaleOak(Codec<FallenPaleOakConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<FallenPaleOakConfig> context) {
        this.generate(context.getConfig(), context.getOrigin(), context.getWorld(), context.getRandom());
        return true;
    }

    private void generate(FallenPaleOakConfig config, BlockPos pos, StructureWorldAccess world, Random random) {
        this.generateStump(config, world, random, pos.mutableCopy());
        Direction direction = Direction.Type.HORIZONTAL.random(random);
        int i = config.logLength.get(random) - 2;
        BlockPos.Mutable mutable = pos.offset(direction, 2 + random.nextInt(2)).mutableCopy();
        this.moveToGroundPos(world, mutable);
        if (this.canPlaceLog(world, i, mutable, direction)) {
            this.generateLog(config, world, random, i, mutable, direction);
        }
    }
    private void moveToGroundPos(StructureWorldAccess world, BlockPos.Mutable pos) {
        pos.move(Direction.UP, 1);

        for(int i = 0; i < 6; ++i) {
            if (this.canReplaceAndHasSolidBelow(world, pos)) {
                return;
            }

            pos.move(Direction.DOWN);
        }

    }

    private void generateStump(FallenPaleOakConfig config, StructureWorldAccess world, Random random, BlockPos.Mutable pos) {
        BlockPos blockPos = setBlockStateAndGetPos(config, world, random, pos, Function.identity());
        this.applyDecorators(world, random, Set.of(blockPos), config.stumpDecorators);
    }

    private boolean canPlaceLog(StructureWorldAccess world, int length, BlockPos.Mutable pos, Direction direction) {
        int i = 0;

        for(int j = 0; j < length; ++j) {
            if (!TreeFeature.canReplace(world, pos)) {
                return false;
            }

            if (!this.isSolidBelow(world, pos)) {
                ++i;
                if (i > 2) {
                    return false;
                }
            } else {
                i = 0;
            }

            pos.move(direction);
        }

        pos.move(direction.getOpposite(), length);
        return true;
    }

    private void generateLog(FallenPaleOakConfig config, StructureWorldAccess world, Random random, int length, BlockPos.Mutable pos, Direction direction) {
        Set<BlockPos> set = new HashSet();

        for(int i = 0; i < length; ++i) {
            set.add(setBlockStateAndGetPos(config, world, random, pos, createAxisApplier(direction)));
            pos.move(direction);
        }

        this.applyDecorators(world, random, set, config.logDecorators);
    }

    private boolean canReplaceAndHasSolidBelow(WorldAccess world, BlockPos pos) {
        return TreeFeature.canReplace(world, pos) && this.isSolidBelow(world, pos);
    }

    private boolean isSolidBelow(WorldAccess world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos, Direction.UP);
    }

    private static BlockPos setBlockStateAndGetPos(FallenPaleOakConfig config, StructureWorldAccess world, Random random, BlockPos.Mutable pos, Function<BlockState, BlockState> stateFunction) {
        world.setBlockState(pos, (BlockState)stateFunction.apply(config.trunkProvider.get(random, pos)), 19);
        return pos.toImmutable();
    }

    private void applyDecorators(StructureWorldAccess world, Random random, Set<BlockPos> positions, List<TreeDecorator> decorators) {
        if (!decorators.isEmpty()) {
            TreeDecorator.Generator generator = new TreeDecorator.Generator(world, this.createStatePlacer(world), random, positions, Set.of(), Set.of());
            decorators.forEach((decorator) -> decorator.generate(generator));
        }

    }

    private BiConsumer<BlockPos, BlockState> createStatePlacer(StructureWorldAccess world) {
        return (pos, state) -> world.setBlockState(pos, state, 19);
    }

    private static Function<BlockState, BlockState> createAxisApplier(Direction direction) {
        return (state) -> (BlockState)state.withIfExists(PillarBlock.AXIS, direction.getAxis());
    }
}
