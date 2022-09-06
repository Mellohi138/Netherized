package mellohi138.netherized.util.config;

import mellohi138.netherized.Netherized;
import net.minecraftforge.common.config.Config;

@Config(modid = Netherized.MODID, name = "Netherized/Item_Config")
public class NetherizedItemConfig {
	public static NetheriteConfig netheriteConfig = new NetheriteConfig();
	
	public static BruteAxeConfig bruteAxeConfig = new BruteAxeConfig();
	
	public static InfernoGearConfig infernalConfig = new InfernoGearConfig();
	
	public static class NetheriteConfig {
		@Config.RequiresMcRestart
		@Config.Comment(value = "How much attack damage netherite items will do. The value here gets added to the base damage of the tool/weapon")
		public float netheriteAttackDamage = 4.0F;
		
		@Config.RequiresMcRestart
		@Config.Comment(value = "Determines the mining speed of netherite")
		public float netheriteMiningSpeed = 10.0F;
		
		@Config.RequiresMcRestart
		@Config.RangeInt(min = 0)
		@Config.Comment(value = "The harvest level of netherite. 0 is wood, 1 is stone, 2 is iron, and 3 is diamond")
		public int netheriteHarvestLevel = 4;
		
		@Config.RequiresMcRestart
		@Config.RangeInt(min = 1)
		@Config.Comment(value = "The durability level of netherite")
		public int netheriteDurability = 2031;
		
		@Config.RequiresMcRestart
		@Config.RangeInt(min = 1)
		@Config.Comment(value = "The enchantability level of netherite")
		public int netheriteEnchantability = 15;
		
		@Config.RequiresMcRestart
		@Config.RangeInt(min = 6)
		@Config.Comment(value = "How much protection the netherite armor will give")
		public int netheriteProtectionAmount = 8;
		
		@Config.RequiresMcRestart
		@Config.RangeDouble(min = 1)
		@Config.Comment(value = "How much protection the netherite horse armor will give")
		public float netheriteKnockbackResistance = 0.25F;
		
		@Config.RequiresMcRestart
		@Config.RangeDouble(min = 0F)
		@Config.Comment(value = "How much armor toughness netherite armor will give")
		public float netheriteArmorToughness = 3.0F;
		
		@Config.RequiresMcRestart
		@Config.RangeInt(min = 1)
		@Config.Comment(value = "Calculates the durability of the netherite armor")
		public int netheriteArmorDurabilityMultiplier = 37;
		
		@Config.RequiresMcRestart
		@Config.RangeInt(min = 1)
		@Config.Comment(value = "How much protection the netherite horse armor will give")
		public int netheriteHorseArmorProtectionAmount = 14;
		
		@Config.RequiresMcRestart
		@Config.RangeDouble(min = 0)
		@Config.Comment(value = "How much knockback resistance the netherite horse armor will give")
		public float netheriteHorseArmorKnockbackResistance = 0.4F;
	}
	
	public static class BruteAxeConfig {
		@Config.RequiresMcRestart
		@Config.RangeDouble(min = 0.0F, max = 100.0F)
		@Config.Comment(value = "The amount of precentage damage the brute'l axe will deal")
		public float bruteAxeDamage = 5.0F;
	}
	
	public static class InfernoGearConfig {
		@Config.RequiresMcRestart
		@Config.RangeInt(min = 6)
		@Config.Comment(value = "How much protection the infernal helmet will give")
		public int infernoProtectionAmount = 3;
		
		@Config.RequiresMcRestart
		@Config.RangeDouble(min = 1)
		@Config.Comment(value = "How much protection the infernal helmet will give")
		public float infernoKnockbackResistance = 0.4F;
		
		@Config.RequiresMcRestart
		@Config.RangeDouble(min = 0F)
		@Config.Comment(value = "How much armor toughness the infernal helmet will give")
		public float infernoToughness = 2.0F;
		
		@Config.RequiresMcRestart
		@Config.RangeInt(min = 1)
		@Config.Comment(value = "Calculates the durability of the infernal helmet")
		public int infernoDurabilityMultiplier = 15;
		
		@Config.RequiresMcRestart
		@Config.RangeInt(min = 1)
		@Config.Comment(value = "The enchantability level of the infernal helmet")
		public int infernoEnchantability = 15;
		
		@Config.RequiresMcRestart
		@Config.Comment(value = "The amount fire damage will be divided by when wearing the infernal helmet. The higher the value the less the damage.")
		public float infernalHelmetDamageReduction = 2.0F;
	}
}
