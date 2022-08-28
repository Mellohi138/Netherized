package mellohi138.netherized.client.init;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.init.NetherizedBlocks;
import mellohi138.netherized.init.NetherizedItems;
import mellohi138.netherized.init.NetherizedParticles;
import mellohi138.netherized.util.interfaces.ICustomRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Netherized.MODID, value = Side.CLIENT)
public class ClientModelRegistry {
	@SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
		for(Item item : NetherizedItems.ITEM_LIST) {
			if(item instanceof ICustomRenderer) {
				((ICustomRenderer)item).registerModels();
			} else if(!(item instanceof ICustomRenderer)) {
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
			}
		}
        for(Block block : NetherizedBlocks.BLOCK_LIST) {
			if(block instanceof ICustomRenderer) {
				((ICustomRenderer)block).registerModels();
			} else if(!(block instanceof ICustomRenderer)) {
				ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Item.getItemFromBlock(block).getRegistryName(), "inventory"));
			}
        }
    }
	
	@SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent event) {
		for(ResourceLocation particleName : NetherizedParticles.PARTICLE_LIST) {
			event.getMap().registerSprite(particleName);
		}
	}
}
