package dev.vesper.paleworld.common.blocks.custom;

import dev.vesper.paleworld.common.items.PaleWorldItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public interface PaleVines {
	VoxelShape SHAPE = Block.box(1.0f, 0.0f, 1.0f, 15.0f, 16.0f, 15.0f);
	BooleanProperty BERRIES = BlockStateProperties.BERRIES;

	static InteractionResult pickBerries(@Nullable Entity picker, BlockState state, Level level, BlockPos pos){
		if ((Boolean)state.getValue(BERRIES)) {
			Block.popResource(level, pos, new ItemStack(PaleWorldItems.PALE_BERRIES, 1));
			float f = Mth.randomBetween(level.random, 0.8F, 1.2F);
			level.playSound((Player)null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, f);
			BlockState blockState = (BlockState)state.setValue(BERRIES, false);
			level.setBlock(pos, blockState, 2);
			level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(picker, blockState));
			return InteractionResult.SUCCESS;
		} else {
			return InteractionResult.PASS;
		}
	}

	static boolean hasBerries(BlockState state){
		return state.hasProperty(BERRIES) && state.getValue(BERRIES);
	}

	static ToIntFunction<BlockState> getLightLevelProvider(int luminance){
		return (state) -> state.getValue(BlockStateProperties.BERRIES) ? luminance : 0;
	}
}
