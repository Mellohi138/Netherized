package mellohi138.netherized.util;

import mellohi138.netherized.init.NetherizedBlocks;
import net.minecraft.entity.monster.EntityEnderman;

public class RegistryHandler {
	public static void registerMobUtils() {
		EntityEnderman.setCarriable(NetherizedBlocks.CRIMSON_NYLIUM, true);
		EntityEnderman.setCarriable(NetherizedBlocks.WARPED_NYLIUM, true);
		EntityEnderman.setCarriable(NetherizedBlocks.SHROOMLIGHT, true);
	}
}
