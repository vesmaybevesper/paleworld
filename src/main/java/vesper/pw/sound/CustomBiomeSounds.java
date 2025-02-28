package vesper.pw.sound;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvent;

public class CustomBiomeSounds extends BiomeMoodSound {
 //   public static final BiomeMoodSound PALE_CAVE_MOOD = new BiomeMoodSound(CustomSounds.PALE_CAVE_ATMOS, 6000, 8, (double) 2.0f);

    public CustomBiomeSounds(RegistryEntry<SoundEvent> sound, int cultivationTicks, int spawnRange, double extraDistance) {
        super(sound, cultivationTicks, spawnRange, extraDistance);
    }

}
