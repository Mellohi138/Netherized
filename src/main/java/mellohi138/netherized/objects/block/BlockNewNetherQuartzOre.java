package mellohi138.netherized.objects.block;

import java.util.Random;

import mellohi138.netherized.init.NetherizedBlocks;
import mellohi138.netherized.init.NetherizedSounds;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockNewNetherQuartzOre extends BlockOre {
	public BlockNewNetherQuartzOre() {
		super(MapColor.NETHERRACK);
		this.setTranslationKey("netherquartz");
		this.setRegistryName("minecraft", "quartz_ore");
        this.setHarvestLevel("pickaxe", 0);
        this.setSoundType(NetherizedSounds.SOUND_TYPE_NETHER_ORE);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
	}
	
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {  
		return this == NetherizedBlocks.NETHER_QUARTZ_ORE ? Items.QUARTZ : Item.getItemFromBlock(this);
    }
	
    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {
            int i = 0;
            if (this == NetherizedBlocks.NETHER_QUARTZ_ORE) {
                i = MathHelper.getInt(rand, 2, 5);
            }
            return i;
        }
        return 0;
    }
}