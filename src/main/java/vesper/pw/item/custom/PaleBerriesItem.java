package vesper.pw.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
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

        // Only allow placement on the bottom face of blocks
        if (side != Direction.DOWN || !world.getBlockState(pos).isSolidBlock(world, pos)) {
            return ActionResult.PASS;
        }

        BlockPos vinePos = pos.down();
        if (world.getBlockState(vinePos).isAir() || world.getBlockState(vinePos).isOf(PaleWorldBlocks.PALE_VINE_BODY)) {
            // Place your custom vine block here
            world.setBlockState(vinePos, PaleWorldBlocks.PALE_VINE.getDefaultState());
            world.playSound(null, pos.getX(), pos.getY(), pos.getZ() , SoundEvents.BLOCK_CAVE_VINES_PLACE, SoundCategory.BLOCKS);
            // Consume the item if not in creative mode
            if (!Objects.requireNonNull(context.getPlayer()).isCreative()) {
                context.getStack().decrement(1);
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
