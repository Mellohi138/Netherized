package mellohi138.netherized.objects.entity.ai;

import mellohi138.netherized.objects.entity.hostile.EntityHoveringInferno;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class EntityAIInfernoFight extends EntityAIBase {
	private final EntityHoveringInferno inferno;
    private int attackStep;
    private int attackTime;

    public EntityAIInfernoFight(EntityHoveringInferno infernoIn) {
        this.inferno = infernoIn;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.inferno.getAttackTarget();
        return entitylivingbase != null && entitylivingbase.isEntityAlive();
    }

    public void startExecuting() {
        this.attackStep = 0;
    }

    public void resetTask() {
        this.inferno.setOnFire(false);
    }

    public void updateTask() {
        --this.attackTime;
        EntityLivingBase entitylivingbase = this.inferno.getAttackTarget();
        double d0 = this.inferno.getDistanceSq(entitylivingbase);

        if (d0 < 4.0D) {
            if (this.attackTime <= 0) {
                this.attackTime = 20;
                this.inferno.attackEntityAsMob(entitylivingbase);
            }

            this.inferno.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
        } else if (d0 < this.getFollowDistance() * this.getFollowDistance()) {
            double d1 = entitylivingbase.posX - this.inferno.posX;
            double d2 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (this.inferno.posY + (double)(this.inferno.height / 2.0F));
            double d3 = entitylivingbase.posZ - this.inferno.posZ;

            if (this.attackTime <= 0) {
                ++this.attackStep;

                if (this.attackStep == 1) {
                    this.attackTime = 60;
                    this.inferno.setOnFire(true);
                } else if (this.attackStep <= 4) {
                    this.attackTime = 6;
                } else {
                    this.attackTime = 100;
                    this.attackStep = 0;
                    this.inferno.setOnFire(false);
                }

                if (this.attackStep > 1) {
                    float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                    this.inferno.world.playEvent((EntityPlayer)null, 1018, new BlockPos((int)this.inferno.posX, (int)this.inferno.posY, (int)this.inferno.posZ), 0);

                    for (int i = 0; i < 1; ++i) {
                        EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.inferno.world, this.inferno, d1 + this.inferno.getRNG().nextGaussian() * (double)f, d2, d3 + this.inferno.getRNG().nextGaussian() * (double)f);
                        entitysmallfireball.posY = this.inferno.posY + (double)(this.inferno.height / 2.0F) + 0.5D;
                        this.inferno.world.spawnEntity(entitysmallfireball);
                    }
                }
            }

            this.inferno.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
        } else {
            this.inferno.getNavigator().clearPath();
            this.inferno.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
        }

        super.updateTask();
    }

    private double getFollowDistance() {
        IAttributeInstance iattributeinstance = this.inferno.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
        return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
    }  
}
