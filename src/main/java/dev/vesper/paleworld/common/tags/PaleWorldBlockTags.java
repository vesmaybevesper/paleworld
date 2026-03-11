package dev.vesper.paleworld.common.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class PaleWorldBlockTags {
	public static final TagKey<Block> PALE_SPIKE_REPLACEABLE = create("pale_spike_replaceable");

	private void BlockTags(){

	}

	private static TagKey<Block> create(final String name) {
		return TagKey.create(Registries.BLOCK, Identifier.withDefaultNamespace(name));
	}
}
