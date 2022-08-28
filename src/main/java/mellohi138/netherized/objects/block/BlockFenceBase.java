package mellohi138.netherized.objects.block;

import mellohi138.netherized.Netherized;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockFenceBase extends BlockFence {
	public BlockFenceBase(String name, Material materialIn, MapColor mapColorIn, CreativeTabs tab) {
		super(materialIn, mapColorIn);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
	}
}
