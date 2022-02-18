package mellohi138.netherized.proxy;

import mellohi138.netherized.Netherized;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Netherized.MODID)
public class CommonProxy {
    @SideOnly(Side.CLIENT)
	public void registerCustomModel(Item item, int metadata) {
	}
    
    @SideOnly(Side.CLIENT)
    public void registerEntityModels() {
    }
    
    @SideOnly(Side.CLIENT)
    public void spawnParticle(String particleName, double x, double y, double z, double motX, double motY, double motZ) {
    }
}