package mellohi138.netherized.objects.block;

import mellohi138.netherized.Netherized;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockFenceBase extends BlockFence {
	public BlockFenceBase(String name, Material materialIn, MapColor mapColorIn, CreativeTabs tab) {
		super(materialIn, mapColorIn);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
	}
	
	@Override
    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
		IBlockState state = worldIn.getBlockState(pos);
		BlockFaceShape shape = state.getBlockFaceShape(worldIn, pos, facing);
		boolean materialFlag = state.getMaterial() == this.material || state.getMaterial() == Material.WOOD; //We want this to connect with the other wood gates
		boolean flag = shape == BlockFaceShape.MIDDLE_POLE && (materialFlag || state.getBlock() instanceof BlockFenceGate);
		return !isExcepBlockForAttachWithPiston(state.getBlock()) && shape == BlockFaceShape.SOLID || flag;
    }
}
