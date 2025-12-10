package dev.vesper.paleworld.common.world.blocks;

import dev.vesper.paleworld.common.world.blocks.custom.BigDyingDripleafBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CreakingHeartBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SmallDripleafBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

import static dev.vesper.paleworld.PaleWorld.MOD_ID;

public class PaleWorldBlocks {

	public static BlockBehaviour.Properties createLogSettings(MapColor topmapColor, MapColor sideMapColor, SoundType sounds){
		return BlockBehaviour.Properties.of().mapColor((state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topmapColor : sideMapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(sounds).ignitedByLava();
	}

	public static Block getPaleVineBody() {
		return PALE_VINE_BODY;
	}

	public static final Block PALE_VINE = regBlock("pale_vine",
			new PaleVinePlantBlock(
					GrowingPlantBodyBlock.Properties.of()
							.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "pale_vine")))
							.noCollision()
							.sound(SoundType.CAVE_VINES)
							.randomTicks()
							.lightLevel(PaleVines.getLightLevelProvider(7))
							.instabreak()
							.mapColor(MapColor.COLOR_GRAY)
							.pushReaction(PushReaction.DESTROY)
							.noOcclusion()
			));

	public static final Block PALE_VINE_BODY = regBlock("pale_vine_body",
			new PaleVineBodyBlock(
					GrowingPlantHeadBlock.Properties.of()
							.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "pale_vine_body")))
							.noCollision()
							.sound(SoundType.CAVE_VINES)
							.randomTicks()
							.lightLevel(PaleVines.getLightLevelProvider(7))
							.breakInstantly()
							.mapColor(MapColor.COLOR_GRAY)
							.pushReaction(PushReaction.DESTROY)
							.nonOpaque()
			));
	public static final Block BIG_DYING_DRIPLEAF_STEM = regBlock("big_dying_dripleaf_stem",
			new BigDyingDripleafStemBlock(
					BlockBehaviour.Properties.of()
							.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "big_dying_dripleaf_stem")))
							.noCollision()
							.sound(SoundType.BIG_DRIPLEAF)
							.randomTicks()
							.instabreak()
							.mapColor(MapColor.COLOR_BROWN)
							.pushReaction(PushReaction.DESTROY)
	));

	public static final Block BIG_DYING_DRIPLEAF = regBlock("big_dying_dripleaf", new BigDyingDripleafBlock(
			BlockBehaviour.Properties.of()
					.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "big_dying_dripleaf")))
					.noCollision()
					.strength(0.1F)
					.sound(SoundType.BIG_DRIPLEAF)
					.mapColor(MapColor.COLOR_BROWN)
					.pushReaction(PushReaction.DESTROY)
	));

	public static final Block SMALL_DYING_DRIPLEAF = regBlock(
			"small_dying_dripleaf",
			new SmallDripleafBlock(
					BlockBehaviour.Properties.of()
							.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_dying_dripleaf")))
							.mapColor(MapColor.COLOR_BROWN)
							.noCollision()
							.instabreak()
							.sound(SoundType.SMALL_DRIPLEAF)
							.offsetType(BlockBehaviour.OffsetType.XYZ)
							.pushReaction(PushReaction.DESTROY)
							.requiresCorrectToolForDrops()
			));

	public static final Block PETRIFIED_PALE_OAK = regBlock("petrified_pale_oak",
			new Block(
					BlockBehaviour.Properties.of()
							.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "petrified_pale_oak")))
							.mapColor(MapColor.WOOL)
							.sound(SoundType.WOOD)
							.ignitedByLava()
							.strength(7)
							.requiresCorrectToolForDrops()
							.pushReaction(PushReaction.NORMAL)
			));

	public static final Block DYING_AZALEA = regBlock(
			"dying_azalea",
			new DyingAzalea(
					BlockBehaviour.Properties.of()
							.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "dying_azalea")))
							.mapColor(MapColor.COLOR_GRAY)
							.forceSolidOff()
							.instabreak()
							.sound(SoundType.AZALEA)
							.noOcclusion()
							.pushReaction(PushReaction.DESTROY)
			));

	public static final Block CHRYSANTHEMUM = regBlock(
			"chrysanthemum",
			(settings) -> new FlowerBlock(MobEffects.REGENERATION, 10.0F, settings),
			BlockBehaviour.Properties.of()
					.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "chrysanthemum")))
					.mapColor(MapColor.QUARTZ)
					.noCollision()
					.instabreak()
					.sound(SoundType.GRASS)
					.offsetType(BlockBehaviour.OffsetType.XZ)
					.pushReaction(PushReaction.DESTROY)
	);

	public static final Block RAFFLESIA = regBlock(
			"rafflesia",
			(settings) -> new RafflesiaBlock(MobEffects.NAUSEA, 10.0F, settings),
			BlockBehaviour.Properties.of()
					.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "rafflesia")))
					.mapColor(MapColor.COLOR_RED)
					.noCollision()
					.instabreak()
					.sound(SoundType.GRASS)
					.offsetType(BlockBehaviour.OffsetType.XZ)
					.pushReaction(PushReaction.DESTROY)
	);

	public static final Block ASPHODEL = regBlock(
			"asphodel",
			(settings) -> new FlowerBlock(MobEffects.LUCK, 15.0F, settings),
			BlockBehaviour.Properties.of()
					.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "asphodel")))
					.mapColor(MapColor.QUARTZ)
					.noCollision()
					.instabreak()
					.sound(SoundType.GRASS)
					.offsetType(BlockBehaviour.OffsetType.XZ)
					.pushReaction(PushReaction.DESTROY)
	);

	public static final Block WHITE_CRYSTAL = regBlock("white_crystal", new AmethystBlock(BlockBehaviour.Properties.of()
			.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "white_crystal")))
			.mapColor(MapColor.WOOL)
			.strength(1.5F)
			.requiresCorrectToolForDrops()
			.pushReaction(PushReaction.NORMAL)
			.sound(SoundType.AMETHYST)
	));

	public static final Block PALE_STONE = regBlock("pale_stone", new Block(BlockBehaviour.Properties.of()
			.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "pale_stone")))
			.pushReaction(PushReaction.NORMAL)
			.sound(SoundType.TUFF)
			.requiresCorrectToolForDrops()
			.strength(1.5F)
			.mapColor(MapColor.STONE)));

	public static final Block STRIPPED_CREAKING_HEART = regBlock("stripped_creaking_heart", new CreakingHeartBlock(BlockBehaviour.Properties.of()
			.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "stripped_creaking_heart")))
			.pushReaction(PushReaction.NORMAL)
			.sound(SoundType.CREAKING_HEART)
			.requiresCorrectToolForDrops()
			.strength(10)
			.instrument(NoteBlockInstrument.BASEDRUM)
			.mapColor(MapColor.COLOR_ORANGE)
	));

	private static Block regBlock(String name, Block block){
		regBlockItem(name, block);
		return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, name), block);
	}

	private static ResourceKey<Block> keyOf(String id){
		return ResourceKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace(id));
	}

	private static Block regBlock(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings){
		ResourceKey<Block> key = keyOf(id);
		Block block = factory.apply(settings);
		regBlockItem(id, block);
		return Registry.register(BuiltInRegistries.BLOCK, key.location(), block);
	}

	private static void regBlockItem(String item, Block block){
		Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, item), new BlockItem(block, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, item))).useItemDescriptionPrefix()));
	}

	private static void regModBlocks(){
		//? fabric{
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SEARCH).register(entries -> {

		});
		//?}
		//? neoforge{

		//?}
	}

	static {
		for (Block block : BuiltInRegistries.BLOCK){
			for (BlockState blockState : block.getStateDefinition().getPossibleStates()){
				Block.BLOCK_STATE_REGISTRY.add(blockState);
				blockState.initCache();
			}
		}
	}
}
