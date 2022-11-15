package mellohi138.netherized.objects.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockWallBase extends BlockWall {
	private final IBlockState mainState;
	
	public BlockWallBase(String name, IBlockState mainState) {
		super(mainState.getBlock());
		Block mainBlock = mainState.getBlock();
		this.mainState = mainState;
		
		this.setTranslationKey(name);
		this.setRegistryName(name);
		this.setCreativeTab(mainBlock.getCreativeTab());
        this.setHarvestLevel(mainBlock.getHarvestTool(mainState), mainBlock.getHarvestLevel(mainState));
	}
	
	public BlockWallBase(IBlockState mainState) {
		super(mainState.getBlock());
		Block mainBlock = mainState.getBlock();
		this.mainState = mainState;
		
		this.setTranslationKey(mainBlock.getRegistryName().getPath() + "_wall");
		this.setRegistryName(mainBlock.getRegistryName().getPath() + "_wall");
		this.setCreativeTab(mainBlock.getCreativeTab());
        this.setHarvestLevel(mainBlock.getHarvestTool(mainState), mainBlock.getHarvestLevel(mainState));
	}
	
	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)   {    
		return this.mainState.getMapColor(worldIn, pos);
	}
	
	@Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this));
    }
}
