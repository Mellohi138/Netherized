package mellohi138.netherized.events;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.init.NetherizedItems;
import mellohi138.netherized.objects.entity.EntityFireproofItem;
import mellohi138.netherized.objects.item.ItemBruteAxe;
import mellohi138.netherized.util.IsImmune;
import mellohi138.netherized.util.MathUtil;
import mellohi138.netherized.util.config.NetherizedConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Netherized.MODID)
public class EventRegistry {
	@SubscribeEvent
	public static void onLivingAttack(LivingAttackEvent event) {
		Entity source = event.getSource().getTrueSource();
		EntityLivingBase target = (EntityLivingBase)event.getEntity();
		
		if(!target.world.isRemote && !target.isDead && source instanceof EntityLiving) {
			EntityLiving attacker = (EntityLiving)source;
			ItemStack weapon = attacker.getHeldItemMainhand();
			if(!weapon.isEmpty() && weapon.getItem() instanceof ItemBruteAxe) {
				float damage = MathUtil.calculatePrecentage(target.getHealth(), NetherizedConfig.bruteAxeDamage);
				target.attackEntityFrom(DamageSource.causeMobDamage(target), damage);
				
				//Resets the immunity frames. Required for this to properly work.
				target.hurtResistantTime = 0;
			}
		}
	}
	
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
		World worldIn = event.getWorld();
		
		if(event.getEntity().getClass() == EntityItem.class) {
			EntityItem entityItem = (EntityItem)event.getEntity();
			ItemStack stack = entityItem.getItem();
			
			if(IsImmune.isFireproof(stack.getItem()) && !stack.isEmpty() && !worldIn.isRemote) {
	    		EntityFireproofItem fireproofItem = new EntityFireproofItem(worldIn, entityItem, stack);
	    		worldIn.spawnEntity(fireproofItem);
				entityItem.setDead();
			}
		} 
		if(event.getEntity().getClass() == EntityFireproofItem.class) {
			EntityFireproofItem fireproofItem = (EntityFireproofItem)event.getEntity();
			ItemStack stack = fireproofItem.getItem();
			
			if(!IsImmune.isFireproof(stack.getItem()) && !stack.isEmpty() && !worldIn.isRemote) {
				EntityItem entityItem = new EntityItem(worldIn, fireproofItem.posX, fireproofItem.posY, fireproofItem.posZ, stack);
				worldIn.spawnEntity(entityItem);
				fireproofItem.setDead();
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
