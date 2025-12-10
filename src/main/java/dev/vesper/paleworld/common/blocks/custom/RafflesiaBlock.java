package dev.vesper.paleworld.common.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class RafflesiaBlock extends FlowerBlock {
	private static final VoxelShape SHAPE = Block.column(6, 0, 10);

	public RafflesiaBlock(Holder<MobEffect> holder, float f, Properties properties) {
		super(holder, f, properties);
	}

	@Override
	protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
		return Block.canSupportCenter(levelReader, blockPos.below(), Direction.UP) && !levelReader.isWaterAt(blockPos);
	}

	@Override
	protected @NotNull BlockState updateShape(BlockState blockState, LevelReader levelReader, ScheduledTickAccess scheduledTickAccess, BlockPos blockPos, Direction direction, BlockPos blockPos2, BlockState blockState2, RandomSource randomSource) {
		return direction == Direction.DOWN && !this.canSurvive(blockState, levelReader, blockPos) ? Blocks.AIR.getStateDefinition().any() : super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
	}

	@Override
	public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
		if (entity instanceof Player){
			((Player) entity).addEffect((MobEffectInstance) MobEffects.NAUSEA);
		}
	}

	@Override
	protected @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
		return SHAPE.move(blockState.getOffset(blockPos));
	}

	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
		int x = blockPos.getX();
		int y = blockPos.getY();
		int z = blockPos.getZ();
		double d = x + randomSource.nextDouble();
		double e = y + 0.7;
		double f = z + randomSource.nextDouble();
		level.addParticle(ParticleTypes.RAFFLESIA_PARTICLE, d, e, f, 0, 0, 0);
	}
}
