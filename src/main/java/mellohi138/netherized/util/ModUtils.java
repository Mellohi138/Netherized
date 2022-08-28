package mellohi138.netherized.util;

import java.util.Random;

import mellohi138.netherized.util.config.NetherizedConfig;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModUtils {
	public static final AxisAlignedBB WEEPING_VINES_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
	public static final AxisAlignedBB TWISTING_VINES_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
	
	/**
	 * Immunity checker. Code copied and edited from IaF ROtN Edition's Myrmex Workers. Credit goes to SandwichHorror, Democat, and Asterixxx
	*/
	public static boolean isFireproof(Item item) {
		for (String itemName : NetherizedConfig.fireproofItemList) {
			if (ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)) == item) {
				return !NetherizedConfig.fireproofItemBlacklist;
			}
		}
		return NetherizedConfig.fireproofItemBlacklist;
	}
	
	/**
	 * Calculates the precentage of a value.
	 * <p>
	 * 1st value is the value you want to take the precentage of.
	 * <p>
	 * 2nd value is how much precentage you want to use. This value cannot exceed %100.
	 */
	public static float calculatePrecentage(float precentageOf, float precentageVal) {
		return (precentageOf * Math.min(precentageVal, 100.0F)) / 100.0F;
	}
	
	/**
	 * This piece of code is copied from 1.16 Forge, I own no rights to it.
	 */
	public static int getPlantGrowthAmount(Random rand) {
		double d0 = 1.0D;
		int i;
		for(i = 0; rand.nextDouble() < d0; ++i) {
			d0 *= 0.826D;
		} 
		return i;
	}
}