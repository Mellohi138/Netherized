package mellohi138.netherized.client.render.item;

import mellohi138.netherized.Netherized;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelShield;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderInfernalShield {
    private static final ModelShield modelShield = new ModelShield();
	private static final ResourceLocation TEXTURE = new ResourceLocation(Netherized.MODID, "textures/models/inferno_shield.png");
	
	public static void render() {	
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);

	    GlStateManager.pushMatrix();
	    GlStateManager.scale(1.0F, -1.0F, -1.0F);
	    modelShield.render();
	    GlStateManager.popMatrix();
	}
}
