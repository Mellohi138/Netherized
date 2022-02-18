package mellohi138.netherized.objects.block;

import mellohi138.netherized.Netherized;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockRotatedPillarBase extends BlockRotatedPillar {
	public BlockRotatedPillarBase(String name, Material material, MapColor color, String usedTool, int toolStrength, SoundType soundType, CreativeTabs tab) {
		super(material, color);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
        this.setHarvestLevel(usedTool, toolStrength);
        this.setSoundType(soundType);
	}
}
