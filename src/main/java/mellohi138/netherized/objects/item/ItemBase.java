package mellohi138.netherized.objects.item;

import mellohi138.netherized.Netherized;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBase extends Item {
	private final boolean isBeaconPayment;
	
	public ItemBase(String name, CreativeTabs tab) {
		this(name, tab, false);
	}
	
	public ItemBase(String name, CreativeTabs tab, boolean isBeaconPayment) {
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
		
		this.isBeaconPayment = isBeaconPayment;
	}
	
    public boolean isBeaconPayment(ItemStack stack) {
        return this.isBeaconPayment;
    }
}