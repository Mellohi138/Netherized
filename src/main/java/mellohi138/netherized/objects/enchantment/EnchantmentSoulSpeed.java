package mellohi138.netherized.objects.enchantment;

import mellohi138.netherized.Netherized;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentSoulSpeed extends Enchantment {
	public EnchantmentSoulSpeed(String name, Rarity rarityIn, EnumEnchantmentType typeIn) {
		super(rarityIn, typeIn, new EntityEquipmentSlot[] {EntityEquipmentSlot.FEET});
		this.setRegistryName(Netherized.MODID, name);
		this.setName(name);
	}
	
    public int getMinEnchantability(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }
	
    public boolean isTreasureEnchantment() {
        return true;
    }
    
    public int getMaxLevel() {
        return 3;
     }
}
