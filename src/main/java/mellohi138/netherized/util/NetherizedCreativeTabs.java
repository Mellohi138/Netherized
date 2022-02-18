package mellohi138.netherized.util;

import mellohi138.netherized.Netherized;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class NetherizedCreativeTabs extends CreativeTabs {
	private final int tabID;
	
	public NetherizedCreativeTabs(int tabID) {
		super(Netherized.MODID + NetherizedCreativeTabs.getName(tabID));
		
		this.tabID = tabID;
	}
	
    @Override
    public ItemStack createIcon() {
    	if(tabID == 0) {
    		return new ItemStack(Items.GOLD_INGOT);
    	} else if(tabID == 1) {
    		return new ItemStack(Blocks.NETHERRACK);
    	}
    	return new ItemStack(Items.AIR);
    }
    
    private static String getName(int ID) {
        if(ID == 0) {
            return "_items";
        } else if (ID == 1) {
            return "_blocks";
        }
        return "_blank";
    }
}