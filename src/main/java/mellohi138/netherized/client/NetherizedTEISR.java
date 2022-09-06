package mellohi138.netherized.client;

import mellohi138.netherized.init.NetherizedItems;
import mellohi138.netherized.util.interfaces.ITEISRModel;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NetherizedTEISR extends TileEntityItemStackRenderer {
    @Override
    @SideOnly(Side.CLIENT)
    public void renderByItem(ItemStack itemStackIn) {
    	for(Item item : NetherizedItems.ITEM_LIST) {
    		if(item instanceof ITEISRModel) {
				((ITEISRModel)item).registerTEISRModel();
    		}
    	}
    }
}
