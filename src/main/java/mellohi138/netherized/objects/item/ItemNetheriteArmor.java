package mellohi138.netherized.objects.item;

import com.google.common.collect.Multimap;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.objects.entity.EntityFireproofItem;
import mellohi138.netherized.util.config.NetherizedConfig;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemNetheriteArmor extends ItemArmor {
	private final AttributeModifier knockbackResistance;

	public ItemNetheriteArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, CreativeTabs tab) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
		
		this.knockbackResistance = new AttributeModifier("generic.knockbackResistance", NetherizedConfig.netheriteKnockbackResistance, 0);
	}
	
	@Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
        if (equipmentSlot == this.armorType) {
            multimap.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), this.knockbackResistance);
        }
        return multimap;
    }
	
    @Override
	public boolean hasCustomEntity(final ItemStack stack) {
		return true;
	}
	
    @Override
	public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
	    return new EntityFireproofItem(world, location, itemstack);
	}
}