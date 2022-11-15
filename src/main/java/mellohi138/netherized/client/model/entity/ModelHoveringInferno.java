package mellohi138.netherized.client.model.entity;

import mellohi138.netherized.objects.entity.hostile.EntityHoveringInferno;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHoveringInferno extends ModelBase {
	private final ModelRenderer head;
    private final ModelRenderer rod;
    private final ModelRenderer shield1;
    private final ModelRenderer shield2;
    private final ModelRenderer shield3;
    private final ModelRenderer shield4;
    private final ModelRenderer shieldSet;

    public ModelHoveringInferno() {
    	super();
        this.textureWidth = 64;
        this.textureHeight = 64;
        
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -12.0F, -4.0F, 8, 8, 8, 0.0F);

        this.rod = new ModelRenderer(this, 0, 36);
        this.rod.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rod.addBox(-2.0F, 0.0F, -2.0F, 4, 22, 4, 0.0F);
        
        this.shieldSet = new ModelRenderer(this);
        this.shieldSet.setRotationPoint(0.0F, 0.0F, 0.0F);
        
        this.shield1 = new ModelRenderer(this, 0, 16);
        this.shield1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shield1.addBox(-4.75F, 1.5F, 6.0F, 10, 18, 2, 0.0F);
        this.shieldSet.addChild(this.shield1);

        this.shield2 = new ModelRenderer(this, 0, 16);
        this.shield2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shield2.addBox(-4.75F, 1.5F, 6.0F, 10, 18, 2, 0.0F);
        this.shieldSet.addChild(this.shield2);
        
        this.shield3 = new ModelRenderer(this, 0, 16);
        this.shield3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shield3.addBox(-4.75F, 1.5F, 6.0F, 10, 18, 2, 0.0F);
        this.shieldSet.addChild(this.shield3);
        
        this.shield4 = new ModelRenderer(this, 0, 16);
        this.shield4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shield4.addBox(-4.75F, 1.5F, 6.0F, 10, 18, 2, 0.0F);
        this.shieldSet.addChild(this.shield4);
    }
    
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    	super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
   		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
   		
   		this.head.render(scale);
   		this.rod.render(scale);
   		this.shieldSet.render(scale);
   }
    
    //Special thanks to DelirusCurx for the shield rotation code
    @Override
    public void setLivingAnimations(EntityLivingBase entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
    	super.setLivingAnimations(entityLivingBaseIn, limbSwing, limbSwingAmount, partialTickTime);
    	if(entityLivingBaseIn instanceof EntityHoveringInferno) {
        	EntityHoveringInferno inferno = (EntityHoveringInferno)entityLivingBaseIn;
        	
        	shieldSet.rotateAngleY = (inferno.ticksExisted + partialTickTime) / -7.0F;;
    	}
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    	this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
    	this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        
    	this.setRotateAngle(this.shield1, 0.3665191429188092F, 3.141592653589793F, 0.0F);
        this.setRotateAngle(this.shield2, 0.3665191429188092F, 1.5707963267948966F, 0.0F);
        this.setRotateAngle(this.shield3, 0.3665191429188092F, 0.0F, 0.0F);
        this.setRotateAngle(this.shield4, 0.3665191429188092F, -1.5707963267948966F, 0.0F);
    }    

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}