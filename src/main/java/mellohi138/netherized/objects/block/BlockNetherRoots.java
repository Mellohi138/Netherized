package mellohi138.netherized.objects.block;

import java.util.Random;

import javax.annotation.Nullable;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.init.NetherizedBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockNetherRoots extends BlockBush implements IShearable {
	public BlockNetherRoots(String name, Material material, MapColor color, SoundType soundType, CreativeTabs tab) {
		super(material, color);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
        this.setSoundType(soundType);
	}
	
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
    	return true;
    }
	
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }
    
    @SuppressWarnings("static-access")
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS) {
            player.addStat(StatList.getBlockStats(this));
            if (this == NetherizedBlocks.CRIMSON_ROOTS) {
            	this.spawnAsEntity(worldIn, pos, new ItemStack(NetherizedBlocks.CRIMSON_ROOTS, 1, 0));
            } else if (this == NetherizedBlocks.WARPED_ROOTS) {
            	this.spawnAsEntity(worldIn, pos, new ItemStack(NetherizedBlocks.WARPED_ROOTS, 1, 0));
            } else if (this == NetherizedBlocks.WARPED_SPROUTS) {
            	this.spawnAsEntity(worldIn, pos, new ItemStack(NetherizedBlocks.WARPED_SPROUTS, 1, 0));
            }
        }
        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }
	
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && this.canStay(worldIn, pos);
    }
    
    public boolean canStay(World worldIn, BlockPos pos) {
        IBlockState soil = worldIn.getBlockState(pos.down());
        if (this == NetherizedBlocks.CRIMSON_ROOTS) {
            return soil.getBlock() == NetherizedBlocks.CRIMSON_NYLIUM;
        } else if (this == NetherizedBlocks.WARPED_ROOTS || this == NetherizedBlocks.WARPED_SPROUTS) {
            return soil.getBlock() == NetherizedBlocks.WARPED_NYLIUM;
        }
		return false;
    }
    
    @Deprecated
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        worldIn.scheduleUpdate(pos, this, 1);
    }
    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    	if (!this.canStay(worldIn, pos)) {
    		worldIn.destroyBlock(pos, true);
        }
    }

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        if (this == NetherizedBlocks.CRIMSON_ROOTS) {
        	return NonNullList.withSize(1, new ItemStack(NetherizedBlocks.CRIMSON_ROOTS, 1, 0));
        } else if (this == NetherizedBlocks.WARPED_ROOTS) {  
        	return NonNullList.withSize(1, new ItemStack(NetherizedBlocks.WARPED_ROOTS, 1, 0));
        } else if (this == NetherizedBlocks.WARPED_SPROUTS) {  
        	return NonNullList.withSize(1, new ItemStack(NetherizedBlocks.WARPED_SPROUTS, 1, 0));
        }
		return null;
	}
}
