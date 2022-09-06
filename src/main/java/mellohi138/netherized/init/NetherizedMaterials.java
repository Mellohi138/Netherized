package mellohi138.netherized.init;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.util.config.NetherizedItemConfig;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class NetherizedMaterials {
	public static final ToolMaterial NETHERITE_TOOL_MATERIAL = EnumHelper.addToolMaterial("netheriteToolMaterial", NetherizedItemConfig.netheriteConfig.netheriteHarvestLevel, NetherizedItemConfig.netheriteConfig.netheriteDurability, NetherizedItemConfig.netheriteConfig.netheriteMiningSpeed, NetherizedItemConfig.netheriteConfig.netheriteAttackDamage, NetherizedItemConfig.netheriteConfig.netheriteEnchantability).setRepairItem(new ItemStack(NetherizedItems.NETHERITE_INGOT));
	public static final ArmorMaterial NETHERITE_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("netheriteArmorMaterial", Netherized.MODID + ":netherite", NetherizedItemConfig.netheriteConfig.netheriteArmorDurabilityMultiplier, new int[]{NetherizedItemConfig.netheriteConfig.netheriteProtectionAmount - 5, NetherizedItemConfig.netheriteConfig.netheriteProtectionAmount - 2, NetherizedItemConfig.netheriteConfig.netheriteProtectionAmount, NetherizedItemConfig.netheriteConfig.netheriteProtectionAmount - 5}, NetherizedItemConfig.netheriteConfig.netheriteEnchantability, NetherizedSounds.ITEM_ARMOR_EQUIP_NETHERITE, NetherizedItemConfig.netheriteConfig.netheriteArmorToughness).setRepairItem(new ItemStack(NetherizedItems.NETHERITE_INGOT));
	public static final ArmorMaterial INFERNAL_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("infernoArmorMaterial", null, NetherizedItemConfig.infernalConfig.infernoDurabilityMultiplier, new int[]{NetherizedItemConfig.infernalConfig.infernoProtectionAmount}, NetherizedItemConfig.infernalConfig.infernoEnchantability, NetherizedSounds.ITEM_ARMOR_EQUIP_NETHERITE, NetherizedItemConfig.infernalConfig.infernoToughness);
	public static final HorseArmorType NETHERITE_HORSE_ARMOR = EnumHelper.addHorseArmor("netheriteHorseArmor", Netherized.MODID + ":textures/models/armor/horse/horse_armor_netherite.png", NetherizedItemConfig.netheriteConfig.netheriteHorseArmorProtectionAmount);
	public static final Material NETHER_WOOD = new Material(MapColor.WOOD);
}
