package mellohi138.netherized.init;

import java.util.ArrayList;
import java.util.List;

import mellohi138.netherized.Netherized;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Netherized.MODID)
public class NetherizedBiomes {
	public static final List<Biome> BIOME_LIST = new ArrayList<Biome>();
	
	private static Biome addBiome(Biome biomeIn) {
		BIOME_LIST.add(biomeIn);
		return biomeIn;
	}
	
	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		for(Biome biomeIn : BIOME_LIST) {
			event.getRegistry().register(biomeIn);
		}
	}
}
