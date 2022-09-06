package mellohi138.netherized.enums;

import mellohi138.netherized.init.NetherizedBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public enum EnumNetherForestType {
	CRIMSON(),
	WARPED();
    
    public EnumNetherForestType getOpposite() {
    	switch(this) {
    	case CRIMSON:
    		return WARPED;
    	case WARPED:
    		return CRIMSON;
    	}
    	return null;
    }
    
    public Block getVegetationBlocks(EnumNetherForestType forestType, int blockID) {
    	switch(forestType) {
    	case CRIMSON:
    		switch(blockID) {
    		case 0:
    			return NetherizedBlocks.CRIMSON_NYLIUM;
    		case 1:
    			return NetherizedBlocks.CRIMSON_ROOTS;
    		case 2:
    			return NetherizedBlocks.CRIMSON_FUNGUS;
    		case 3:
    			return NetherizedBlocks.CRIMSON_STEM;
    		case 4:
    			return Blocks.NETHER_WART_BLOCK;
    		}
    	case WARPED:
    		switch(blockID) {
    		case 0:
    			return NetherizedBlocks.WARPED_NYLIUM;
    		case 1:
    			return NetherizedBlocks.WARPED_ROOTS;
    		case 2:
    			return NetherizedBlocks.WARPED_FUNGUS;
    		case 3:
    			return NetherizedBlocks.WARPED_STEM;
    		case 4:
    			return NetherizedBlocks.WARPED_WART_BLOCK;
    		}
    	}
    	return Blocks.AIR;
    }
}
