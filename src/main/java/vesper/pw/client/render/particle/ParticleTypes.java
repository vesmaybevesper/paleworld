package vesper.pw.client.render.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import vesper.pw.PaleWorld;

public class ParticleTypes {

    public static final SimpleParticleType MOSS_PARTICLE = registerParticle("moss_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType FOG_PARTICLE = registerParticle("fog_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType RAFFLESIA_PARTICLE = registerParticle("rafflesia_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType LOST_SOUL_AURA = registerParticle("lost_soul_aura", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType){
            return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(PaleWorld.MOD_ID, name), particleType);
    }

    public static void register(){

    }
}
