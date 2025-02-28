package vesper.pw.registry.tags;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public interface EntityTypeTags {
    TagKey<EntityType<?>> PRISMATIC = of("prismatic-able");

    private static TagKey<EntityType<?>> of(String id) {
        return TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.ofVanilla(id));
    }
}
