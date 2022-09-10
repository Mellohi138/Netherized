package mellohi138.netherized.objects.block;

import mellohi138.netherized.Netherized;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockLogBase extends BlockLog {
	private final Material blockMaterial;
	private final MapColor blockColor;
	
	public BlockLogBase(String name, Material material, MapColor color, String usedTool, int toolStrength, SoundType soundType, CreativeTabs tab) {
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
        this.setHarvestLevel(usedTool, toolStrength);
        this.setSoundType(soundType);
        
        this.blockMaterial = material;
        this.blockColor = color;
	}
	
	@Override
    public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState();
		
		switch(meta) {
		case 1:
			state = state.withProperty(LOG_AXIS, EnumAxis.X);
			break;
		case 2:
			state = state.withProperty(LOG_AXIS, EnumAxis.Y);
			break;
		case 3:
			state = state.withProperty(LOG_AXIS, EnumAxis.Z);
			break;
		default:
			state = state.withProperty(LOG_AXIS, EnumAxis.NONE);
		}
		
		return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
		int i = 0;
		
		switch(state.getValue(LOG_AXIS)) {
		case X:
			i = 1;
			break;
			
		case Y:
			i = 2;
			break;
			
		case Z:
			i = 3;
			
		default:
			break;
		}
		
		return i;
    }
    
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {	
        		LOG_AXIS
        });
    }
	
	@Override
    public Material getMaterial(IBlockState state) {
        return this.blockMaterial;
    }
	
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return this.blockColor;
    }
}
