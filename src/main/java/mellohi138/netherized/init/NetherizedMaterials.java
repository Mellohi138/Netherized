package mellohi138.netherized.init;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.util.config.NetherizedConfig;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class NetherizedMaterials {
	public static final ToolMaterial NETHERITE_TOOL_MATERIAL = EnumHelper.addToolMaterial("netheriteToolMaterial", NetherizedConfig.netheriteHarvestLevel, NetherizedConfig.netheriteDurability, NetherizedConfig.netheriteMiningSpeed, NetherizedConfig.netheriteAttackDamage, NetherizedConfig.netheriteEnchantability).setRepairItem(new ItemStack(NetherizedItems.NETHERITE_INGOT));
	public static final ArmorMaterial NETHERITE_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("netheriteArmorMaterial", Netherized.MODID + ":netherite", NetherizedConfig.netheriteArmorDurabilityMultiplier, new int[]{NetherizedConfig.netheriteProtectionAmount - 5, NetherizedConfig.netheriteProtectionAmount - 2, NetherizedConfig.netheriteProtectionAmount, NetherizedConfig.netheriteProtectionAmount - 5}, 15, NetherizedSounds.ITEM_ARMOR_EQUIP_NETHERITE, NetherizedConfig.netheriteArmorToughness).setRepairItem(new ItemStack(NetherizedItems.NETHERITE_INGOT));
	public static final HorseArmorType NETHERITE_HORSE_ARMOR = EnumHelper.addHorseArmor("netheriteHorseArmor", Netherized.MODID + ":textures/models/armor/horse/horse_armor_netherite.png", NetherizedConfig.netheriteHorseArmorProtectionAmount);
	public static final Material NETHER_WOOD = new Material(MapColor.WOOD);
}
