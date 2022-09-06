package mellohi138.netherized.util;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.init.NetherizedBlocks;
import net.minecraft.creativetab.CreativeTabs;
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
    	switch(this.tabID) {
    	case 0:
    		return new ItemStack(Items.GOLD_INGOT);
    	case 1:
    		return new ItemStack(NetherizedBlocks.CRIMSON_NYLIUM);
    	}
    	return new ItemStack(Items.AIR);
    }
    
    private static String getName(int ID) {
    	switch(ID) {
    	case 0:
            return "_items";
    	case 1:
            return "_blocks";
    	}
        return "_blank";
    }
}