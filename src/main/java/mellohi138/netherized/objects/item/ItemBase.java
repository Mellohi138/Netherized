package mellohi138.netherized.objects.item;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.init.NetherizedItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBase extends Item {	
	public ItemBase(String name, CreativeTabs tab) {
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
	}
	
    public boolean isBeaconPayment(ItemStack stack) {
        return this == NetherizedItems.NETHERITE_INGOT;
    }
}