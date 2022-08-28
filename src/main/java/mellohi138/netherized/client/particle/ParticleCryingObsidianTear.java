package mellohi138.netherized.client.particle;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleDrip;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleCryingObsidianTear extends ParticleDrip {
	public ParticleCryingObsidianTear(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, null);
        this.setRBGColorF(0.51171875F, 0.03125F, 0.890625F);
        
        this.particleMaxAge = 100;
        this.particleGravity *= 0.01F;
	}
	
	@Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
  
        this.particleRed = 0.51171875F;
        this.particleGreen = 0.03125F;
        this.particleBlue = 0.890625F;

        this.motionY -= (double)this.particleGravity;

        this.move(this.motionX, this.motionY, this.motionZ);
        
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.particleMaxAge-- <= 0) {
            this.setExpired();
        }

        if (this.onGround) {
        	this.setParticleTextureIndex(114);

            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }

        BlockPos blockPos = new BlockPos(this.posX, this.posY, this.posZ);
        IBlockState iBlockState = this.world.getBlockState(blockPos);
        Material material = iBlockState.getMaterial();

        if (material.isLiquid() || material.isSolid()) {
            double d0 = 0.0D;

            if (iBlockState.getBlock() instanceof BlockLiquid) {
                d0 = (double)BlockLiquid.getLiquidHeightPercent(((Integer)iBlockState.getValue(BlockLiquid.LEVEL)).intValue());
            }

            double d1 = (double)(MathHelper.floor(this.posY) + 1) - d0;

            if (this.posY < d1) {
                this.setExpired();
            }
        }
    }
	
	@Override
    public int getBrightnessForRender(float partialTick) {
        return 240;
    }
	
    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
    	public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
    		return new ParticleCryingObsidianTear(worldIn, xCoordIn, yCoordIn, zCoordIn);
    	}
    }
}
