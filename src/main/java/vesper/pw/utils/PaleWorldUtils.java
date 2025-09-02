package vesper.pw.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.apache.commons.lang3.Validate;
import vesper.pw.biomes.PaleWorldBiomes;

import java.util.Objects;

public class PaleWorldUtils {
    public double getPracticalViewDistance(Entity entity){
        World world = MinecraftClient.getInstance().world;
        Objects.requireNonNull(world);
        RegistryEntry<Biome> biomeRegistryEntry = world.getBiome(entity.getBlockPos());
        if (biomeRegistryEntry != BiomeKeys.PALE_GARDEN && biomeRegistryEntry != PaleWorldBiomes.PALE_VALLEY){
            return MinecraftClient.getInstance().worldRenderer.getViewDistance();
        }

        return varHolder.fogEnvEnd - 0.05D;
    }
}
