package mellohi138.netherized.client.model;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPiglin extends ModelPlayer {
	public ModelPiglin(float modelSize) {
		super(modelSize, false);
	}
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    }
}
