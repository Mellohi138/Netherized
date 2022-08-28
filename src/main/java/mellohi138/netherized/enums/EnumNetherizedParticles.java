package mellohi138.netherized.enums;

public enum EnumNetherizedParticles {
	REVERSED_PORTAL("reversed_portal", 0),
	CRYING_OBSIDIAN_TEAR("crying_obsidian_tear", 1),
	CRIMSON_SPORE("crimson_spore", 2),
	WARPED_SPORE("warped_spore", 3),
	SOUL_FLAME("soul_flame", 4),
	SOUL("soul", 5);
	
    private final String particleName;
    private final int particleID;

    private EnumNetherizedParticles(String particleNameIn, int particleIDIn) {
        this.particleName = particleNameIn;
        this.particleID = particleIDIn;
    }

    public String getParticleName() {
        return this.particleName;
    }

    public int getParticleID() {
        return this.particleID;
    }
}
