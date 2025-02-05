package vesper.pw.block.custom;

import java.util.function.ToIntFunction;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.GameEvent.Emitter;
import org.jetbrains.annotations.Nullable;
import vesper.pw.item.PaleWorldItems;

public interface PaleVines {
    VoxelShape SHAPE = Block.createCuboidShape((double)1.0F, (double)0.0F, (double)1.0F, (double)15.0F, (double)16.0F, (double)15.0F);
    BooleanProperty BERRIES = Properties.BERRIES;


    static ActionResult pickBerries(@Nullable Entity picker, BlockState state, World world, BlockPos pos) {
        if ((Boolean)state.get(BERRIES)) {
            Block.dropStack(world, pos, new ItemStack(PaleWorldItems.PALE_BERRIES, 1));
            float f = MathHelper.nextBetween(world.random, 0.8F, 1.2F);
            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_CAVE_VINES_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, f);
            BlockState blockState = (BlockState)state.with(BERRIES, false);
            world.setBlockState(pos, blockState, 2);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, Emitter.of(picker, blockState));
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }
    }

    static boolean hasBerries(BlockState state) {
        return state.contains(BERRIES) && (Boolean)state.get(BERRIES);
    }

    static ToIntFunction<BlockState> getLuminanceSupplier(int luminance) {
        return (state) -> (Boolean)state.get(Properties.BERRIES) ? luminance : 0;
    }
}