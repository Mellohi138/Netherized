package mellohi138.netherized.events;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.enums.EnumNetherizedParticles;
import mellohi138.netherized.init.NetherizedEnchantments;
import mellohi138.netherized.init.NetherizedMaterials;
import mellohi138.netherized.init.NetherizedSounds;
import mellohi138.netherized.objects.enchantment.EnchantmentSoulSpeed;
import mellohi138.netherized.objects.entity.EntityFireproofItem;
import mellohi138.netherized.objects.item.ItemNetheriteHorseArmor;
import mellohi138.netherized.util.ModUtils;
import mellohi138.netherized.util.config.NetherizedGeneralConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Netherized.MODID)
public class EntityEvents {
	@SubscribeEvent
	public static void turnItemsFireproof(EntityJoinWorldEvent event) {
		World worldIn = event.getWorld();
		
		if(event.getEntity().getClass() == EntityItem.class) {
			EntityItem entityItem = (EntityItem)event.getEntity();
			ItemStack stack = entityItem.getItem();
			
			if(ModUtils.isFireproof(stack.getItem()) && !stack.isEmpty() && !worldIn.isRemote) {
	    		EntityFireproofItem fireproofItem = new EntityFireproofItem(worldIn, entityItem, stack);
	    		worldIn.spawnEntity(fireproofItem);
				entityItem.setDead();
			}
		}
	}
	
	@SubscribeEvent
	public static void turnFireproofItemsToNormal(EntityJoinWorldEvent event) {
		World worldIn = event.getWorld();
		
		if(event.getEntity().getClass() == EntityFireproofItem.class) {
			EntityFireproofItem fireproofItem = (EntityFireproofItem)event.getEntity();
			ItemStack stack = fireproofItem.getItem();
			
			if(!ModUtils.isFireproof(stack.getItem()) && !stack.isEmpty() && !worldIn.isRemote) {
				EntityItem entityItem = new EntityItem(worldIn, fireproofItem.posX, fireproofItem.posY, fireproofItem.posZ, stack);
				worldIn.spawnEntity(entityItem);
				fireproofItem.setDead();
			}
		}
	}
	
	@SubscribeEvent
	public static void addSoulSpeed(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entityIn = event.getEntityLiving();
		
    	IAttributeInstance attributeIn = entityIn.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
    	
        World worldIn = entityIn.world;
        BlockPos blockPos = new BlockPos(entityIn.posX, entityIn.posY - 0.5D, entityIn.posZ);
    	IBlockState state = worldIn.getBlockState(blockPos);
    	
    	if(entityIn.onGround && ModUtils.hasSoulSpeed(entityIn)) {
        	if(ModUtils.getSoulBlocks(state.getBlock())) {
        		if(!worldIn.isRemote) {
                	int i = EnchantmentHelper.getEnchantmentLevel(NetherizedEnchantments.SOUL_SPEED, entityIn.getItemStackFromSlot(EntityEquipmentSlot.FEET));
                    float speed = (i * 0.105F) + 1.3F;
                    
                    if(attributeIn.getModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) == null) attributeIn.applyModifier(new AttributeModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER, "soul_speed_modifier", speed, 1).setSaved(false));
        		}
                
                if(entityIn.motionX != 0 && entityIn.motionZ != 0 && entityIn.ticksExisted % 5 == 0) {
                	Netherized.PROXY.spawnParticle(EnumNetherizedParticles.SOUL, worldIn, entityIn.posX, entityIn.posY, entityIn.posZ, entityIn.motionX, entityIn.motionY, entityIn.motionZ);
                	float f = worldIn.rand.nextFloat() * 0.4F + worldIn.rand.nextFloat() > 0.9F ? 0.6F : 0.0F;
                    worldIn.playSound((EntityPlayer)null, blockPos, NetherizedSounds.PARTICLE_SOUL_SCREAM, SoundCategory.AMBIENT, f, 0.6F + worldIn.rand.nextFloat() * 0.4F);
                }
        	} else {
        		if(attributeIn.getModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) != null) attributeIn.removeModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER);
        	}
        }
	}
	
	@SubscribeEvent
	public static void adjustSoulSpeedUponBootChange(LivingEquipmentChangeEvent event) {
		EntityLivingBase entityIn = event.getEntityLiving();
    	IAttributeInstance attributeIn = entityIn.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		
    	IBlockState state = entityIn.world.getBlockState(new BlockPos(entityIn.posX, entityIn.posY - 0.5D, entityIn.posZ));
    	
    	if(event.getSlot() == EntityEquipmentSlot.FEET) {
			if(!ModUtils.hasSoulSpeed(entityIn)) {
	    		if(attributeIn.getModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) != null) attributeIn.removeModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER);
			} else {
				if(ModUtils.getSoulBlocks(state.getBlock())) {
					//Adjusting the speed, just incase the switched equipment has a different level than the original one
		    		if(attributeIn.getModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) != null) attributeIn.removeModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER);
	            	int i = EnchantmentHelper.getEnchantmentLevel(NetherizedEnchantments.SOUL_SPEED, entityIn.getItemStackFromSlot(EntityEquipmentSlot.FEET));
	                float speed = (i * 0.105F) + 1.3F;
	                
	                if(!entityIn.world.isRemote && attributeIn.getModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) == null) {
	                	attributeIn.applyModifier(new AttributeModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER, "soul_speed_modifier", speed, 1).setSaved(false));;	
	                }
				}
			}
    	}
	}
	
	@SubscribeEvent
	public static void removeNetheriteHorseArmorBuffs(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entityIn = event.getEntityLiving();
		
		if(entityIn instanceof EntityHorse) {
			EntityHorse horse = (EntityHorse)entityIn;
			if(horse.getHorseArmorType() != NetherizedMaterials.NETHERITE_HORSE_ARMOR) {
		    	IAttributeInstance attribute = horse.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
		    	if(attribute.getModifier(ItemNetheriteHorseArmor.KNOCKBACK_RESISTANCE_MODIFIER) != null) {
		    		attribute.removeModifier(ItemNetheriteHorseArmor.KNOCKBACK_RESISTANCE_MODIFIER);
		    	}
			}
		}
	}
	
	@SubscribeEvent
	public static void backportKnockback(LivingKnockBackEvent event) {
		if(NetherizedGeneralConfig.newKnockback) {
			event.setCanceled(true);
			
			EntityLivingBase entityLiving = event.getEntityLiving();
			
			float strength = event.getStrength();
			double xRatio = event.getRatioX();
			double zRatio = event.getRatioZ();
			
			double knockbackResistance = entityLiving.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue();
			strength *= 1.0D - Math.min(knockbackResistance, 1.0F);
		      
			entityLiving.isAirBorne = true;
	        double f = 1.0D / MathHelper.sqrt(xRatio * xRatio + zRatio * zRatio);
	        entityLiving.motionX /= 2.0D;
	        entityLiving.motionZ /= 2.0D;
	        entityLiving.motionX -= xRatio * f * strength;
	        entityLiving.motionZ -= zRatio * f * strength;

	        if (entityLiving.onGround) {
	        	entityLiving.motionY /= 2.0D;
	        	entityLiving.motionY += strength;

	            if (entityLiving.motionY > 0.4000000059604645D) {
	            	entityLiving.motionY = 0.4000000059604645D;
	            }
	        }
		}
	}
}
