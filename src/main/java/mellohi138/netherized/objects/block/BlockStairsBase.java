package mellohi138.netherized.objects.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockStairsBase extends BlockStairs {
	private final IBlockState mainState;
	
	public BlockStairsBase(IBlockState mainState) {
		super(mainState);
		Block mainBlock = mainState.getBlock();
		this.mainState = mainState;
		
		this.setTranslationKey(mainBlock.getRegistryName().getPath() + "_stairs");
		this.setRegistryName(mainBlock.getRegistryName().getPath() + "_stairs");
		this.setCreativeTab(mainBlock.getCreativeTab());
		this.setHarvestLevel(mainBlock.getHarvestTool(mainState), mainBlock.getHarvestLevel(mainState));
		
		this.useNeighborBrightness = true;
	}
	
	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)   {    
		return this.mainState.getMapColor(worldIn, pos);
	}
}
