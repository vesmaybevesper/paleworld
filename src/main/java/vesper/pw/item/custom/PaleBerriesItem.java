package vesper.pw.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import vesper.pw.block.PaleWorldBlocks;

import java.util.Objects;

public class PaleBerriesItem extends Item {
    public PaleBerriesItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        Direction side = context.getSide();


        if (side != Direction.DOWN) {
            return ActionResult.PASS;
        }

        BlockPos vinePos = pos.down();
        BlockState clickedBlockState = world.getBlockState(pos);
        BlockState belowBlockState = world.getBlockState(vinePos);
        if (world.getBlockState(vinePos).isAir() || world.getBlockState(vinePos).isOf(PaleWorldBlocks.PALE_VINE_BODY)) {

            world.setBlockState(vinePos, PaleWorldBlocks.PALE_VINE.getDefaultState());
            world.playSound(null, pos.getX(), pos.getY(), pos.getZ() , SoundEvents.BLOCK_CAVE_VINES_PLACE, SoundCategory.BLOCKS);

            if (!Objects.requireNonNull(context.getPlayer()).isCreative()) {
                context.getStack().decrement(1);
            }
            return ActionResult.SUCCESS;
        }

        if (clickedBlockState.isOf(PaleWorldBlocks.PALE_VINE_BODY)) {
            BlockPos belowClicked = pos.down();
            if (world.getBlockState(belowClicked).isAir()) {
                world.setBlockState(belowClicked, PaleWorldBlocks.PALE_VINE.getDefaultState());
            }

            if (!Objects.requireNonNull(context.getPlayer()).isCreative()) {
                context.getStack().decrement(1);
            }
        }

        return ActionResult.PASS;
    }
}
