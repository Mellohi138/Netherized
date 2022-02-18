package mellohi138.netherized.objects.item;

import java.util.List;

import javax.annotation.Nullable;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.util.MathUtil;
import mellohi138.netherized.util.config.NetherizedConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBruteAxe extends ItemAxe {
	public ItemBruteAxe(String name, ToolMaterial materialIn, CreativeTabs tab) {
		super(materialIn, materialIn.getAttackDamage() + 9F, -3.0F);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
		this.setMaxDamage(materialIn.getMaxUses() * 20);
	}
	
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(!target.isDead) {
			if(attacker instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)attacker;
				
				if(!player.getCooldownTracker().hasCooldown(this)) {					
					//Resets the immunity frames. Required for this to properly work.
					target.hurtResistantTime = 0;
					
					//Copied and edited from EntityPlayer.attackTargetEntityWithCurrentItem(Entity entityIn)
					boolean isCritical = player.swingProgress == 0 && player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(MobEffects.BLINDNESS) && !player.isRiding() && !player.isSprinting();
					
					if(isCritical) {
						float criticalDamage = MathUtil.calculatePrecentage(target.getHealth(), NetherizedConfig.bruteAxeDamage * 1.5F);
						target.attackEntityFrom(DamageSource.causePlayerDamage(player), criticalDamage);
						target.knockBack(target, 0.5F, player.posX - target.posX, player.posZ - target.posZ);
						player.getCooldownTracker().setCooldown(this, 300);
					} else {
						float swingProgress = 1.0F - attacker.swingProgress;
						float damage = MathUtil.calculatePrecentage(target.getHealth(), swingProgress * NetherizedConfig.bruteAxeDamage);
						
						target.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
						player.getCooldownTracker().setCooldown(this, (int) (swingProgress * 200));
					}
				}
			}
		}
        stack.damageItem(1, attacker);
        return true;
    }
	
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack itemStack, Enchantment enchantment) {
    	if(enchantment == Enchantments.SWEEPING) 
    		return false;
    	
        return enchantment.type == EnumEnchantmentType.WEAPON || enchantment.type == EnumEnchantmentType.DIGGER || enchantment.type == EnumEnchantmentType.BREAKABLE;
    }
	
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.BLUE + I18n.format("item.weapon.brute_axe.desc"));
    }
}
