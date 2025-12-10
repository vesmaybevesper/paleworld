package vesper.pw.sound;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.BiomeEffectSoundPlayer;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvent;

public class CustomBiomeSounds extends BiomeEffectSoundPlayer {
    public CustomBiomeSounds(ClientPlayerEntity player, SoundManager soundManager) {
        super(player, soundManager);
    }
    //   public static final BiomeMoodSound PALE_CAVE_MOOD = new BiomeMoodSound(CustomSounds.PALE_CAVE_ATMOS, 6000, 8, (double) 2.0f);



}
