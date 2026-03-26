package dev.vesper.paleworld.common.modify;

//? fabric{
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
//?}
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;

public class PaleGardenMobSpawnRates {
	public static ResourceKey<Biome> paleGardenKey = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("minecraft", "pale_garden"));

	public static void override(){
		//? fabric{
		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				EntityType.ZOMBIE,
				10,
				1,
				2
		);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				EntityType.SKELETON,
				15,
				1,
				2
		);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				EntityType.SPIDER,
				10,
				1,
				2
		);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				EntityType.CREEPER,
				5,
				1,
				2
		);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				EntityType.ENDERMAN,
				10,
				1,
				2
		);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				EntityType.WITCH,
				5,
				1,
				2
		);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				EntityType.ZOMBIE_VILLAGER,
				7,
				1,
				2
		);
		//?}
		//? neoforge{
		// nothing to do here cause NF spawns are data driven why TF cant the data driven stuff be the same across loaders aaaaaaa
		//?}
	}

}
