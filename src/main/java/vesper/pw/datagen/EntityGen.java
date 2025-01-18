package vesper.pw.datagen;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.world.Heightmap;
import vesper.pw.biomes.PaleWorldBiomes;
import vesper.pw.entity.Entities;

public class EntityGen {
    public static void addSpawns(){
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_CAVE), SpawnGroup.AXOLOTLS, Entities.PALE_AXOLOTL, 50,1,1);

        SpawnRestriction.register(Entities.PALE_AXOLOTL, SpawnLocationTypes.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AxolotlEntity::canSpawn);
    }
}
