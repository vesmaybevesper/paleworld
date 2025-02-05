package vesper.pw.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import vesper.pw.PaleWorld;
import vesper.pw.block.custom.*;

import java.util.function.Function;

public class PaleWorldBlocks {

    public static AbstractBlock.Settings createLogSettings(MapColor topMapColor, MapColor sideMapColor, BlockSoundGroup sounds) {
        return AbstractBlock.Settings.create().mapColor((state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(sounds).burnable();
    }

    public static Block getPaleVineBody() {
        return PALE_VINE_BODY;
    }

    public static final Block PALE_VINE = regBlock("pale_vine",
            new PaleVineHeadBlock(
            AbstractPlantBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PaleWorld.MOD_ID, "pale_vine")))
                    .noCollision()
                    .sounds(BlockSoundGroup.CAVE_VINES)
                    .ticksRandomly()
                    .luminance(PaleVines.getLuminanceSupplier(7))
                    .breakInstantly()
                    .mapColor(MapColor.GRAY)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .nonOpaque()
    ));
    public static final Block PALE_VINE_BODY = regBlock(
            "pale_vine_body",
            new PaleVineBodyBlock(
            AbstractPlantStemBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PaleWorld.MOD_ID, "pale_vine_body")))
                    .noCollision()
                    .sounds(BlockSoundGroup.CAVE_VINES)
                    .ticksRandomly()
                    .luminance(PaleVines.getLuminanceSupplier(7))
                    .breakInstantly()
                    .mapColor(MapColor.GRAY)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .nonOpaque()
            ));
    public static final Block BIG_DYING_DRIPLEAF_STEM = regBlock("big_dying_dripleaf_stem", new BigDyingDripleafStemBlock(
            AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PaleWorld.MOD_ID, "big_dying_dripleaf_stem")))
                    .noCollision()
                    .sounds(BlockSoundGroup.BIG_DRIPLEAF)
                    .ticksRandomly().breakInstantly()
                    .mapColor(MapColor.BROWN)
                    .pistonBehavior(PistonBehavior.DESTROY)
    ));

    public static final Block BIG_DYING_DRIPLEAF = regBlock("big_dying_dripleaf", new BigDyingDripleafBlock(
            AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PaleWorld.MOD_ID, "big_dying_dripleaf")))
                    .noCollision()
                    .strength(0.1F)
                    .sounds(BlockSoundGroup.BIG_DRIPLEAF)
                    .mapColor(MapColor.BROWN)
                    .pistonBehavior(PistonBehavior.DESTROY)
    ));

    public static final Block SMALL_DYING_DRIPLEAF = regBlock(
            "small_dying_dripleaf",
            new SmallDripleafBlock(
            AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PaleWorld.MOD_ID, "small_dying_dripleaf")))
                    .mapColor(MapColor.BROWN)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.SMALL_DRIPLEAF)
                    .offset(AbstractBlock.OffsetType.XYZ)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .requiresTool()
    ));
    public static final Block PETRIFIED_PALE_OAK = regBlock("petrified_pale_oak", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PaleWorld.MOD_ID, "petrified_pale_oak")))
            .mapColor(MapColor.WHITE_GRAY).sounds(BlockSoundGroup.WOOD).burnable().hardness(7).requiresTool().pistonBehavior(PistonBehavior.NORMAL)));

    public static final Block DYING_AZALEA = regBlock(
            "dying_azalea",
            new DyingAzalea(
                    AbstractBlock.Settings.create()
                            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PaleWorld.MOD_ID, "dying_azalea")))
                            .mapColor(MapColor.GRAY)
                            .notSolid()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.AZALEA)
                            .nonOpaque()
                            .pistonBehavior(PistonBehavior.DESTROY)
    ));

    private static Block regBlock(String name, Block block) {
        regBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PaleWorld.MOD_ID, name), block);
    }

    /*private static Block regBlock(String id, AbstractBlock.Settings settings) {
        return regBlock(id, Block::new, settings);*/


    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(id));
    }

    private static Block regBlock(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        RegistryKey<Block> key = keyOf(id);
        Block block = factory.apply(settings);
        regBlockItem(id, block);
        return Registry.register(Registries.BLOCK, key.getValue(), block);
    }


    private static void regBlockItem(String item, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(PaleWorld.MOD_ID, item), new BlockItem(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PaleWorld.MOD_ID, item))).useBlockPrefixedTranslationKey()));
    }

    public static void regModBlocks() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SEARCH).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(PALE_VINE);
            fabricItemGroupEntries.add(PALE_VINE_BODY);
            fabricItemGroupEntries.add(PETRIFIED_PALE_OAK);
            fabricItemGroupEntries.add(BIG_DYING_DRIPLEAF_STEM);
            fabricItemGroupEntries.add(BIG_DYING_DRIPLEAF);
            fabricItemGroupEntries.add(DYING_AZALEA);
            fabricItemGroupEntries.add(SMALL_DYING_DRIPLEAF);
        });
    }

    static {
        for (Block block : Registries.BLOCK) {
            for (BlockState blockState : block.getStateManager().getStates()) {
                Block.STATE_IDS.add(blockState);
                blockState.initShapeCache();
            }

        }
    }
}


