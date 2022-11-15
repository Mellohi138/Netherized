package mellohi138.netherized.objects.block;

import mellohi138.netherized.init.NetherizedItems;
import mellohi138.netherized.init.NetherizedSounds;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLodestone extends BlockBase {
	public BlockLodestone(String name, Material material, MapColor color, String usedTool, int toolStrength, SoundType soundType, CreativeTabs tab) {
		super(name, material, color, usedTool, toolStrength, soundType, tab);
	}

    @Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		
		if(!playerIn.isSneaking() && (stack.getItem() == Items.COMPASS || stack.getItem() == NetherizedItems.LODESTONE_COMPASS)) {
			ItemStack lodestoneCompass = NetherizedItems.LODESTONE_COMPASS.getDefaultInstance();
			lodestoneCompass.setTagCompound(this.setupLodestoneCompass(pos, worldIn));
			
			if(!playerIn.capabilities.isCreativeMode) stack.shrink(1);
			playerIn.addItemStackToInventory(lodestoneCompass);
			worldIn.playSound(playerIn, pos, NetherizedSounds.BLOCK_LODESTONE_LOCK, SoundCategory.BLOCKS, 1.0F, 1.0F);
			return true;
		}
		
		return false;
    }
    
    private NBTTagCompound setupLodestoneCompass(BlockPos pos, World worldIn) {
		NBTTagCompound lodestoneNBT = new NBTTagCompound();
		lodestoneNBT.setIntArray("LodestonePos", new int[] {pos.getX(), pos.getY(), pos.getZ()});
		lodestoneNBT.setInteger("LodestoneDimension", worldIn.provider.getDimension());
		return lodestoneNBT;
    }
}
