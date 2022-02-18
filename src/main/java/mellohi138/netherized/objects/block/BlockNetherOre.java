package mellohi138.netherized.objects.block;

import java.util.Random;

import mellohi138.netherized.Netherized;
import mellohi138.netherized.init.NetherizedBlocks;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockNetherOre extends BlockOre {
    public BlockNetherOre(String name, Material material, MapColor color, String usedTool, int toolStrength, SoundType soundType, CreativeTabs tab) {
		super(color);
		this.setTranslationKey(name);
		this.setRegistryName(Netherized.MODID, name);
		this.setCreativeTab(tab);
        this.setHarvestLevel(usedTool, toolStrength);
        this.setSoundType(soundType);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (this == NetherizedBlocks.NETHER_GOLD_ORE) {
            return Items.GOLD_NUGGET;
        } else {
            return Item.getItemFromBlock(this);
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random) {
        return this == NetherizedBlocks.NETHER_GOLD_ORE ? 2 + random.nextInt(7) : 1;
    }
    
    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {
            int i = 0;
            if (this == NetherizedBlocks.NETHER_GOLD_ORE) {
                i = MathHelper.getInt(rand, 0, 1);
            }
            return i;
        }
        return 0;
    }
}