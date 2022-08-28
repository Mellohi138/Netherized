package mellohi138.netherized.objects.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSlabsBase extends BlockSlab {
	private final IBlockState mainState;
	private final boolean isDouble;
	private static final PropertyEnum<Variant> VARIANT = PropertyEnum.<Variant>create("variant", Variant.class);
	
	@SuppressWarnings("deprecation")
	public BlockSlabsBase(IBlockState mainState, boolean isDouble) {
		super(mainState.getMaterial());
		Block mainBlock = mainState.getBlock();
		this.mainState = mainState;
		
		IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
		this.setDefaultState(isDouble ? state : state.withProperty(HALF, EnumBlockHalf.BOTTOM));
		
		this.setRegistryName(mainState.getBlock().getRegistryName().getPath() + "_slabs" + (isDouble ? "_double" : ""));
		this.setTranslationKey(mainBlock.getRegistryName().getPath() + "_slabs");
		this.setCreativeTab(mainBlock.getCreativeTab());
        this.setHarvestLevel(mainBlock.getHarvestTool(mainState), mainBlock.getHarvestLevel(mainState));
        this.setHardness(mainBlock.blockHardness);
        this.setResistance(mainBlock.blockResistance / 3.0F);
        this.setSoundType(mainBlock.getSoundType());
        
		this.useNeighborBrightness = !isDouble;
        this.isDouble = isDouble;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
		return this.isDouble() ? state : state.withProperty(HALF, (meta == 1) ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		if(!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) {
			meta = 1;
		}
		return meta;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				this.isDouble() ? VARIANT :
					VARIANT, HALF	
		});
	}
	
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    	return Item.getByNameOrId(mainState.getBlock().getRegistryName().toString() + "_slabs");
    }
	
	@Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(Item.getByNameOrId(mainState.getBlock().getRegistryName().toString() + "_slabs"));
	}
	
	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)   {    
		return this.mainState.getMapColor(worldIn, pos);
	}

	@Override
	public String getTranslationKey(int meta) {
		return super.getTranslationKey();
	}

	@Override
	public boolean isDouble() {
		return this.isDouble;
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return Variant.DEFAULT;
	}
	
	private enum Variant implements IStringSerializable {
		DEFAULT();

		@Override
		public String getName() {
			return "default";
		}
	}
}
