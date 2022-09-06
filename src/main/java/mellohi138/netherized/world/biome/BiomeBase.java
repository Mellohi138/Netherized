package mellohi138.netherized.world.biome;

import mellohi138.netherized.Netherized;
import net.minecraft.world.biome.Biome;

public abstract class BiomeBase extends Biome {
	public BiomeBase(String nameIn, BiomeProperties properties) {
		super(properties);
		properties.setBaseBiome(nameIn);
		
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        
        this.setRegistryName(Netherized.MODID, nameIn);
	}
	
	public abstract void setTypes();
}
