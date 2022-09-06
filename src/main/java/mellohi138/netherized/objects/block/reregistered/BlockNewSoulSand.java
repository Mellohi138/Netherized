package mellohi138.netherized.objects.block.reregistered;

import mellohi138.netherized.init.NetherizedSounds;
import mellohi138.netherized.util.ModUtils;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNewSoulSand extends BlockSoulSand {
	public BlockNewSoulSand() {
		this.setRegistryName("minecraft", "soul_sand");
		this.setTranslationKey("hellsand");
		
		this.setHardness(0.5F);
		this.setResistance(0.5F);
		this.setSoundType(NetherizedSounds.SOUND_TYPE_SOUL_SAND);
	}
	
	@Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
    	if(entityIn instanceof EntityLivingBase) {
    		EntityLivingBase livingEntity = (EntityLivingBase)entityIn;
    		if(!ModUtils.hasSoulSpeed(livingEntity)) {
    	        entityIn.motionX *= 0.4D;
    	        entityIn.motionZ *= 0.4D;
    		}
    	} else {
            entityIn.motionX *= 0.4D;
            entityIn.motionZ *= 0.4D;
    	}
    }
}
