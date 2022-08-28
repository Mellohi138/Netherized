package mellohi138.netherized.proxy;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.client.init.EntityRenderRegistry;
import mellohi138.netherized.client.init.NetherizedTEISR;
import mellohi138.netherized.client.particle.ParticleCryingObsidianTear;
import mellohi138.netherized.client.particle.ParticleNetherSpores;
import mellohi138.netherized.client.particle.ParticleReversedPortal;
import mellohi138.netherized.client.particle.ParticleSoul;
import mellohi138.netherized.client.particle.ParticleSoulFlame;
import mellohi138.netherized.client.render.entity.RenderHoveringInferno;
import mellohi138.netherized.client.render.entity.RenderStrider;
import mellohi138.netherized.enums.EnumNetherizedParticles;
import mellohi138.netherized.init.NetherizedItems;
import mellohi138.netherized.objects.entity.hostile.EntityHoveringInferno;
import mellohi138.netherized.objects.entity.passive.EntityStrider;
import mellohi138.netherized.util.interfaces.IProxy;
import mellohi138.netherized.util.interfaces.ITEISRModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Netherized.MODID, value = Side.CLIENT)
public class ClientProxy implements IProxy {
	@Override
	public void preInit() {
		this.registerEntityModels();
	}
	
	@Override
	public void init() {
	}

	@Override
	public void postInit() {
		for(Item item : NetherizedItems.ITEM_LIST) {
			if(item instanceof ITEISRModel) {
				item.setTileEntityItemStackRenderer(new NetherizedTEISR());
			}
		}
	}
	
	private void registerEntityModels() {	
		RenderingRegistry.registerEntityRenderingHandler(EntityStrider.class, new EntityRenderRegistry<EntityStrider>() {
			@Override
			public Render<? super EntityStrider> createRenderFor(RenderManager manager) {
				return new RenderStrider(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityHoveringInferno.class, new EntityRenderRegistry<EntityHoveringInferno>( ) {
			@Override
			public Render<? super EntityHoveringInferno> createRenderFor(RenderManager manager) {
				return new RenderHoveringInferno(manager);
			}
		});
    }
    
	@Override
    public void spawnParticle(EnumNetherizedParticles particleEnum, World worldIn, double x, double y, double z, double motX, double motY, double motZ) {
		if(worldIn == null) worldIn = Minecraft.getMinecraft().world;
		
		Particle particleEffect = null;
		
		switch(particleEnum) {
		case REVERSED_PORTAL:
			particleEffect = new ParticleReversedPortal.Factory().createParticle(0, worldIn, x, y, z, motX, motY, motZ);
			break;
		case CRYING_OBSIDIAN_TEAR:
			particleEffect = new ParticleCryingObsidianTear.Factory().createParticle(0, worldIn, x, y, z, motX, motY, motZ);
			break;
		case CRIMSON_SPORE:
			particleEffect = new ParticleNetherSpores.CrimsonFactory().createParticle(0, worldIn, x, y, z, motX, motY, motZ);
			break;
		case WARPED_SPORE:
			particleEffect = new ParticleNetherSpores.WarpedFactory().createParticle(0, worldIn, x, y, z, motX, motY, motZ);
			break;
		case SOUL_FLAME:
			particleEffect = new ParticleSoulFlame.Factory().createParticle(0, worldIn, x, y, z, motX, motY, motZ);
			break;
		case SOUL:
			particleEffect = new ParticleSoul.Factory().createParticle(0, worldIn, x, y, z, motX, motY, motZ);
			break;
		}
        
		if(particleEffect != null) {
			Minecraft.getMinecraft().effectRenderer.addEffect(particleEffect);
		}           	
    }
}