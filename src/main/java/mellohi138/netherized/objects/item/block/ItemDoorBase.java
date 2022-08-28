package mellohi138.netherized.objects.item.block;

import net.minecraft.block.BlockDoor;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;

public class ItemDoorBase extends ItemDoor {
	private final BlockDoor door;
	
	public ItemDoorBase(BlockDoor door) {
		super(door);
		this.setTranslationKey(door.getRegistryName().getPath());
		this.setRegistryName(door.getRegistryName());
		this.setCreativeTab(door.getCreativeTab());
		
		this.door = door;
	}
	
    public String getTranslationKey(ItemStack stack) {
        return this.door.getTranslationKey();
    }

    public String getTranslationKey() {
        return this.door.getTranslationKey();
    }
}
