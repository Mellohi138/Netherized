package mellohi138.netherized.objects.item;

import mellohi138.netherized.Netherized;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class ItemRecordBase extends ItemRecord {
	public ItemRecordBase(String name, SoundEvent soundIn, CreativeTabs tab) {
		super(name, soundIn);
		this.setTranslationKey("record");
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
	}
}
