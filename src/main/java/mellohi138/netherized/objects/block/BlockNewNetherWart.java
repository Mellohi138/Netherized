package mellohi138.netherized.objects.block;

import mellohi138.netherized.init.NetherizedItems;
import mellohi138.netherized.init.NetherizedSounds;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class BlockNewNetherWart extends BlockNetherWart {
    public BlockNewNetherWart()  {
        super();      
        this.setSoundType(NetherizedSounds.SOUND_TYPE_NETHER_WART);
        this.setRegistryName("minecraft", "nether_wart");
        this.setTranslationKey("netherStalk");
        this.setCreativeTab(CreativeTabs.MISC);
        
		NetherizedItems.ITEM_LIST.add(new ItemBlock(this).setRegistryName("minecraft", "nether_wart"));
    }
}
