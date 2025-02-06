package vesper.pw.entity;


import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.world.Heightmap;
import vesper.pw.biomes.PaleWorldBiomes;

public class EntityGen {
    public static void addSpawns(){
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_CAVE), SpawnGroup.AXOLOTLS, Entities.PALE_AXOLOTL, 5, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_CAVE), SpawnGroup.MONSTER, Entities.VAMPIRE_BAT, 7, 1, 3);

        SpawnRestriction.register(Entities.PALE_AXOLOTL, SpawnLocationTypes.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AxolotlEntity::canSpawn);
        SpawnRestriction.register(Entities.VAMPIRE_BAT, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FlyingEntity::canMobSpawn);

    }
}
