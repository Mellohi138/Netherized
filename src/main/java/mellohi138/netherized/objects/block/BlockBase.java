package mellohi138.netherized.objects.block;

import mellohi138.netherized.Netherized;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBase extends Block {
	private final boolean isBeaconBase;
	
	public BlockBase(String name, Material material, MapColor color, String usedTool, int toolStrength, SoundType soundType, CreativeTabs tab) {
		this(name, material, color, usedTool, toolStrength, soundType, tab, false);
	}
	
	public BlockBase(String name, Material material, MapColor color, String usedTool, int toolStrength, SoundType soundType, CreativeTabs tab, boolean isBeaconBase) {
		super(material, color);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
        this.setHarvestLevel(usedTool, toolStrength);
        this.setSoundType(soundType);
        
        this.isBeaconBase = isBeaconBase;
	}
	
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
    	return this.isBeaconBase;
    }
}