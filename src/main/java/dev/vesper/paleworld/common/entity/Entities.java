package dev.vesper.paleworld.common.entity;

import dev.vesper.paleworld.common.entity.PaleAxolotl.PaleAxolotl;
import dev.vesper.paleworld.common.entity.VampireBat.VampireBat;
//? fabric{
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
//?}
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
//?1.21.11{
/*import net.minecraft.resources.Identifier;
*///?}
import net.minecraft.resources.ResourceKey;
//? <1.21.11{
import net.minecraft.resources.ResourceLocation;
//?}
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import static dev.vesper.paleworld.PaleWorld.MOD_ID;

public class Entities {
	public static void init(){}
//?<1.21.11{
	private static final ResourceKey<EntityType<?>> PALE_AXOLOTL_KEY = ResourceKey.create(
			BuiltInRegistries.ENTITY_TYPE.key(),
			ResourceLocation.fromNamespaceAndPath(MOD_ID, "pale_axolotl")
	);

	private static final ResourceKey<EntityType<?>> VAMPIRE_BAT_KEY = ResourceKey.create(
			BuiltInRegistries.ENTITY_TYPE.key(),
			ResourceLocation.fromNamespaceAndPath(MOD_ID, "vampire_bat")
	);
	//?}
	//?1.21.11{
	/*private static final ResourceKey<EntityType<?>> PALE_AXOLOTL_KEY = ResourceKey.create(
			BuiltInRegistries.ENTITY_TYPE.key(),
			Identifier.fromNamespaceAndPath(MOD_ID, "pale_axolotl")
	);

	private static final ResourceKey<EntityType<?>> VAMPIRE_BAT_KEY = ResourceKey.create(
			BuiltInRegistries.ENTITY_TYPE.key(),
			Identifier.fromNamespaceAndPath(MOD_ID, "vampire_bat")
	);
	*///?}
//? fabric{
	//?<1.21.11{
	public static final EntityType<PaleAxolotl> PALE_AXOLOTL = Registry.register(BuiltInRegistries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "pale_axolotl"),
			FabricEntityType.Builder.createLiving(PaleAxolotl::new, MobCategory.WATER_AMBIENT, builder -> builder).sized(.75f, .45f).build(PALE_AXOLOTL_KEY));

	public static final EntityType<VampireBat> VAMPIRE_BAT = Registry.register(BuiltInRegistries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "vampire_bat"),
			FabricEntityType.Builder.createLiving(VampireBat::new, MobCategory.MONSTER, builder -> builder).sized(.75f, 1f).build(VAMPIRE_BAT_KEY));
	//?}
	//?1.21.11{
	/*public static final EntityType<PaleAxolotl> PALE_AXOLOTL = Registry.register(BuiltInRegistries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "pale_axolotl"),
			FabricEntityType.Builder.createLiving(PaleAxolotl::new, MobCategory.WATER_AMBIENT, builder -> builder).sized(.75f, .45f).build(PALE_AXOLOTL_KEY));

	public static final EntityType<VampireBat> VAMPIRE_BAT = Registry.register(BuiltInRegistries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "vampire_bat"),
			FabricEntityType.Builder.createLiving(VampireBat::new, MobCategory.MONSTER, builder -> builder).sized(.75f, 1f).build(VAMPIRE_BAT_KEY));
	*///?}
	//?}

//? neoforge{
	/*public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MOD_ID);

	public static final RegistryObject<EntityType<PaleAxolotl>> PALE_AXOLOTL = ENTITY_TYPES.register("pale_axolotl", () -> EntityType.Builder.of(PaleAxolotl::new, MobCategory.MONSTER).sized(0.25f, 0.5f).build(PALE_AXOLOTL_KEY));
	public static final RegistryObject<EntityType<VampireBat>> VAMPIRE_BAT = ENTITY_TYPES.register("vampire_bat", () -> EntityType.Builder.of(VampireBat::new, MobCategory.MONSTER).sized(0.25f, 0.5f).build(VAMPIRE_BAT_KEY));
	*///?}
}
