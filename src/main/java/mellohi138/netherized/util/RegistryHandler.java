package mellohi138.netherized.util;

import mellohi138.netherized.init.NetherizedBlocks;
import mellohi138.netherized.init.NetherizedSounds;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;

public class RegistryHandler {
	public static void registerBlockSounds() {
		Blocks.NETHER_BRICK.setSoundType(NetherizedSounds.SOUND_TYPE_NETHER_BRICKS);
		Blocks.NETHER_BRICK_FENCE.setSoundType(NetherizedSounds.SOUND_TYPE_NETHER_BRICKS);
		Blocks.NETHER_BRICK_STAIRS.setSoundType(NetherizedSounds.SOUND_TYPE_NETHER_BRICKS);
		Blocks.QUARTZ_ORE.setSoundType(NetherizedSounds.SOUND_TYPE_NETHER_ORE);
		Blocks.NETHER_WART.setSoundType(NetherizedSounds.SOUND_TYPE_NETHER_WART);
		Blocks.NETHER_WART_BLOCK.setSoundType(NetherizedSounds.SOUND_TYPE_NETHER_WART);
		Blocks.SOUL_SAND.setSoundType(NetherizedSounds.SOUND_TYPE_SOUL_SAND);
	}
	
	public static void registerMobUtils() {
		EntityEnderman.setCarriable(NetherizedBlocks.CRIMSON_NYLIUM, true);
		EntityEnderman.setCarriable(NetherizedBlocks.WARPED_NYLIUM, true);
		EntityEnderman.setCarriable(NetherizedBlocks.SHROOMLIGHT, true);
	}
}
