package mellohi138.netherized.objects.block;

import mellohi138.netherized.Netherized;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockPressurePlateBase extends BlockPressurePlate {
	private final MapColor mapColor;
	
	public BlockPressurePlateBase(String nameIn, Material materialIn, MapColor mapColorIn, CreativeTabs tabIn) {
		super(materialIn, Sensitivity.MOBS);
		this.setTranslationKey(nameIn);
		this.setRegistryName(Netherized.MODID, nameIn);
		this.setCreativeTab(tabIn);
		
		this.mapColor = mapColorIn;
	}
	
	@Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return this.mapColor;
    }
}
