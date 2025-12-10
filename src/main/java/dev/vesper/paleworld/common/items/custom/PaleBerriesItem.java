package dev.vesper.paleworld.common.items.custom;

import dev.vesper.paleworld.common.blocks.PaleWorldBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

public class PaleBerriesItem extends Item {

	public PaleBerriesItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext useOnContext) {
		Level level = useOnContext.getLevel();
		BlockPos pos = useOnContext.getClickedPos();
		Direction side = useOnContext.getClickedFace();

		if (side != Direction.DOWN){
			return InteractionResult.PASS;
		}

		BlockPos vinePos = pos.below();
		BlockState clickedBlockSate = level.getBlockState(pos);
		BlockState belowBlockState = level.getBlockState(vinePos);

		if (level.getBlockState(vinePos).isAir() || level.getBlockState(vinePos).is(PaleWorldBlocks.PALE_VINE_BODY)){
			level.setBlockAndUpdate(vinePos, PaleWorldBlocks.PALE_VINE.defaultBlockState());
			level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.CAVE_VINES_PLACE, SoundSource.BLOCKS);

			if (!Objects.requireNonNull(useOnContext.getPlayer()).isCreative()){
				useOnContext.getItemInHand().shrink(1);
			}
			return InteractionResult.SUCCESS;
		}

		if (clickedBlockSate.is(PaleWorldBlocks.PALE_VINE_BODY)){
			BlockPos belowClicked = pos.below();
			if (level.getBlockState(belowClicked).isAir()){
				level.setBlockAndUpdate(belowClicked, PaleWorldBlocks.PALE_VINE.defaultBlockState());
			}
			if (!Objects.requireNonNull(useOnContext.getPlayer()).isCreative()){
				useOnContext.getItemInHand().shrink(1);
			}
		}
		return InteractionResult.PASS;
	}
}
