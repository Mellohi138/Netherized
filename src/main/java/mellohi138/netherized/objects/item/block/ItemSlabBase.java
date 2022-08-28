package mellohi138.netherized.objects.item.block;

import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;

public class ItemSlabBase extends ItemSlab {
	public ItemSlabBase(BlockSlab halfSlab, BlockSlab doubleSlab) {
		super(halfSlab, halfSlab, doubleSlab);
		this.setRegistryName(halfSlab.getRegistryName());
	}
}
