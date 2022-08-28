package mellohi138.netherized.init;

import net.minecraft.util.ResourceLocation;
import java.util.ArrayList;
import java.util.List;

import mellohi138.netherized.Netherized;

public class NetherizedParticles {
	public static final List<ResourceLocation> PARTICLE_LIST = new ArrayList<ResourceLocation>();

	public static final ResourceLocation SOUL_FIRE_FLAME = addParticle(new ResourceLocation(Netherized.MODID, "particles/soul_fire_flame"));
	
	public static ResourceLocation addParticle(ResourceLocation particle) {
		PARTICLE_LIST.add(particle);
		return particle;
	}
}
