package mellohi138.netherized.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import mellohi138.netherized.util.interfaces.ICustomRenderer;
import mellohi138.netherized.Netherized;
import mellohi138.netherized.client.particle.*;
import mellohi138.netherized.client.render.entity.*;
import mellohi138.netherized.init.NetherizedBlocks;
import mellohi138.netherized.init.NetherizedItems;
import mellohi138.netherized.objects.entity.passive.*;
import mellohi138.netherized.objects.entity.hostile.*;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Netherized.MODID)
public class ClientProxy extends CommonProxy {
	@SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {
		for(Item item : NetherizedItems.ITEM_LIST) {
			if(!(item instanceof ICustomRenderer)) {
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
			} else if(item instanceof ICustomRenderer) {
				((ICustomRenderer)item).registerModels();
			}
		}
        for(Block block : NetherizedBlocks.BLOCK_LIST) {
			if(!(block instanceof ICustomRenderer)) {
				ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Item.getItemFromBlock(block).getRegistryName(), "inventory"));
			} else if(block instanceof ICustomRenderer) {
				((ICustomRenderer)block).registerModels();
			}
        }
    }
	
	@Override
    @SideOnly(Side.CLIENT)
	public void registerCustomModel(Item item, int metadata) {
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	@SuppressWarnings("deprecation")
    public void registerEntityModels() {
		RenderManager renderManagerIn = Minecraft.getMinecraft().getRenderManager();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityStrider.class, new RenderStrider(renderManagerIn));
    }
    
	@Override
    @SideOnly(Side.CLIENT)
    public void spawnParticle(String particleName, double x, double y, double z, double motX, double motY, double motZ) {
        World worldIn = Minecraft.getMinecraft().world;
        if (worldIn == null) {
            return;
        }
        
        Particle particle = null;
        
        if (particleName == "reversed_portal") {
        	particle = new ParticleReversedPortal(worldIn, x, y, z, motX, motY, motZ);
        }
        
        if (particleName == "crying_obsidian_tear") {
            particle = new ParticleCryingObsidianTear(worldIn, x, y, z);
        }
        
        if (particleName == "crimson_spore") {
        	particle = null;
        }
        
        if (particleName == "warped_spore") {
        	particle = null;
        }
        
        if (particleName == "soul_fire_flame") {
        	particle = null;
        }
        
        if (particleName == "soul") {
        	particle = null;
        }
        
        if (particle != null) {
            Minecraft.getMinecraft().effectRenderer.addEffect(particle);
        }
    }
}