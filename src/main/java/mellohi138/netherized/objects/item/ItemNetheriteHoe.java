package mellohi138.netherized.objects.item;

import mellohi138.netherized.Netherized;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class ItemNetheriteHoe extends ItemHoe {
	public ItemNetheriteHoe(String name, ToolMaterial material, CreativeTabs tab) {
		super(material);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
	}
	
	//Makes the item repairable. Put this here because for some reason it didn't exist in the original class file for the hoes.
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        ItemStack mat = this.toolMaterial.getRepairItemStack();
        if (!mat.isEmpty() && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }
}
