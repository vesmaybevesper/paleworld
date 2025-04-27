package vesper.pw.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;

public class ParticleTypes {

    public static final SimpleParticleType MOSS_PARTICLE = registerParticle("moss_particle", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType){
            return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(PaleWorld.MOD_ID, name), particleType);
    }

    public static void register(){

    }
}
