package vesper.pw.registry;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;

public class ParticleRegistry {
    public static final SimpleParticleType FOG_PARTICLE = FabricParticleTypes.simple(true);

    public static SimpleParticleType registerParticle(String name, ParticleFactoryRegistry.PendingParticleFactory<SimpleParticleType> factory){
        SimpleParticleType particle = FabricParticleTypes.simple();
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(PaleWorld.MOD_ID, name), particle);
        ParticleFactoryRegistry.getInstance().register(particle, factory);
        return particle;
    }

    public static void register(){
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(PaleWorld.MOD_ID, "fog_particle"), FOG_PARTICLE);
    }
}
