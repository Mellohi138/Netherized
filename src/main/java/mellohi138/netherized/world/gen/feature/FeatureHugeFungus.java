package mellohi138.netherized.world.gen.feature;

import java.util.Random;

import mellohi138.netherized.enums.EnumNetherForestType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
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
	
    protected boolean canGrowInto(Block blockType) {
        Material material = blockType.getDefaultState().getMaterial();
        return material == Material.AIR || material == Material.LEAVES || blockType == Blocks.GRASS || blockType == Blocks.DIRT || blockType == Blocks.LOG || blockType == Blocks.LOG2 || blockType == Blocks.SAPLING || blockType == Blocks.VINE;
    }
	
    public boolean isReplaceable(World world, BlockPos pos) {
        net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos) || state.getBlock().isWood(world, pos) || canGrowInto(state.getBlock());
    }
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos) {
		Block nylium = this.forestType.getVegetationBlocks(this.forestType, "nylium");
		Block soil = worldIn.getBlockState(pos.down()).getBlock();
		if(nylium == soil) {
	        int i = rand.nextInt(3) + 5;

	        boolean flag = true;

	        if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256) {
	            for (int j = pos.getY(); j <= pos.getY() + 1 + i; ++j) {
	                int k = 1;

	                if (j == pos.getY()) {
	                    k = 0;
	                }

	                if (j >= pos.getY() + 1 + i - 2) {
	                    k = 2;
	                }

	                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

	                for (int l = pos.getX() - k; l <= pos.getX() + k && flag; ++l) {
	                    for (int i1 = pos.getZ() - k; i1 <= pos.getZ() + k && flag; ++i1) {
	                        if (j >= 0 && j < worldIn.getHeight()) {
	                            if (!this.isReplaceable(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) {
	                                flag = false;
	                            }
	                        } else {
	                            flag = false;
	                        }
	                    }
	                }
	            }

	            if (!flag) {
	                return false;
	            } else {
	                if (pos.getY() < worldIn.getHeight() - i - 1) {
	                    for (int i2 = pos.getY() - 3 + i; i2 <= pos.getY() + i; ++i2) {
	                        int k2 = i2 - (pos.getY() + i);
	                        int l2 = 1 - k2 / 2;

	                        for (int i3 = pos.getX() - l2; i3 <= pos.getX() + l2; ++i3) {
	                            int j1 = i3 - pos.getX();

	                            for (int k1 = pos.getZ() - l2; k1 <= pos.getZ() + l2; ++k1) {
	                                int l1 = k1 - pos.getZ();

	                                if (Math.abs(j1) != l2 || Math.abs(l1) != l2 || rand.nextInt(2) != 0 && k2 != 0) {
	                                    BlockPos blockpos = new BlockPos(i3, i2, k1);
	                                    IBlockState state2 = worldIn.getBlockState(blockpos);

	                                    if (state2.getBlock().isAir(state2, worldIn, blockpos) || state2.getBlock().isAir(state2, worldIn, blockpos)) {
	                                        this.setBlockAndNotifyAdequately(worldIn, blockpos, this.forestType.getVegetationBlocks(this.forestType, "wart").getDefaultState());
	                                    }
	                                }
	                            }
	                        }
	                    }

	                    for (int j2 = 0; j2 < i; ++j2) {
	                        BlockPos upN = pos.up(j2);
	                        IBlockState state2 = worldIn.getBlockState(upN);

	                        if (state2.getBlock().isAir(state2, worldIn, upN) || state2.getBlock().isLeaves(state2, worldIn, upN)) {
	                            this.setBlockAndNotifyAdequately(worldIn, pos.up(j2), this.forestType.getVegetationBlocks(this.forestType, "stem").getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
	                        }
	                    }

	                    return true;
	                } else {
	                    return false;
	                }
	            }
	        } else {
	            return false;
	        }
		}
		return false;
	}
 }
