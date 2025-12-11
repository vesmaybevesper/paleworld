package dev.vesper.paleworld.common.client.render.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import static dev.vesper.paleworld.PaleWorld.MOD_ID;

public class ParticleTypes {

	public static final SimpleParticleType MOSS_PARTICLE = registerParticle("moss_particle", FabricParticleTypes.simple());
	public static final SimpleParticleType FOG_PARTICLE = registerParticle("fog_particle", FabricParticleTypes.simple());
	public static final SimpleParticleType RAFFLESIA_PARTICLE = registerParticle("rafflesia_particle", FabricParticleTypes.simple());
	public static final SimpleParticleType LOST_SOUL_AURA = registerParticle("lost_soul_aura", FabricParticleTypes.simple());

	private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType){
		return Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, name), particleType);
	}
}
