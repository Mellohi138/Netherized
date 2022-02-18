package mellohi138.netherized.util;

import mellohi138.netherized.init.NetherizedBlocks;
import mellohi138.netherized.init.NetherizedItems;
import net.minecraftforge.oredict.OreDictionary;

public class NetherizedOreDictionary {
	public static void registerOreDict() {
		OreDictionary.registerOre("oreDebris", NetherizedBlocks.ANCIENT_DEBRIS);
		OreDictionary.registerOre("oreIron", NetherizedBlocks.NETHER_IRON_ORE);
		OreDictionary.registerOre("oreGold", NetherizedBlocks.NETHER_GOLD_ORE);	
		OreDictionary.registerOre("ingotNetherite", NetherizedItems.NETHERITE_INGOT);
		OreDictionary.registerOre("blockNetherite", NetherizedBlocks.NETHERITE_BLOCK);
		OreDictionary.registerOre("toolAxe", NetherizedItems.NETHERITE_AXE);
		OreDictionary.registerOre("materialStoneTool", NetherizedBlocks.BLACKSTONE);
	}
}
