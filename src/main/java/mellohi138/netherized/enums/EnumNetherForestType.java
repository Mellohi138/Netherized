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
    
    public Block getVegetationBlocks(EnumNetherForestType forestType, String blockID) {
    	switch(forestType) {
    	case CRIMSON:
    		switch(blockID) {
    		case "nylium":
    			return NetherizedBlocks.CRIMSON_NYLIUM;
    		case "roots":
    			return NetherizedBlocks.CRIMSON_ROOTS;
    		case "fungus":
    			return NetherizedBlocks.CRIMSON_FUNGUS;
    		case "stem":
    			return NetherizedBlocks.CRIMSON_STEM;
    		case "wart":
    			return Blocks.NETHER_WART_BLOCK;
    		}
    	case WARPED:
    		switch(blockID) {
    		case "nylium":
    			return NetherizedBlocks.WARPED_NYLIUM;
    		case "roots":
    			return NetherizedBlocks.WARPED_ROOTS;
    		case "fungus":
    			return NetherizedBlocks.WARPED_FUNGUS;
    		case "stem":
    			return NetherizedBlocks.WARPED_STEM;
    		case "wart":
    			return NetherizedBlocks.WARPED_WART_BLOCK;
    		}
    	}
    	return Blocks.AIR;
    }
}
