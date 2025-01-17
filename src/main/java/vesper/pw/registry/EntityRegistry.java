/*
package vesper.pw.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.AxolotlEntity;
import vesper.pw.mobs.PaleAxolotl.PaleAxolotl;

import java.util.function.Supplier;

public class EntityRegistry {
    public static void init(){}

    public static final Supplier<EntityType<AxolotlEntity>> PALE_AXOLOTL = registerEntity("", PaleAxolotl::new, .7f, 1.3f, 0x1F1F1F, 0x0D0D0D);

    private static <T extends Entity> Supplier<EntityType<T>> registerEntity(String name, EntityType.EntityFactory<T> entity, float width, float height, int primaryEggColor, int secondaryEggColor){
        return registerEntity(name, () -> FabricEntityType.Builder.createMob(entity, SpawnGroup.AXOLOTLS).dimensions(width, height).build(name));
    }
}
*/
