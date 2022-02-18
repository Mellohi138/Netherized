package mellohi138.netherized.util.config;

import mellohi138.netherized.Netherized;
import net.minecraftforge.common.config.Config;

@Config(modid = Netherized.MODID)
public class NetherizedConfig {
	@Config.Comment(value = "How much attack damage netherite items will do. The value here gets added to the base damage of the tool/weapon")
	public static float netheriteAttackDamage = 4.0F;
	
	@Config.Comment(value = "Determines the mining speed of netherite")
	public static float netheriteMiningSpeed = 10.0F;
	
	@Config.RangeInt(min = 0)
	@Config.Comment(value = "The harvest level of netherite. 0 is wood, 1 is stone, 2 is iron, and 3 is diamond")
	public static int netheriteHarvestLevel = 4;
	
	@Config.RangeInt(min = 1)
	@Config.Comment(value = "The durability level of netherite")
	public static int netheriteDurability = 2031;
	
	@Config.RangeInt(min = 1)
	@Config.Comment(value = "The enchantability level of netherite")
	public static int netheriteEnchantability = 15;
	
	@Config.RangeInt(min = 6)
	@Config.Comment(value = "How much protection the netherite armor will give")
	public static int netheriteProtectionAmount = 8;
	
	@Config.RangeDouble(min = 1)
	@Config.Comment(value = "How much protection the netherite horse armor will give")
	public static float netheriteKnockbackResistance = 0.25F;
	
	@Config.RangeDouble(min = 1F)
	@Config.Comment(value = "How much armor toughness netherite armor will give")
	public static float netheriteArmorToughness = 3.0F;
	
	@Config.RangeInt(min = 1)
	@Config.Comment(value = "Calculates the durability of the netherite armor")
	public static int netheriteArmorDurabilityMultiplier = 37;
	
	@Config.RangeInt(min = 1)
	@Config.Comment(value = "How much protection the netherite horse armor will give")
	public static int netheriteHorseArmorProtectionAmount = 14;
	
	@Config.RangeDouble(min = 1)
	@Config.Comment(value = "How much protection the netherite horse armor will give")
	public static float netheriteHorseArmorKnockbackResistance = 1F;
	
	@Config.Comment(value = "Whether or not allow netherite anvil recipes to be used")
	public static boolean netheriteAnvilRecipes = true;
	
	@Config.RangeDouble(min = 1.0F, max = 100.0F)
	@Config.Comment(value = "The amount of precentage damage the brute'l axe will deal")
	public static float bruteAxeDamage = 5.0F;
	
	@Config.Comment(value = "Which items will be fireproof. Works with 'modID:itemName', does not work with metadatas.")
    public static String[] fireproofItemList = new String[] {
    		Netherized.MODID + ":ancient_debris",
    		Netherized.MODID + ":netherite_scrap",
    		Netherized.MODID + ":netherite_ingot",
    		Netherized.MODID + ":netherite_sword",
    		Netherized.MODID + ":netherite_shovel",
    		Netherized.MODID + ":netherite_pickaxe",
    		Netherized.MODID + ":netherite_axe",
    		Netherized.MODID + ":netherite_hoe",
    		Netherized.MODID + ":netherite_helmet",
    		Netherized.MODID + ":netherite_chestplate",
    		Netherized.MODID + ":netherite_leggings",
    		Netherized.MODID + ":netherite_boots",
    		Netherized.MODID + ":netherite_horse_armor",
    		Netherized.MODID + ":netherite_block"
    };
}
