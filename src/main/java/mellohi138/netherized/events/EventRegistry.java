package mellohi138.netherized.events;

import java.util.Random;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.init.NetherizedBlocks;
import mellohi138.netherized.init.NetherizedItems;
import mellohi138.netherized.init.NetherizedMaterials;
import mellohi138.netherized.objects.entity.EntityFireproofItem;
import mellohi138.netherized.objects.item.ItemBruteAxe;
import mellohi138.netherized.objects.item.ItemInfernalShield;
import mellohi138.netherized.objects.item.ItemNetheriteHorseArmor;
import mellohi138.netherized.util.ModUtils;
import mellohi138.netherized.util.config.NetherizedConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Netherized.MODID)
public class EventRegistry {
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		
		if(entity instanceof EntityHorse) {
			EntityHorse horse = (EntityHorse)entity;
			if(horse.getHorseArmorType() != NetherizedMaterials.NETHERITE_HORSE_ARMOR) {
		    	IAttributeInstance attribute = horse.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
		    	if(attribute.getModifier(ItemNetheriteHorseArmor.KNOCKBACK_RESISTANCE_MODIFIER) != null) {
		    		attribute.removeModifier(ItemNetheriteHorseArmor.KNOCKBACK_RESISTANCE_MODIFIER);
		    	}
			}
		}
	}
	
	@SubscribeEvent
	public static void onBlock(BlockEvent event) {
		World worldIn = event.getWorld();
		BlockPos pos = event.getPos();
		
		if(event.getState().getBlock() == Blocks.FIRE) {
	    	IBlockState soil = worldIn.getBlockState(pos.down());
	    	if(soil.getBlock() == Blocks.SOUL_SAND || soil.getBlock() == NetherizedBlocks.SOUL_SOIL) {
	    		worldIn.setBlockState(pos, NetherizedBlocks.SOUL_FIRE.getDefaultState());
	    	}
		}
	}
	
	@SubscribeEvent
	public static void onLivingAttack(LivingAttackEvent event) {
		Entity source = event.getSource().getImmediateSource();
		EntityLivingBase target = event.getEntityLiving();
		
		if(source != null) {
			if(!target.world.isRemote && !target.isDead && source instanceof EntityLiving) {
				EntityLiving attacker = (EntityLiving)source;
				ItemStack weapon = attacker.getHeldItemMainhand();
				if(!weapon.isEmpty() && weapon.getItem() instanceof ItemBruteAxe) {
					float damage = ModUtils.calculatePrecentage(target.getHealth(), NetherizedConfig.bruteAxeDamage);
					target.attackEntityFrom(DamageSource.causeMobDamage(target), damage);
					
					//Resets the immunity frames. Required for this to properly work.
					target.hurtResistantTime = 0;
				}
			}
			
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
	}
	
	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent event) {
		EntityLivingBase entityIn = event.getEntityLiving();
		if(entityIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == NetherizedItems.INFERNAL_HELMET) {
			DamageSource source = event.getSource();
			if(source.isFireDamage()) {
				float amount = event.getAmount();
				event.setAmount(amount / 2.0F);
			}
		}
	}
	
	@SubscribeEvent
	public static void onBonemeal(BonemealEvent event) {
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
	public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
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
	public static void onLivingKnockBackEvent(LivingKnockBackEvent event) {
		if(NetherizedConfig.newKnockback) {
			event.setCanceled(true);
			
			EntityLivingBase entityLiving = event.getEntityLiving();
			
			float strength = event.getStrength();
			double xRatio = event.getRatioX();
			double zRatio = event.getRatioZ();
			
			float knockbackResistance = (float)event.getEntityLiving().getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue();
			strength *= (1.0F - knockbackResistance);
		      
			if (!(strength <= 0.0F)) {
				entityLiving.isAirBorne = true;
		        double f = 1.0F / MathHelper.sqrt((xRatio * xRatio) + (zRatio * zRatio));
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
	
	@SubscribeEvent
	public static void onAnvilUpdate(AnvilUpdateEvent event) {
		if(NetherizedConfig.netheriteAnvilRecipes) {
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
