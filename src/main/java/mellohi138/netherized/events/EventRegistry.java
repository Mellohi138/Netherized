package mellohi138.netherized.events;

import java.util.Random;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.enums.EnumNetherizedParticles;
import mellohi138.netherized.init.NetherizedBlocks;
import mellohi138.netherized.init.NetherizedEnchantments;
import mellohi138.netherized.init.NetherizedItems;
import mellohi138.netherized.init.NetherizedMaterials;
import mellohi138.netherized.init.NetherizedSounds;
import mellohi138.netherized.objects.enchantment.EnchantmentSoulSpeed;
import mellohi138.netherized.objects.entity.EntityFireproofItem;
import mellohi138.netherized.objects.item.ItemBruteAxe;
import mellohi138.netherized.objects.item.ItemInfernalShield;
import mellohi138.netherized.objects.item.ItemNetheriteHorseArmor;
import mellohi138.netherized.util.ModUtils;
import mellohi138.netherized.util.config.NetherizedGeneralConfig;
import mellohi138.netherized.util.config.NetherizedItemConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Netherized.MODID)
public class EventRegistry {
	@SubscribeEvent
	public static void addSoulSpeed(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entityIn = event.getEntityLiving();
		
    	IAttributeInstance attributeIn = entityIn.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
    	
        World worldIn = entityIn.world;
        BlockPos blockPos = new BlockPos(entityIn.posX, entityIn.posY - 0.5D, entityIn.posZ);
    	IBlockState state = worldIn.getBlockState(blockPos);
    	
    	if(entityIn.onGround && ModUtils.hasSoulSpeed(entityIn)) {
        	if(ModUtils.getSoulBlocks(state.getBlock())) {
            	int i = EnchantmentHelper.getEnchantmentLevel(NetherizedEnchantments.SOUL_SPEED, entityIn.getItemStackFromSlot(EntityEquipmentSlot.FEET));
                float speed = (i * 0.105F) + 1.3F;
                
                if(!entityIn.world.isRemote && attributeIn.getModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) == null) {
                	attributeIn.applyModifier(new AttributeModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER, "soul_speed_modifier", speed, 1).setSaved(false));;	
                }
                if(entityIn.motionX != 0 && entityIn.motionZ != 0 && entityIn.ticksExisted % 5 == 0) {
                	Netherized.PROXY.spawnParticle(EnumNetherizedParticles.SOUL, worldIn, entityIn.posX, entityIn.posY, entityIn.posZ, entityIn.motionX, entityIn.motionY, entityIn.motionZ);
                	float f = worldIn.rand.nextFloat() * 0.4F + worldIn.rand.nextFloat() > 0.9F ? 0.6F : 0.0F;
                    worldIn.playSound((EntityPlayer)null, blockPos, NetherizedSounds.PARTICLE_SOUL_SCREAM, SoundCategory.AMBIENT, f, 0.6F + worldIn.rand.nextFloat() * 0.4F);
                }
        	} else {
        		if(attributeIn.getModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) != null) {
        			attributeIn.removeModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER);
        		}
        	}
        }
	}
	
	@SubscribeEvent
	public static void adjustSoulSpeedUponBootChange(LivingEquipmentChangeEvent event) {
		EntityLivingBase entityIn = event.getEntityLiving();
		
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer)entityIn;	
	    	IAttributeInstance attributeIn = playerIn.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
			
	    	IBlockState state = playerIn.world.getBlockState(new BlockPos(playerIn.posX, playerIn.posY - 0.5D, playerIn.posZ));
	    	
	    	if(event.getSlot() == EntityEquipmentSlot.FEET) {
				if(!ModUtils.hasSoulSpeed(playerIn)) {
		    		if(attributeIn.getModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) != null) {
		    			attributeIn.removeModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER);
		    		}
				} else {
					if(ModUtils.getSoulBlocks(state.getBlock())) {
						//Adjusting the speed, just incase the switched equipment has a different level than the original one
			    		if(attributeIn.getModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) != null) {
			    			attributeIn.removeModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER);
			    		}
		            	int i = EnchantmentHelper.getEnchantmentLevel(NetherizedEnchantments.SOUL_SPEED, playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET));
		                float speed = (i * 0.105F) + 1.3F;
		                
		                if(!playerIn.world.isRemote && attributeIn.getModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) == null) {
		                	attributeIn.applyModifier(new AttributeModifier(EnchantmentSoulSpeed.SOUL_SPEED_MODIFIER, "soul_speed_modifier", speed, 1).setSaved(false));;	
		                }
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
	public static void convertFireToSoulFire(BlockEvent event) {
		World worldIn = event.getWorld();
		BlockPos pos = event.getPos();
		
		if(event.getState().getBlock() == Blocks.FIRE) {
	    	IBlockState soil = worldIn.getBlockState(pos.down());
	    	if(ModUtils.getSoulBlocks(soil.getBlock())) {
	    		worldIn.setBlockState(pos, NetherizedBlocks.SOUL_FIRE.getDefaultState());
	    	}
		}
	}
	
	
	@SubscribeEvent
	public static void addHoeHarvesting(PlayerEvent.BreakSpeed event) {
		EntityPlayer playerIn = event.getEntityPlayer();
		ItemStack stack = playerIn.getHeldItem(EnumHand.MAIN_HAND);
		
		if(stack.getItem() instanceof ItemHoe) {
			IBlockState state = event.getState();
			if(ModUtils.isHoeWhitelisted(state.getBlock())) {
				ItemHoe hoe = (ItemHoe)playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem();
				ToolMaterial material = ToolMaterial.valueOf(hoe.getMaterialName());
				event.setNewSpeed(material.getEfficiency());
			}
		}
	}
	
	@SubscribeEvent
	public static void damageHoeUponHarvest(BlockEvent.BreakEvent event) {
		EntityPlayer player = event.getPlayer();
		ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND);
		
		if(stack.getItem() instanceof ItemHoe) {
			IBlockState state = event.getState();
			if(ModUtils.isHoeWhitelisted(state.getBlock()) && !player.world.isRemote) {
				player.getHeldItem(EnumHand.MAIN_HAND).damageItem(1, player);
			}
		}
	}
	
	@SubscribeEvent
	public static void addBruteAxeDamage(LivingAttackEvent event) {
		Entity source = event.getSource().getImmediateSource();
		EntityLivingBase target = event.getEntityLiving();
		
		if(source != null) {
			if(!target.world.isRemote && !target.isDead && source instanceof EntityLiving) {
				EntityLiving attacker = (EntityLiving)source;
				ItemStack weapon = attacker.getHeldItemMainhand();
				if(!weapon.isEmpty() && weapon.getItem() instanceof ItemBruteAxe) {
					float damage = ModUtils.calculatePrecentage(target.getHealth(), NetherizedItemConfig.bruteAxeConfig.bruteAxeDamage);
					target.attackEntityFrom(DamageSource.causeMobDamage(target), damage);
					
					//Resets the immunity frames. Required for this to properly work.
					target.hurtResistantTime = 0;
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void addInfernalShieldBlock(LivingAttackEvent event) {
		Entity source = event.getSource().getImmediateSource();
		EntityLivingBase target = event.getEntityLiving();
		
		//TODO: Make it so that this is called upon a successful block instead of checking if it's blocking with this shield
		if(target.getActiveItemStack().getItem() instanceof ItemInfernalShield && target.isHandActive()) {
			if(!source.isImmuneToFire()) {
				source.setFire(8);
			}
			if(source instanceof EntityLivingBase) {
				EntityLivingBase attacker = (EntityLivingBase)source;
				attacker.knockBack(attacker, 0.25F, target.posX - attacker.posX, target.posZ - attacker.posZ);
			}
		}
	}
	
	@SubscribeEvent
	public static void addInfernalHelmetDamageReduction(LivingHurtEvent event) {
		EntityLivingBase entityIn = event.getEntityLiving();
		if(entityIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == NetherizedItems.INFERNAL_HELMET) {
			DamageSource source = event.getSource();
			if(source.isFireDamage()) {
				float amount = event.getAmount();
				event.setAmount(amount / NetherizedItemConfig.infernalConfig.infernalHelmetDamageReduction);
			}
		}
	}
	
	@SubscribeEvent
	public static void addNetherrackBonemeal(BonemealEvent event) {
		if(event.getBlock() == Blocks.NETHERRACK.getDefaultState()) {
			BlockPos pos = event.getPos();
			World worldIn = event.getWorld();
			Random rand = worldIn.rand;
			
			if(!worldIn.getBlockState(pos.up()).isSideSolid(worldIn, pos, EnumFacing.UP)) {
				for(BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
					if (worldIn.getBlockState(blockPos).getBlock() == NetherizedBlocks.CRIMSON_NYLIUM || worldIn.getBlockState(blockPos).getBlock() == NetherizedBlocks.WARPED_NYLIUM) {
					      boolean crimsonFlag = false;
					      boolean warpedFlag = false;
					      
				    	  IBlockState blockState = worldIn.getBlockState(blockPos);
				    	  if(blockState.getBlock() == NetherizedBlocks.CRIMSON_NYLIUM) {
				    		  crimsonFlag = true;
				    	  }
				    	  
				    	  if(blockState.getBlock() == NetherizedBlocks.WARPED_NYLIUM) {
				    		  warpedFlag = true;
				    	  }
				    	  
				    	  if(crimsonFlag && warpedFlag) {
				    		  break;
				    	  }
				    	  
					      if (crimsonFlag && warpedFlag) {
					    	  worldIn.setBlockState(pos, rand.nextBoolean() ? NetherizedBlocks.CRIMSON_NYLIUM.getDefaultState() : NetherizedBlocks.WARPED_NYLIUM.getDefaultState());
					      } else if (crimsonFlag) {
					    	  worldIn.setBlockState(pos, NetherizedBlocks.CRIMSON_NYLIUM.getDefaultState());
					      } else if (warpedFlag) {
					    	  worldIn.setBlockState(pos, NetherizedBlocks.WARPED_NYLIUM.getDefaultState());
					      }
				    	  event.setResult(Result.ALLOW);
					}
				}
			}
		}
	}
	
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
	
	@SubscribeEvent
	public static void addNetheriteAnvilRecipes(AnvilUpdateEvent event) {
		if(NetherizedGeneralConfig.netheriteAnvilRecipes) {
			ItemStack leftInput = event.getLeft();
			ItemStack rightInput = event.getRight();
			ItemStack output = event.getOutput();
			
			if(rightInput.getItem() == NetherizedItems.NETHERITE_INGOT) {
				if(leftInput.getItem().getRegistryName().getPath().contains("diamond")) {
					ResourceLocation itemName = new ResourceLocation(Netherized.MODID, leftInput.getItem().getRegistryName().getPath().replace("diamond", "netherite"));
					output = new ItemStack(Item.getByNameOrId(itemName.toString()));
					NBTTagCompound tags = leftInput.getTagCompound();
					output.setTagCompound(tags);
					output.setItemDamage(leftInput.getItemDamage());
					event.setOutput(output);
					event.setMaterialCost(1);
					event.setCost(3);
				}
			}
 		}
	}
}
