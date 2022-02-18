package mellohi138.netherized.objects.block;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.init.NetherizedBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBase extends Block {
	public BlockBase(String name, Material material, MapColor color, String usedTool, int toolStrength, SoundType soundType, CreativeTabs tab) {
		super(material, color);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
        this.setHarvestLevel(usedTool, toolStrength);
        this.setSoundType(soundType);
	}
	
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
    	return this == NetherizedBlocks.NETHERITE_BLOCK;
    }
}