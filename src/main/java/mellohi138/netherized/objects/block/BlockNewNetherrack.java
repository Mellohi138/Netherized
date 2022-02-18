package mellohi138.netherized.objects.block;

import java.util.Random;

import mellohi138.netherized.init.NetherizedBlocks;
import mellohi138.netherized.init.NetherizedSounds;
import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNewNetherrack extends BlockNetherrack implements IGrowable {
	public BlockNewNetherrack() {
		super();
		this.setTranslationKey("hellrock");
		this.setRegistryName("minecraft", "netherrack");
        this.setHarvestLevel("pickaxe", 0);
        this.setSoundType(NetherizedSounds.SOUND_TYPE_NETHERRACK);
        this.setHardness(0.4F);
        this.setResistance(0.4F);
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		if(worldIn.getBlockState(pos.up()).isTopSolid()) {
			return false;
		} else {
			for(BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
				if (worldIn.getBlockState(blockPos).getBlock() == NetherizedBlocks.CRIMSON_NYLIUM || worldIn.getBlockState(blockPos).getBlock() == NetherizedBlocks.WARPED_NYLIUM) {
					return true;
				}
			}
		}
		return false; 
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
	      boolean crimsonFlag = false;
	      boolean warpedFlag = false;
	      
	      for(BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
	    	  IBlockState blockState = worldIn.getBlockState(blockPos);
	    	  if(blockState.getBlock() == NetherizedBlocks.CRIMSON_NYLIUM) {
	    		  crimsonFlag = true;
	    	  }
	    	  
	    	  if(blockState.getBlock() == NetherizedBlocks.WARPED_NYLIUM) {
	    		  warpedFlag = true;
	    	  }
	    	  
	    	  if(crimsonFlag && warpedFlag) {
	    		  break;
	    	  }
	      }
	      
	      if (crimsonFlag && warpedFlag) {
	    	  worldIn.setBlockState(pos, rand.nextBoolean() ? NetherizedBlocks.CRIMSON_NYLIUM.getDefaultState() : NetherizedBlocks.WARPED_NYLIUM.getDefaultState(), 3);
	      } else if (crimsonFlag) {
	    	  worldIn.setBlockState(pos, NetherizedBlocks.CRIMSON_NYLIUM.getDefaultState(), 3);
	      } else if (warpedFlag) {
	    	  worldIn.setBlockState(pos, NetherizedBlocks.WARPED_NYLIUM.getDefaultState(), 3);
	      }
	}
}