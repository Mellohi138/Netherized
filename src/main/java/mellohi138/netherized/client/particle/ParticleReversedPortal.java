package mellohi138.netherized.client.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticlePortal;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**Code taken from forge 1.16 to make it as accurate as possible.**/
@SideOnly(Side.CLIENT)
public class ParticleReversedPortal extends ParticlePortal {
	public ParticleReversedPortal(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.particleScale = (float)((double)this.particleScale * 1.5D);
		this.particleMaxAge = (int)(Math.random() * 2.0D) + 60;
	}

    public void renderParticle(BufferBuilder bufferIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        float f1 = 1.0F - ((float)this.particleAge + partialTicks) / ((float)this.particleMaxAge * 1.5F);
        this.particleScale = this.particleScale * f1;
        
        super.renderParticle(bufferIn, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }
    
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.particleAge++ >= this.particleMaxAge) {
           this.setExpired();
        } else {
           float f = (float)this.particleAge / (float)this.particleMaxAge;
           this.posX += this.motionX * (double)f;
           this.posY += this.motionY * (double)f;
           this.posZ += this.motionZ * (double)f;
        }
    }
    
    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
    	public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
    		return new ParticleReversedPortal(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
    	}
    }
}
