package mellohi138.netherized.objects.item;

import java.util.UUID;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.util.config.NetherizedConfig;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemNetheriteHorseArmor extends Item {
	protected static final UUID KNOCKBACK_RESISTANCE_MODIFIER = UUID.fromString("ba912d2b-9413-3171-be0e-5b384abadf2a");
	public static final float KNOCKBACK_RESISTANCE_BONUS = NetherizedConfig.netheriteHorseArmorKnockbackResistance;
	private final HorseArmorType armorType;
	
	public ItemNetheriteHorseArmor(String name, HorseArmorType armorType, CreativeTabs tab) {
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
		this.setMaxStackSize(1);
		
		this.armorType = armorType;
	}
    
    @Override
    public void onHorseArmorTick(World world, EntityLiving horse, ItemStack armor) {
    	IAttributeInstance attribute = horse.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
    	if(armor.getItem() == this) {
        	if(attribute.getModifier(KNOCKBACK_RESISTANCE_MODIFIER) == null) {
        		attribute.applyModifier(new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER, "knockback_resistance_bonus", KNOCKBACK_RESISTANCE_BONUS, 0).setSaved(false));
        	}
    	}
    }
	
	@Override
    public HorseArmorType getHorseArmorType(ItemStack stack) {
        return this.armorType;
    }
}