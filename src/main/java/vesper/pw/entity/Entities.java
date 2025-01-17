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

import java.util.function.UnaryOperator;

public class Entities {
    private static final RegistryKey<EntityType<?>> PALE_AXOLOTL_KEY = RegistryKey.of(
            Registries.ENTITY_TYPE.getKey(),
            Identifier.of(PaleWorld.MOD_ID, "pale_axolotl")
    );
    public static final EntityType<PaleAxolotl> PALE_AXOLOTL = Registry.register(Registries.ENTITY_TYPE, Identifier.of(PaleWorld.MOD_ID, "pale_axolotl"),
            FabricEntityType.Builder.createLiving(PaleAxolotl::new,SpawnGroup.WATER_AMBIENT, builder -> builder).dimensions(1,1).build(PALE_AXOLOTL_KEY));

    public static void init(){

    }
}
