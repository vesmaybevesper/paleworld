package dev.vesper.paleworld.common.world.gen.feature;

import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jspecify.annotations.NonNull;

public class BlankLeaves extends FoliagePlacer {
	public BlankLeaves(IntProvider intProvider, IntProvider intProvider2) {

		super(UniformInt.of(0, 0), UniformInt.of(0, 0));
	}

	@Override
	protected @NonNull FoliagePlacerType<?> type() {
		return FoliagePlacerType.DARK_OAK_FOLIAGE_PLACER;
	}

	@Override
	protected void createFoliage(@NonNull WorldGenLevel level, @NonNull FoliageSetter foliageSetter, @NonNull RandomSource random, @NonNull TreeConfiguration config, int treeHeight, @NonNull FoliageAttachment foliageAttachment, int foliageHeight, int leafRadius, int offset) {}

	@Override
	public int foliageHeight(@NonNull RandomSource randomSource, int i, @NonNull TreeConfiguration treeConfiguration) {
		return 0;
	}

	@Override
	protected boolean shouldSkipLocation(@NonNull RandomSource randomSource, int i, int j, int k, int l, boolean bl) {
		return true;
	}
}
