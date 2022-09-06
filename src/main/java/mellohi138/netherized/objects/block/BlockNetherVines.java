package mellohi138.netherized.objects.block;

import java.util.Random;

import javax.annotation.Nullable;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.enums.EnumNetherForestType;
import mellohi138.netherized.init.NetherizedBlocks;
import mellohi138.netherized.util.ModUtils;
import mellohi138.netherized.util.interfaces.ICustomRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockNetherVines extends Block implements IPlantable {
	private static final AxisAlignedBB WEEPING_VINES_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
	private static final AxisAlignedBB TWISTING_VINES_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
	private final EnumNetherForestType forestType;
	protected final EnumFacing side;
	
	public BlockNetherVines(String name, Material blockMaterialIn, MapColor blockMapColorIn, EnumNetherForestType forestTypeIn, EnumFacing sideIn, SoundType type, CreativeTabs tab) {
		super(blockMaterialIn, blockMapColorIn);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
		this.setSoundType(type);
		
		this.setTickRandomly(true);
		
		this.side = sideIn;
		this.forestType = forestTypeIn;
	}
	
	@Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState soil = worldIn.getBlockState(pos.offset(this.side.getOpposite()));
        return this.canSustainBush(soil) || soil.getBlock() instanceof BlockNetherVines;
    }
	
    protected boolean canSustainBush(IBlockState state) {
		switch(this.forestType) {
		case CRIMSON:
			return state.getBlock() == forestType.getVegetationBlocks(this.forestType, 4);
		case WARPED:
			return state.getBlock() == forestType.getVegetationBlocks(this.forestType, 0);
		}
		return false;
    }
    
	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        this.checkAndDropBlock(worldIn, pos, state);
        
        if(worldIn.isAirBlock(pos.offset(this.side)) && worldIn.getBlockState(pos).getBlock() == this) {
            worldIn.setBlockState(pos, this.getGrowingVine());
        }
    }
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		this.checkAndDropBlock(worldIn, pos, state);
	}
	
	@Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, this.getGrowingVine());
    }
	
    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!this.canBlockStay(worldIn, pos, state)) {
    		worldIn.destroyBlock(pos, true);
        }
    }
    
    private boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
    	return this.canPlaceBlockAt(worldIn, pos);
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	switch(this.forestType) {
    	case CRIMSON:
    		return WEEPING_VINES_AABB;
    	case WARPED:
    		return TWISTING_VINES_AABB;
    	}
        return NULL_AABB;
    }
    
    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
	@Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }
	
	@Override
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) { 
    	return true; 
    }
	
	@Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityIn;
			Minecraft mc = Minecraft.getMinecraft();
			if(player.motionY < -0.15F) {
				player.motionY = -0.15F;
			}
			//TODO: Prevent this from working when chat is open
			if(GameSettings.isKeyDown(mc.gameSettings.keyBindJump) && player.motionY < 0.2F) {
				if(player.isSneaking()) {
					player.motionY = 0.0F;
				} else {
					player.motionY = 0.2F;
				}
			}
		}
    }
	
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
    	return EnumPlantType.Nether;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) return getDefaultState();
        return state;
    }
	
	private IBlockState getGrowingVine() {
		switch(this.forestType) {
		case CRIMSON:
			return NetherizedBlocks.WEEPING_VINES_END.getDefaultState();
		case WARPED:
			return NetherizedBlocks.TWISTING_VINES_END.getDefaultState();
		}
		return this.getDefaultState();
	}
	
	public static class BlockNetherVinesEnd extends BlockNetherVines implements IGrowable, ICustomRenderer {
		protected static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
		private final float growthChance = 0.1F;
		
		public BlockNetherVinesEnd(String name, Material blockMaterialIn, MapColor blockMapColorIn, EnumNetherForestType forestTypeIn, EnumFacing side, SoundType type) {
			super(name, blockMaterialIn, blockMapColorIn, forestTypeIn, side, type, null);
			this.setTranslationKey(this.getBodyVine().getBlock().getRegistryName().getPath());
			
	        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
		}
		
		@Override
	    public IBlockState getStateFromMeta(int meta) {
	    	return this.getDefaultState().withProperty(AGE, Integer.valueOf(MathHelper.clamp(meta, 0, 15)));
	    }

	    @Override
	    public int getMetaFromState(IBlockState state) {
	    	return state.getValue(AGE).intValue();
	    }
	    
	    @Override
	    protected BlockStateContainer createBlockState() {
	        return new BlockStateContainer(this, new IProperty[] {
	        		AGE
	        });
	    }
		
		@Override
	    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
	    	return new ItemStack(Item.getItemFromBlock(this.getBodyVine().getBlock()), 1, this.damageDropped(state));
	    }
		
		@Override
		public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
			this.checkAndDropBlock(worldIn, pos, state);
            BlockPos blockPos = pos.offset(this.side);
            
	        if (state.getValue(AGE) < 15 && ForgeHooks.onCropsGrowPre(worldIn, blockPos, worldIn.getBlockState(blockPos), rand.nextFloat() < this.growthChance)) {
	            if (worldIn.isAirBlock(blockPos)) {
		        	worldIn.setBlockState(blockPos, state.cycleProperty(AGE));
		        	ForgeHooks.onCropsGrowPost(worldIn, blockPos, worldIn.getBlockState(blockPos), state);
	            }
	        }
		}
		
		@Override
	    public boolean getTickRandomly() {
	        return this.getDefaultState().getValue(AGE) < 15 && super.getTickRandomly();
		}
		
		@Override
	    protected boolean canSustainBush(IBlockState state) {
			return super.canSustainBush(state) || state.getBlock().getDefaultState() == this.getBodyVine();
	    }
	    
	    @Override
	    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
	    	this.checkAndDropBlock(worldIn, pos, state);
	    	
	        if(worldIn.getBlockState(pos.offset(this.side)).getBlock() == this) {
	            worldIn.setBlockState(pos, this.getBodyVine());
	        }
	    }
	    
		@Override
		public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
			return true;
		}

		@Override
		public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
			return worldIn.isAirBlock(pos.offset(this.side)) && state.getValue(AGE) < 15;
		}

		@Override
		public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
			BlockPos blockPos = pos.offset(this.side);
			int growthAmount = ModUtils.getPlantGrowthAmount(rand);
		    int age = Math.min(state.getValue(AGE) + growthAmount, 15);
			
			for(int k = 0; k < growthAmount; ++k) {
				worldIn.setBlockState(blockPos, state.withProperty(AGE, Integer.valueOf(age)));
		        blockPos = blockPos.offset(this.side);
		        age = Math.min(age + 1, age);
			}
		}
		
	    private IBlockState getBodyVine() {
	    	switch(super.forestType) {
	    	case CRIMSON:
	    		return NetherizedBlocks.WEEPING_VINES.getDefaultState();
	    	case WARPED:
	    		return NetherizedBlocks.TWISTING_VINES.getDefaultState();
	    	}
	    	return this.getDefaultState();
	    }
		
	    //We don't need to give this block a special item texture, so use the body vine texture instead
		@Override
		@SideOnly(Side.CLIENT)
		public void registerModels() {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(Item.getItemFromBlock(this.getBodyVine().getBlock()).getRegistryName(), "intentory"));
		}
	}
}