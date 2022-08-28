package mellohi138.netherized.objects.block;

import mellohi138.netherized.Netherized;
import net.minecraft.block.BlockButton;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockButtonBase extends BlockButton {
	public BlockButtonBase(String nameIn, CreativeTabs tabIn) {
		super(false);
		this.setTranslationKey(nameIn);
		this.setRegistryName(Netherized.MODID, nameIn);
		this.setCreativeTab(tabIn);
	}

	@Override
	protected void playClickSound(EntityPlayer player, World worldIn, BlockPos pos) {
		worldIn.playSound(player, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}

	@Override
	protected void playReleaseSound(World worldIn, BlockPos pos) {	
		worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}
}
