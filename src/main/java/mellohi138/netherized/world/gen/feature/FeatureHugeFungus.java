package mellohi138.netherized.world.gen.feature;

import java.util.Random;

import mellohi138.netherized.enums.EnumNetherForestType;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class FeatureHugeFungus extends WorldGenerator {
	private final EnumNetherForestType forestType;
	
	public FeatureHugeFungus(EnumNetherForestType forestTypeIn) {
		this(forestTypeIn, true);
	}
	
	public FeatureHugeFungus(EnumNetherForestType forestTypeIn, boolean notify) {
		super(notify);
		
		this.forestType = forestTypeIn;
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos) {
		Block nylium = this.forestType.getVegetationBlocks(this.forestType, 0);
		Block soil = worldIn.getBlockState(pos.down()).getBlock();
		if(nylium == soil) {
			
		}
		return false;
	}
 }
