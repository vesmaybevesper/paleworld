package dev.vesper.paleworld.common.modify;

//? fabric{
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
//?}
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;

public class PaleGardenMobSpawnRates {
	public static ResourceKey<Biome> paleGardenKey = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("minecraft", "pale_garden"));

	public static EntityType<?> entityType(String mob){
		return BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.withDefaultNamespace(mob));
	}

	public static void override(){
		//? <26.2{
		/*BiomeModifications.addSpawn(
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
		*///?} >=26.2{
		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				entityType("zombie"),
				10,
				1,
				2
		);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				entityType("skeleton"),
				15,
				1,
				2
		);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				entityType("spider"),
				10,
				1,
				2
		);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				entityType("creeper"),
				5,
				1,
				2
		);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				entityType("enderman"),
				10,
				1,
				2
		);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				entityType("witch"),
				5,
				1,
				2
		);

		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(paleGardenKey),
				MobCategory.MONSTER,
				entityType("zombie_villager"),
				7,
				1,
				2
		);
		//?}
	}

}
