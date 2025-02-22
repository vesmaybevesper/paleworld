package vesper.pw.entity;


import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import vesper.pw.biomes.PaleWorldBiomes;
import vesper.pw.entity.VampireBat.VampireBat;

public class EntityGen {
    public static void addSpawns(){
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_CAVE), SpawnGroup.AXOLOTLS, Entities.PALE_AXOLOTL, 3, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_CAVE), SpawnGroup.MONSTER, Entities.VAMPIRE_BAT, 50, 1, 3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PALE_GARDEN), SpawnGroup.MONSTER, Entities.VAMPIRE_BAT, 25, 1, 3);

        SpawnRestriction.register(Entities.PALE_AXOLOTL, SpawnLocationTypes.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AxolotlEntity::canSpawn);
        SpawnRestriction.register(Entities.VAMPIRE_BAT, SpawnLocationTypes.UNRESTRICTED, Heightmap.Type.WORLD_SURFACE, VampireBat::canMobSpawn);

    }
}
