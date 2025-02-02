package vesper.pw.entity;


import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;
import vesper.pw.entity.PaleAxolotl.PaleAxolotl;
import vesper.pw.entity.VampireBat.VampireBat;

import java.util.function.UnaryOperator;

public class Entities {
    private static final RegistryKey<EntityType<?>> PALE_AXOLOTL_KEY = RegistryKey.of(
            Registries.ENTITY_TYPE.getKey(),
            Identifier.of(PaleWorld.MOD_ID, "pale_axolotl")
    );

    private static final RegistryKey<EntityType<?>> VAMPIRE_BAT_KEY = RegistryKey.of(
            Registries.ENTITY_TYPE.getKey(),
            Identifier.of(PaleWorld.MOD_ID, "vampire_bat")
    );

    public static final EntityType<PaleAxolotl> PALE_AXOLOTL = Registry.register(Registries.ENTITY_TYPE, Identifier.of(PaleWorld.MOD_ID, "pale_axolotl"),
            FabricEntityType.Builder.createLiving(PaleAxolotl::new, SpawnGroup.WATER_AMBIENT, builder -> builder).dimensions(.75F,.42F).build(PALE_AXOLOTL_KEY));

    public static final EntityType<VampireBat> VAMPIRE_BAT = Registry.register(Registries.ENTITY_TYPE, Identifier.of(PaleWorld.MOD_ID, "vampire_bat"),
            FabricEntityType.Builder.createLiving(VampireBat::new, SpawnGroup.MONSTER, builder -> builder).dimensions(.25F,.5F).build(VAMPIRE_BAT_KEY));


    public static void init(){

    }
}
