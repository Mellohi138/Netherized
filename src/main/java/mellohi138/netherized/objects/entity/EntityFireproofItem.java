package mellohi138.netherized.objects.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Fireproof items were assigned to normal items with the help of Tinkers' Construct source code. Credit goes to bonusboni.
 * Tinkers Construct belongs to mDiyo. 
 */
public class EntityFireproofItem extends EntityItem { 
    private static final DataParameter<ItemStack> ITEM = EntityDataManager.<ItemStack>createKey(EntityFireproofItem.class, DataSerializers.ITEM_STACK);
    /** The age of this EntityFireproofItem (used to animate it up and down as well as expire it) */
    private int age;
    private int pickupDelay;
    /** The health of this EntityFireproofItem. (For example, damage for tools) */
    private int health;
    
	public EntityFireproofItem(World worldIn) {
		super(worldIn);
        this.health = 5;
        
        this.isImmuneToFire = true;
	}
	
	public EntityFireproofItem(World worldIn, double x, double y, double z) {
	    super(worldIn, x, y, z);
        this.health = 5;
        
        this.isImmuneToFire = true;
	}
	
	public EntityFireproofItem(World worldIn, double x, double y, double z, ItemStack stack) {
		super(worldIn, x, y, z, stack);
        
        this.isImmuneToFire = true;
	}
	
    public EntityFireproofItem(World worldIn, Entity entity, ItemStack stack) {
        this(worldIn, entity.posX, entity.posY, entity.posZ, stack);
        this.motionX = entity.motionX;
        this.motionY = entity.motionY;
        this.motionZ = entity.motionZ;
      	 if(entity instanceof EntityItem) {
     	    NBTTagCompound tag = new NBTTagCompound();
     	    entity.writeToNBT(tag);
     	    this.setPickupDelay(tag.getShort("PickupDelay"));
     	 } else {
      	    this.setDefaultPickupDelay();
     	 }
    }
    
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.world.isRemote || this.isDead) return false;
        if (this.isEntityInvulnerable(source)) {
            return false;
        } else if (source.isFireDamage()) {
            return false;
        } else {
            this.markVelocityChanged();
            this.health = (int)((float)this.health - amount);

            if (this.health <= 0) {
                this.setDead();
            }
            return false;
        }
    }
    
    public void onUpdate() {
        if (getItem().getItem().onEntityItemUpdate(this)) return;
        if (this.getItem().isEmpty()) {
            this.setDead();
        } else {
            if (!this.world.isRemote) {
                this.setFlag(6, this.isGlowing());
            }

            this.onEntityUpdate();

            if (this.pickupDelay > 0 && this.pickupDelay != 32767) {
                --this.pickupDelay;
            }

            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            double d0 = this.motionX;
            double d1 = this.motionY;
            double d2 = this.motionZ;

            if (this.isInsideOfMaterial(Material.LAVA)) {
                this.motionX *= 0.95F;
                this.motionY += this.floatInLava(this);
                this.motionZ *= 0.95F;
            } else if (!this.hasNoGravity()) {
                this.motionY -= 0.03999999910593033D;
            }

            if (this.world.isRemote) {
                this.noClip = false;
            } else {
                this.noClip = this.pushOutOfBlocks(this.posX, (this.getEntityBoundingBox().minY + this.getEntityBoundingBox().maxY) / 2.0D, this.posZ);
            }
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            
            boolean flag = (int)this.prevPosX != (int)this.posX || (int)this.prevPosY != (int)this.posY || (int)this.prevPosZ != (int)this.posZ;
            
            if (flag || this.ticksExisted % 25 == 0) {
                if (!this.world.isRemote) {
                    this.searchForOtherItemsNearby();
                }
            }

            float f = 0.98F;

            if (this.onGround) {
                BlockPos underPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ));
                net.minecraft.block.state.IBlockState underState = this.world.getBlockState(underPos);
                f = underState.getBlock().getSlipperiness(underState, this.world, underPos, this) * 0.98F;
            }

            this.motionX *= (double)f;
            this.motionY *= 0.9800000190734863D;
            this.motionZ *= (double)f;

            if (this.onGround) {
                this.motionY *= -0.5D;
            } if (this.age != -32768) {
                ++this.age;
            }

            this.handleWaterMovement();
            this.handleLavaMovement();

            if (!this.world.isRemote) {
                double d3 = this.motionX - d0;
                double d4 = this.motionY - d1;
                double d5 = this.motionZ - d2;
                double d6 = d3 * d3 + d4 * d4 + d5 * d5;

                if (d6 > 0.01D) {
                    this.isAirBorne = true;
                }
            }

            ItemStack item = this.getItem();

            if (!this.world.isRemote && this.age >= lifespan) {
                int hook = net.minecraftforge.event.ForgeEventFactory.onItemExpire(this, item);
                if (hook < 0) this.setDead();
                else          this.lifespan += hook;
            } if (item.isEmpty()) {
                this.setDead();
            }
        }
    }
    
    private double floatInLava(EntityItem entity) {
    	if(entity.motionY < 0.6) {
    		return 5.0E-4;
    	} else {
    		return 0.0;
    	}
    }
    
    private boolean handleLavaMovement() {
        if (this.world.handleMaterialAcceleration(this.getEntityBoundingBox(), Material.LAVA, this)) {
            this.motionX *= 0.75F;
            this.motionZ *= 0.75F;
        }

        return this.isInsideOfMaterial(Material.LAVA);
    }
    
	protected void entityInit() {
        this.getDataManager().register(ITEM, ItemStack.EMPTY);
    }
    
    private void searchForOtherItemsNearby() {
        for (EntityFireproofItem entityitem : this.world.getEntitiesWithinAABB(EntityFireproofItem.class, this.getEntityBoundingBox().grow(0.5D, 0.0D, 0.5D))) {
            this.combineItems(entityitem);
        }
    }
    
    private boolean combineItems(EntityFireproofItem other) {
        if (other == this) {
            return false;
        } else if (other.isEntityAlive() && this.isEntityAlive()) {
            ItemStack itemstack = this.getItem();
            ItemStack itemstack1 = other.getItem();

            if (this.pickupDelay != 32767 && other.pickupDelay != 32767) {
                if (this.age != -32768 && other.age != -32768) {
                    if (itemstack1.getItem() != itemstack.getItem()) {
                        return false;
                    } else if (itemstack1.hasTagCompound() ^ itemstack.hasTagCompound()) {
                        return false;
                    } else if (itemstack1.hasTagCompound() && !itemstack1.getTagCompound().equals(itemstack.getTagCompound())) {
                        return false;
                    } else if (itemstack1.getItem() == null) {
                        return false;
                    } else if (itemstack1.getItem().getHasSubtypes() && itemstack1.getMetadata() != itemstack.getMetadata()) {
                        return false;
                    } else if (itemstack1.getCount() < itemstack.getCount()) {
                        return other.combineItems(this);
                    } else if (itemstack1.getCount() + itemstack.getCount() > itemstack1.getMaxStackSize()) {
                        return false;
                    } else if (!itemstack.areCapsCompatible(itemstack1)) {
                        return false;
                    } else {
                        itemstack1.grow(itemstack.getCount());
                        other.pickupDelay = Math.max(other.pickupDelay, this.pickupDelay);
                        other.age = Math.min(other.age, this.age);
                        other.setItem(itemstack1);
                        this.setDead();
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    @Override
    public void setAgeToCreativeDespawnTime() {
        this.age = 4800;
    }
    
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setShort("Health", (short)this.health);
        compound.setShort("Age", (short)this.age);
        compound.setShort("PickupDelay", (short)this.pickupDelay);
        compound.setInteger("Lifespan", lifespan);

        if (!this.getItem().isEmpty()) {
            compound.setTag("Item", this.getItem().writeToNBT(new NBTTagCompound()));
        }
    }
    
    public void readEntityFromNBT(NBTTagCompound compound) {
        this.health = compound.getShort("Health");
        this.age = compound.getShort("Age");

        if (compound.hasKey("PickupDelay")) {
            this.pickupDelay = compound.getShort("PickupDelay");
        }

        NBTTagCompound nbttagcompound = compound.getCompoundTag("Item");
        this.setItem(new ItemStack(nbttagcompound));

        if (this.getItem().isEmpty()) {
            this.setDead();
        }
        if (compound.hasKey("Lifespan")) lifespan = compound.getInteger("Lifespan");
    }
    
    public ItemStack getItem() {
        return (ItemStack)this.getDataManager().get(ITEM);
    }
    
    public void setItem(ItemStack stack) {
        this.getDataManager().set(ITEM, stack);
        this.getDataManager().setDirty(ITEM);
    }

    @SideOnly(Side.CLIENT)
    public int getAge() {
        return this.age;
    }
    
    public void setDefaultPickupDelay() {
        this.pickupDelay = 10;
    }
    
    public void setNoPickupDelay() {
        this.pickupDelay = 0;
    }
    
    public void setInfinitePickupDelay() {
        this.pickupDelay = 32767;
    }
    
    public void setPickupDelay(int ticks) {
        this.pickupDelay = ticks;
    }
    
    public boolean cannotPickup() {
        return this.pickupDelay > 0;
    }
    
    public void setNoDespawn() {
        this.age = -6000;
    }

    public void makeFakeItem() {
        this.setInfinitePickupDelay();
        this.age = getItem().getItem().getEntityLifespan(getItem(), world) - 1;
    }
    
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        if (!this.world.isRemote) {
            if (this.pickupDelay > 0) return;
            ItemStack itemstack = this.getItem();
            Item item = itemstack.getItem();
            int i = itemstack.getCount();

            int hook = net.minecraftforge.event.ForgeEventFactory.onItemPickup(this, entityIn);
            if (hook < 0) return;
            ItemStack clone = itemstack.copy();

            if (this.pickupDelay <= 0 && (this.getOwner() == null || lifespan - this.age <= 200 || this.getOwner().equals(entityIn.getName())) && (hook == 1 || i <= 0 || entityIn.inventory.addItemStackToInventory(itemstack) || clone.getCount() > this.getItem().getCount())) {
                clone.setCount(clone.getCount() - this.getItem().getCount());
                net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerItemPickupEvent(entityIn, this, clone);

                if (itemstack.isEmpty()) {
                    entityIn.onItemPickup(this, i);
                    this.setDead();
                    itemstack.setCount(i);
                }

                entityIn.addStat(StatList.getObjectsPickedUpStats(item), i);
            }
        }
    }
}