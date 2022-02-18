package mellohi138.netherized.enums;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

public enum EnumNetherizedParticles {
	REVERSED_PORTAL("reversed_portal", 0),
	CRYING_OBSIDIAN_TEAR("crying_obsidian_tear", 1),
	CRIMSON_SPORE("crimson_spore", 2),
	WARPED_SPORE("warped_spore", 3),
	SOUL_FIRE_FLAME("soul_fire_flame", 4),
	SOUL("soul", 5);
	
    private final String particleName;
    private final int particleID;
    private static final Map<Integer, EnumNetherizedParticles> PARTICLES = Maps.<Integer, EnumNetherizedParticles>newHashMap();
    private static final Map<String, EnumNetherizedParticles> BY_NAME = Maps.<String, EnumNetherizedParticles>newHashMap();

    private EnumNetherizedParticles(String particleNameIn, int particleIDIn) {
        this.particleName = particleNameIn;
        this.particleID = particleIDIn;
    }

    public static Set<String> getParticleNames() {
        return BY_NAME.keySet();
    }

    public String getParticleName() {
        return this.particleName;
    }

    public int getParticleID() {
        return this.particleID;
    }

    @Nullable
    public static EnumNetherizedParticles getParticleFromId(int particleId) {
        return PARTICLES.get(Integer.valueOf(particleId));
    }

    @Nullable
    public static EnumNetherizedParticles getByName(String nameIn) {
        return BY_NAME.get(nameIn);
    }

    static {
        for (EnumNetherizedParticles enumparticletypes : values()) {
            PARTICLES.put(Integer.valueOf(enumparticletypes.getParticleID()), enumparticletypes);
            BY_NAME.put(enumparticletypes.getParticleName(), enumparticletypes);
        }
    }
}
