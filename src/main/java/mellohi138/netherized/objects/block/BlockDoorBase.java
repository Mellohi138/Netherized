package mellohi138.netherized.objects.block;

import java.util.Random;

import mellohi138.netherized.Netherized;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDoorBase extends BlockDoor {
	private final MapColor color;
	
	public BlockDoorBase(String name, Material materialIn, MapColor colorIn, CreativeTabs tab) {
		super(materialIn);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
		this.setHardness(3.0F);
		this.setResistance(3.0F);
		this.setHarvestLevel("axe", 0);
		
		this.color = colorIn;
	}
	
	@Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(Item.getByNameOrId(this.getRegistryName().toString()));
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : Item.getByNameOrId(this.getRegistryName().toString());
	}
	
	@Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    	return this.color;
    }
}
