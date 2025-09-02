package vesper.pw.block.entity;

import com.mojang.logging.LogUtils;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Util;
import org.slf4j.Logger;
import vesper.pw.block.PaleWorldBlocks;

import java.util.Set;


public class BlockEntities<T extends BlockEntity> extends BlockEntityType<BlockEntity> implements FabricBlockEntityType {
    private static Logger LOGGER = LogUtils.getLogger();

    private final BlockEntityType.BlockEntityFactory<? extends T> factory;
    private final Set<Block> blocks;
    private final RegistryEntry.Reference<BlockEntityType<?>> registryEntry;

    private BlockEntities(BlockEntityType.BlockEntityFactory<? extends T> factory, Set<Block> blocks) {
        super(factory, blocks);
        this.registryEntry = Registries.BLOCK_ENTITY_TYPE.createEntry(this);
        this.factory = factory;
        this.blocks = blocks;
    }

    private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.BlockEntityFactory<? extends T> factory, Block... blocks) {
        if (blocks.length == 0) {
            LOGGER.warn("Block entity type {} requires at least one valid block to be defined!", id);
        }

        Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return (BlockEntityType) Registry.register(Registries.BLOCK_ENTITY_TYPE, id, new BlockEntities<>(factory, Set.of(blocks)));
    }



    static {
    }


    @Override
    public void addSupportedBlock(Block block) {
        super.addSupportedBlock(block);
    }
}
