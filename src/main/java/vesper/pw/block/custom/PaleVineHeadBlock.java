package vesper.pw.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.WorldView;
import vesper.pw.block.PaleWorldBlocks;

public class PaleVineHeadBlock extends AbstractPlantStemBlock implements CaveVines {

    public static final MapCodec<PaleVineHeadBlock> CODEC = createCodec(PaleVineHeadBlock::new);

    public PaleVineHeadBlock(AbstractBlock.Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false, 0.1);
    }

    @Override
    protected MapCodec<? extends AbstractPlantStemBlock> getCodec() {
        return null;
    }

    @Override
    protected int getGrowthLength(Random random) {
        return 0;
    }

    @Override
    protected boolean chooseStemState(BlockState state) {
        return false;
    }

    @Override
    protected Block getPlant() {
        return PaleWorldBlocks.PALE_VINE_BODY;
    }

    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(PaleWorldBlocks.PALE_VINE);
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return null;
    }
}
