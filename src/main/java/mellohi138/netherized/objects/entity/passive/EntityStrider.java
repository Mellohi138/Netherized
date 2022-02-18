package mellohi138.netherized.objects.entity.passive;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import mellohi138.netherized.init.NetherizedItems;
import mellohi138.netherized.objects.entity.ai.EntityAIMoveToLava;
import mellohi138.netherized.objects.entity.ai.pathfinding.PathNavigateLava;
import mellohi138.netherized.objects.entity.hostile.EntityZombifiedPiglin;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityStrider extends EntityAnimal {
    private static final DataParameter<Boolean> IS_COLD = EntityDataManager.<Boolean>createKey(EntityStrider.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IS_SADDLED = EntityDataManager.<Boolean>createKey(EntityStrider.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.<Integer>createKey(EntityStrider.class, DataSerializers.VARINT);
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.CARROT);
    private boolean boosting;
    private int boostTime;
    private int totalBoostTime;
    
	public EntityStrider(World worldIn) {
		super(worldIn);
		this.setPathPriority(PathNodeType.WATER, -1.0F);
		this.setPathPriority(PathNodeType.LAVA, 0.0F);
		this.setPathPriority(PathNodeType.DANGER_FIRE, 0.0F); 
		this.setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
		this.isImmuneToFire = true;
		this.setSize(0.9F, 1.7F);
	}
	
	@Override
    protected void entityInit() {
		super.entityInit();
        this.dataManager.register(BOOST_TIME, Integer.valueOf(0));
        this.dataManager.register(IS_COLD, Boolean.valueOf(false));
        this.dataManager.register(IS_SADDLED, Boolean.valueOf(false));
    }
    
	@Override
    public void onUpdate() {
		super.onUpdate();
		
		if(this.checkIfOnLava()) {
			this.motionY = 0.0F;
			this.onGround = true;
		}
    }
    
	@Override
    protected void updateAITasks() {
		super.updateAITasks();
        if (this.isWet()) {
            this.attackEntityFrom(DamageSource.DROWN, 1.0F);
        }
        if (this.world.getTotalWorldTime() % 100L == 0L) {
            this.setIsCold(!this.checkIfOnLava());
        }
    }
	
	private boolean checkIfOnLava() {
		float x = MathHelper.floor(this.posX);
		float y = MathHelper.floor(this.posY - Float.MIN_VALUE);
		float z = MathHelper.floor(this.posZ);
		
		return this.world.getBlockState(new BlockPos(x, y, z)).getMaterial() == Material.LAVA || this.isInsideOfMaterial(Material.LAVA) || this.isInLava() ;
	}
	
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
    	this.doBlockCollisions();
    	if(this.isInLava()) {
    		this.fallDistance = 0F;
    	} else {
    		super.updateFallState(y, onGroundIn, state, pos);
    	}
    }
    
    public void notifyDataManagerChange(DataParameter<?> key) {
        if (BOOST_TIME.equals(key) && this.world.isRemote) {
            this.boosting = true;
            this.boostTime = 0;
            this.totalBoostTime = ((Integer)this.dataManager.get(BOOST_TIME)).intValue();
        }
        super.notifyDataManagerChange(key);
    }
    
	@Override
    public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
        compound.setBoolean("Saddle", this.getIsSaddled());
    }
    
	@Override
    public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
        this.setIsSaddled(compound.getBoolean("Saddle"));
    }
	
	@Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAIPanic(this, 1.65D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.2D, NetherizedItems.WARPED_FUNGUS_ON_A_STICK, false));
        this.tasks.addTask(3, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
        this.tasks.addTask(4, new EntityAIMoveToLava(this, 1.5F));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1F));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityStrider.class, 12.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }
	
    protected PathNavigate createNavigator(World worldIn) {
		return new PathNavigateLava(this, worldIn);
    }
    
	@Override
    protected void applyEntityAttributes() {
		super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0F);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.175F);
    }
	
	@Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (!super.processInteract(player, hand)) {
            ItemStack itemstack = player.getHeldItem(hand);

            if (itemstack.getItem() == Items.NAME_TAG) {
                itemstack.interactWithEntity(player, this, hand);
                return true;
            } else if (this.getIsSaddled() && !this.isBeingRidden()) {
                if (!this.world.isRemote) {
                    player.startRiding(this);
                }
                return true;
            } else if (itemstack.getItem() == Items.SADDLE) {
                itemstack.interactWithEntity(player, this, hand);
                this.world.playSound((EntityPlayer)null, this.posX, this.posY + 0.5F, this.posZ, SoundEvents.ENTITY_PIG_SADDLE, this.getSoundCategory(), 1.0F, 1.0F);
            	this.setIsSaddled(true);
            	itemstack.shrink(1);
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
	
    public static void registerFixesStrider(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntityStrider.class);
    }
	
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyIn, @Nullable IEntityLivingData livingData) {
    	if(this.isChild()) {
        	return super.onInitialSpawn(difficultyIn, livingData);
        } else {
        	if(!this.world.isRemote) {
        		if(this.rand.nextInt(30) == 0) {
        			EntityZombifiedPiglin zombifiedPiglin = new EntityZombifiedPiglin(this.world);
        			livingData = this.addRider(difficultyIn, zombifiedPiglin, livingData);
        		} else if(this.rand.nextInt(10) == 0) {
        			EntityStrider strider = new EntityStrider(this.world);
        			strider.setGrowingAge(-24000);
        			livingData = this.addRider(difficultyIn, strider, livingData);
        		}
        		return super.onInitialSpawn(difficultyIn, livingData);
        	}
    		return super.onInitialSpawn(difficultyIn, livingData);
        }
    }
    
    private IEntityLivingData addRider(DifficultyInstance difficultyIn, EntityLiving entityMob, @Nullable IEntityLivingData livingData) {
    	entityMob.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
    	entityMob.onInitialSpawn(difficultyIn, livingData);
		this.world.spawnEntity(entityMob);
		entityMob.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, NetherizedItems.WARPED_FUNGUS_ON_A_STICK.getDefaultInstance());
		entityMob.startRiding(this);
        return livingData;
     }
    
    public boolean canBeSteered() {
        Entity entity = this.getControllingPassenger();

        if (!(entity instanceof EntityPlayer)) {
            return false;
        } else {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            return entityplayer.getHeldItemMainhand().getItem() == NetherizedItems.WARPED_FUNGUS_ON_A_STICK || entityplayer.getHeldItemOffhand().getItem() == NetherizedItems.WARPED_FUNGUS_ON_A_STICK;
        }
    }
    
    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
    }
    
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (!this.world.isRemote) {
            if (this.getIsSaddled()) {
                this.dropItem(Items.SADDLE, 1);
            }
        }
    }
    
    @Nullable
    protected ResourceLocation getLootTable() {
    	return null;
    }
    
    public void travel(float strafe, float vertical, float forward) {
    	this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * (this.canBeBoosted() ? 0.66F : 1.0F));
    	
        Entity entity = this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);

        if (this.isBeingRidden() && this.canBeSteered()) {
            this.rotationYaw = entity.rotationYaw;
            this.prevRotationYaw = this.rotationYaw;
            this.rotationPitch = entity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.renderYawOffset = this.rotationYaw;
            this.rotationYawHead = this.rotationYaw;
            this.stepHeight = 1.0F;
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

            if (this.boosting && this.boostTime++ > this.totalBoostTime) {
                this.boosting = false;
            }

            if (this.canPassengerSteer()) {
                float f = (float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * (this.canBeBoosted() ? 0.23F : 0.55F);

                if (this.boosting) {
                    f += f * 1.15F * MathHelper.sin((float)this.boostTime / (float)this.totalBoostTime * (float)Math.PI);
                }

                this.setAIMoveSpeed(f);
                super.travel(0.0F, 0.0F, 1.0F);
            } else {
                this.motionX = 0.0D;
                this.motionY = 0.0D;
                this.motionZ = 0.0D;
            }

            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d1 = this.posX - this.prevPosX;
            double d0 = this.posZ - this.prevPosZ;
            float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

            if (f1 > 1.0F) {
                f1 = 1.0F;
            }

            this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
        } else {
            this.stepHeight = 0.5F;
            this.jumpMovementFactor = 0.02F;
            super.travel(strafe, vertical, forward);
        }
    }

    public boolean boost() {
        if (this.boosting) {
            return false;
        } else {
            this.boosting = true;
            this.boostTime = 0;
            this.totalBoostTime = this.getRNG().nextInt(841) + 140;
            this.getDataManager().set(BOOST_TIME, Integer.valueOf(this.totalBoostTime));
            return true;
        }
    }
    
    public boolean canBeBoosted() {
    	return this.getRidingEntity() instanceof EntityStrider ? ((EntityStrider)this.getRidingEntity()).canBeBoosted() : this.dataManager.get(IS_COLD);
    }
    
    public double getMountedYOffset() {
        float f = Math.min(0.25F, this.limbSwingAmount);
        float f1 = this.limbSwing;
        return (double)this.height - 0.19D + (double)(0.12F * MathHelper.cos(f1 * 1.5F) * 2.0F * f);
    }

    public boolean getIsSaddled() {
        return this.dataManager.get(IS_SADDLED).booleanValue();
    }

    public void setIsSaddled(boolean saddled) {
    	this.dataManager.set(IS_SADDLED, Boolean.valueOf(saddled));
    }
    
    public boolean getIsCold() {
    	return this.dataManager.get(IS_COLD).booleanValue();
    }
    
    public void setIsCold(boolean isCold) {
    	this.dataManager.set(IS_COLD, isCold);
    }
    
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return TEMPTATION_ITEMS.contains(stack.getItem());
    }
	
	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return new EntityStrider(this.world);
	}
}
