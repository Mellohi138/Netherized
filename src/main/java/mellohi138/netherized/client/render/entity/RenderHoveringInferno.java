package mellohi138.netherized.client.render.entity;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.client.model.entity.ModelHoveringInferno;
import mellohi138.netherized.client.render.entity.layer.LayerHoveringInfernoHelmet;
import mellohi138.netherized.objects.entity.hostile.EntityHoveringInferno;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHoveringInferno extends RenderLiving<EntityHoveringInferno> {
	private static final ResourceLocation INFERNO_TEXTURE = new ResourceLocation(Netherized.MODID, "textures/entity/hovering_inferno.png");
	
	public RenderHoveringInferno(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelHoveringInferno(), 0.7F);
		this.addLayer(new LayerHoveringInfernoHelmet(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHoveringInferno entity) {
		return INFERNO_TEXTURE;
	}
}
