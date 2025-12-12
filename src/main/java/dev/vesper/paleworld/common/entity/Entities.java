package dev.vesper.paleworld.common.entity;

import dev.vesper.paleworld.common.entity.PaleAxolotl.PaleAxolotl;
import dev.vesper.paleworld.common.entity.VampireBat.VampireBat;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import static dev.vesper.paleworld.PaleWorld.MOD_ID;

public class Entities {
	private static final ResourceKey<EntityType<?>> PALE_AXOLOTL_KEY = ResourceKey.create(
			BuiltInRegistries.ENTITY_TYPE.key(),
			ResourceLocation.fromNamespaceAndPath(MOD_ID, "pale_axolotl")
	);

	private static final ResourceKey<EntityType<?>> VAMPIRE_BAT_KEY = ResourceKey.create(
			BuiltInRegistries.ENTITY_TYPE.key(),
			ResourceLocation.fromNamespaceAndPath(MOD_ID, "vampire_bat")
	);

	public static final EntityType<PaleAxolotl> PALE_AXOLOTL = Registry.register(BuiltInRegistries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "pale_axolotl"),
			FabricEntityType.Builder.createLiving(PaleAxolotl::new, MobCategory.WATER_AMBIENT, builder -> builder).sized(.25f, .25f).build(PALE_AXOLOTL_KEY));

	public static final EntityType<VampireBat> VAMPIRE_BAT = Registry.register(BuiltInRegistries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "vampire_bat"),
			FabricEntityType.Builder.createLiving(VampireBat::new, MobCategory.MONSTER, builder -> builder).sized(.25f, .5f).build(PALE_AXOLOTL_KEY));
}
