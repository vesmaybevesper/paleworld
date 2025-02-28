package vesper.pw.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;

public class CustomSounds {
    private CustomSounds(){

    }

    public static final SoundEvent PALE_CAVE_ATMOS = registerSounds("pale-cave-ambi");
    public static final SoundEvent ENGINE_LOOP = registerSounds("engine");

    public static SoundEvent registerSounds(String id) {
        Identifier identifier = Identifier.of(PaleWorld.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void init(){

    }
}
