package vesper.pw.modify;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class PaleGardenSpawnRate {

    static RegistryKey<Biome> paleGardenKey = RegistryKey.of(RegistryKeys.BIOME, Identifier.of("minecraft", "pale_garden"));
    public static void override(){
        BiomeModifications.addSpawn(
        BiomeSelectors.includeByKey(paleGardenKey),
                SpawnGroup.MONSTER,
                EntityType.ZOMBIE,
                10,
                1,
                2
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(paleGardenKey),
                SpawnGroup.MONSTER,
                EntityType.SKELETON,
                15,
                1,
                2
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(paleGardenKey),
                SpawnGroup.MONSTER,
                EntityType.SPIDER,
                10,
                1,
                2
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(paleGardenKey),
                SpawnGroup.MONSTER,
                EntityType.CREEPER,
                5,
                1,
                2
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(paleGardenKey),
                SpawnGroup.MONSTER,
                EntityType.ENDERMAN,
                10,
                1,
                2
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(paleGardenKey),
                SpawnGroup.MONSTER,
                EntityType.WITCH,
                5,
                1,
                2
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(paleGardenKey),
                SpawnGroup.MONSTER,
                EntityType.ZOMBIE_VILLAGER,
                7,
                1,
                2
        );
    }
}
