package mellohi138.netherized.objects.block;

import java.util.Random;

import mellohi138.netherized.Netherized;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNetherNylium extends Block implements IGrowable {
	public BlockNetherNylium(String name, Material material, MapColor color, String usedTool, int toolStrength, SoundType soundType, CreativeTabs tab) {
		super(material, color);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
        this.setHarvestLevel(usedTool, toolStrength);
        this.setSoundType(soundType);
	}
	
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    	return Item.getItemFromBlock(Blocks.NETHERRACK);
    }

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		/*
	      IBlockState blockstate = worldIn.getBlockState(pos);
	      BlockPos blockpos = pos.up();
	      if (this == BlockInit.CRIMSON_NYLIUM) {
	         NetherVegetationFeature.func_236325_a_(worldIn, rand, blockpos, Features.Configs.CRIMSON_FOREST_VEGETATION_CONFIG, 3, 1);
	      } else if (this == BlockInit.WARPED_NYLIUM) {
	         NetherVegetationFeature.func_236325_a_(worldIn, rand, blockpos, Features.Configs.WARPED_FOREST_VEGETATION_CONFIG, 3, 1);
	         NetherVegetationFeature.func_236325_a_(worldIn, rand, blockpos, Features.Configs.NETHER_SPROUTS_CONFIG, 3, 1);
	         if (rand.nextInt(8) == 0) {
	            TwistingVineFeature.func_236423_a_(worldIn, rand, blockpos, 3, 1, 2);
	         }
	      } */
	}
}