package mellohi138.netherized.objects.block;

import mellohi138.netherized.init.NetherizedSounds;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockNewNetherWartBlock extends Block {
	public BlockNewNetherWartBlock() {
		super(Material.GLASS, MapColor.RED);
		this.setTranslationKey("netherWartBlock");
		this.setRegistryName("minecraft", "nether_wart_block");
        this.setSoundType(NetherizedSounds.SOUND_TYPE_NETHER_WART);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setHardness(1.0F);
	}
}