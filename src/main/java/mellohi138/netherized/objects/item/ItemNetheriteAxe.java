package mellohi138.netherized.objects.item;

import mellohi138.netherized.Netherized;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

public class ItemNetheriteAxe extends ItemAxe {
	public ItemNetheriteAxe(String name, ToolMaterial materialIn, CreativeTabs tab) {
		super(materialIn, materialIn.getAttackDamage() + 5, -3.0F);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
	}
}
