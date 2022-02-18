package mellohi138.netherized.objects.block;

import mellohi138.netherized.init.NetherizedSounds;
import net.minecraft.block.BlockSoulSand;

public class BlockNewSoulSand extends BlockSoulSand {
	public BlockNewSoulSand() {
		super();
		this.setSoundType(NetherizedSounds.SOUND_TYPE_SOUL_SAND);
        this.setRegistryName("minecraft", "soul_sand");
        this.setTranslationKey("hellsand");
        this.setHardness(0.5F);
	}
}
