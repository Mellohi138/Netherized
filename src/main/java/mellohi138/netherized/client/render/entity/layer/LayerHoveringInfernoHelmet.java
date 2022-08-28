package mellohi138.netherized.client.render.entity.layer;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.client.model.armor.ModelInfernalHelmet;
import mellohi138.netherized.client.render.entity.RenderHoveringInferno;
import mellohi138.netherized.objects.entity.hostile.EntityHoveringInferno;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerHoveringInfernoHelmet implements LayerRenderer<EntityHoveringInferno> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(Netherized.MODID, "textures/models/armor/infernal_helmet.png");
	private final RenderHoveringInferno infernoRenderer;
	private final ModelInfernalHelmet helmetModel = new ModelInfernalHelmet(-3.0F);
	
	public LayerHoveringInfernoHelmet(RenderHoveringInferno infernoRendererIn) {
		this.infernoRenderer = infernoRendererIn;
	}
	
	@Override
	public void doRenderLayer(EntityHoveringInferno entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.infernoRenderer.bindTexture(TEXTURE);
		this.helmetModel.setModelAttributes(this.infernoRenderer.getMainModel());
		this.helmetModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
