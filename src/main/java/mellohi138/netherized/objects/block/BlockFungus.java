package mellohi138.netherized.objects.block;

import java.util.Random;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.init.NetherizedBlocks;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFungus extends BlockBush implements IGrowable {
	public BlockFungus(String name, Material material, MapColor color, SoundType soundType, CreativeTabs tab) {
		super(material, color);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
        this.setSoundType(soundType);
        
		this.setTickRandomly(true);
	}
	
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
    	return true;
    }
	
	@Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    	return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && this.canStay(worldIn, pos);
    }
    
    public boolean canStay(World worldIn, BlockPos pos) {
        IBlockState soil = worldIn.getBlockState(pos.down());
        if(this == NetherizedBlocks.CRIMSON_FUNGUS) {
            return soil.getBlock() == NetherizedBlocks.CRIMSON_NYLIUM;
        } else if(this == NetherizedBlocks.WARPED_FUNGUS) {
            return soil.getBlock() == NetherizedBlocks.WARPED_NYLIUM;
        }
        return false;
    }
    
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return false;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return (double)rand.nextFloat() < 0.4D;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
	}
}
