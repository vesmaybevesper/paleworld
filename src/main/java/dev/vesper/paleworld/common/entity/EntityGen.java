package dev.vesper.paleworld.common.entity;

import dev.vesper.paleworld.common.entity.VampireBat.VampireBat;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

public class EntityGen {
	public static void addSpawns(){
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_CAVE), MobCategory.AXOLOTLS, Entities.PALE_AXOLOTL, 3, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_CAVE), MobCategory.MONSTER, Entities.VAMPIRE_BAT, 50, 1, 3);
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), MobCategory.MONSTER, Entities.VAMPIRE_BAT, 25, 1, 2);
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(PaleWorldBiomes.PALE_VALLEY), MobCategory.MONSTER, Entities.VAMPIRE_BAT, 25, 1, 1);

		SpawnPlacements.register(Entities.PALE_AXOLOTL, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Axolotl::checkAxolotlSpawnRules);
		SpawnPlacements.register(Entities.VAMPIRE_BAT, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.WORLD_SURFACE, VampireBat::canSpawn);
	}
}
