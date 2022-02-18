package mellohi138.netherized.util;

import mellohi138.netherized.util.config.NetherizedConfig;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * Immunity checker. Code copied from IaF ROtN Edition's Myrmex Workers. Credit goes to SandwichHorror, Democat, and Asterixxx
*/
public class IsImmune {
	public static boolean isFireproof(Item item) {
		for (String itemName : NetherizedConfig.fireproofItemList) {
			if (ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)) == item) {
				return true;
			}
		}
		return false;
	}
}
