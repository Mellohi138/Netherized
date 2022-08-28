package mellohi138.netherized.client.render.entity.layer;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.client.model.entity.ModelStrider;
import mellohi138.netherized.client.render.entity.RenderStrider;
import mellohi138.netherized.objects.entity.passive.EntityStrider;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerStriderSaddle implements LayerRenderer<EntityStrider> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Netherized.MODID, "textures/entity/strider/strider_saddle.png");
    private final RenderStrider striderRenderer;
    private final ModelStrider striderModel = new ModelStrider();
    
    public LayerStriderSaddle(RenderStrider striderRendererIn) {
		this.striderRenderer = striderRendererIn;
    }
	@Override
	public void doRenderLayer(EntityStrider entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (entitylivingbaseIn.getIsSaddled()) {
            this.striderRenderer.bindTexture(TEXTURE);
            this.striderModel.setModelAttributes(this.striderRenderer.getMainModel());
            this.striderModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
