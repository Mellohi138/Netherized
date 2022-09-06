package mellohi138.netherized.enums;

import mellohi138.netherized.client.particle.*;
import net.minecraft.client.particle.IParticleFactory;

public enum EnumNetherizedParticles {
	REVERSED_PORTAL(),
	CRYING_OBSIDIAN_TEAR(),
	CRIMSON_SPORE(),
	WARPED_SPORE(),
	WHITE_ASH(),
	SOUL_FLAME(),
	SOUL();
    
    public IParticleFactory getParticleFactory() {
    	switch(this) {
		case REVERSED_PORTAL:
			return new ParticleReversedPortal.Factory();
		case CRYING_OBSIDIAN_TEAR:
			return new ParticleCryingObsidianTear.Factory();
		case CRIMSON_SPORE:
			return new ParticleNetherAmbience.CrimsonFactory();
		case WARPED_SPORE:
			return new ParticleNetherAmbience.WarpedFactory();
		case WHITE_ASH:
			return new ParticleNetherAmbience.BasaltFactory();
		case SOUL_FLAME:
			return new ParticleSoulFlame.Factory();
		case SOUL:
			return new ParticleSoul.Factory();
    	}
		return null;
    }
}
