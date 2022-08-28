package mellohi138.netherized.client.particle;

import mellohi138.netherized.init.NetherizedParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleSoulFlame extends ParticleFlame {
    
	public ParticleSoulFlame(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        
		this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(NetherizedParticles.SOUL_FIRE_FLAME.toString()));
	}
	
	@Override
    public void setParticleTextureIndex(int particleTextureIndex) {  
		this.particleTextureIndexX = particleTextureIndex % 16;  
		this.particleTextureIndexY = particleTextureIndex / 16;
    }
	
	@Override
    public int getFXLayer() {
        return 1;
    }
	
	public static class Factory implements IParticleFactory {
		@Override
		public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new ParticleSoulFlame(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		}
	}
}
