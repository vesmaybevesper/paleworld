package dev.vesper.paleworld.common.client.render.particle;
//? fabric{
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
//?}
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import static dev.vesper.paleworld.PaleWorld.MOD_ID;

public class ParticleTypes {

	//? fabric{
	public static final SimpleParticleType MOSS_PARTICLE = registerParticle("moss_particle", FabricParticleTypes.simple());
	public static final SimpleParticleType FOG_PARTICLE = registerParticle("fog_particle", FabricParticleTypes.simple());
	public static final SimpleParticleType RAFFLESIA_PARTICLE = registerParticle("rafflesia_particle", FabricParticleTypes.simple());
	public static final SimpleParticleType LOST_SOUL_AURA = registerParticle("lost_soul_aura", FabricParticleTypes.simple());
	//?}

	//? neoforge{
	/*private static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, MOD_ID);

	public static final Supplier<SimpleParticleType> MOSS_PARTICLE = PARTICLE_TYPES.register("moss_particle", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> FOG_PARTICLE = PARTICLE_TYPES.register("fog_particle", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> RAFFLESIA_PARTICLE = PARTICLE_TYPES.register("rafflesia_particle", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> LOST_SOUL_AURA = PARTICLE_TYPES.register("lost_soul_aura", () -> new SimpleParticleType(false));
	*///?}
	private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType){
		return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, name), particleType);

	}

	public static void register(){}
}
