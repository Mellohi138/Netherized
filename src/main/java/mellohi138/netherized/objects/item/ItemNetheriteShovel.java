package mellohi138.netherized.objects.item;

import mellohi138.netherized.Netherized;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;

public class ItemNetheriteShovel extends ItemSpade {
	public ItemNetheriteShovel(String name, ToolMaterial material, CreativeTabs tab) {
		super(material);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
	}
}
