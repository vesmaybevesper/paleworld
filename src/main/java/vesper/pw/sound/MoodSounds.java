/*package vesper.pw.sound;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvent;

public class MoodSounds implements BiomeMoodSound {
    public static final Codec<BiomeMoodSound> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(SoundEvent.ENTRY_CODEC.fieldOf("sound").forGetter((sound) -> sound.sound),
                    Codec.INT.fieldOf("tick_delay").forGetter((sound) -> sound.cultivationTicks),
                    Codec.INT.fieldOf("block_search_extent").forGetter((sound) -> sound.spawnRange),
                    Codec.DOUBLE.fieldOf("offset").forGetter((sound) -> sound.extraDistance)).apply(instance, MoodSound::new));
    public static final MoodSounds PALE_CAVE;
    private final RegistryEntry<SoundEvent> sound;
    private final int cultivationTicks;
    private final int spawnRange;
    private final double extraDistance;

    public MoodSounds(RegistryEntry<SoundEvent> sound, int cultivationTicks, int spawnRange, double extraDistance) {
        this.sound = sound;
        this.cultivationTicks = cultivationTicks;
        this.spawnRange = spawnRange;
        this.extraDistance = extraDistance;
    }

    public RegistryEntry<SoundEvent> getSound() {
        return this.sound;
    }

    public int getCultivationTicks() {
        return this.cultivationTicks;
    }

    public int getSpawnRange() {
        return this.spawnRange;
    }

    public double getExtraDistance() {
        return this.extraDistance;
    }


    static {
        PALE_CAVE = new MoodSounds(SoundEvents.PALE_CAVE_MOOD, 6000, 8, (double) 2.0f)
    }
}*/
