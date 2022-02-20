package mellohi138.netherized.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import mellohi138.netherized.Netherized;
import mellohi138.netherized.objects.block.*;
import mellohi138.netherized.objects.entity.tile.TileEntityInfernoReactor;

@Mod.EventBusSubscriber(modid = Netherized.MODID)
public class NetherizedBlocks {
	public static final List<Block> BLOCK_LIST = new ArrayList<Block>();
	
	//Blocks
	public static final Block BASALT = NetherizedBlocks.addBlock(new BlockRotatedPillarBase("basalt", Material.ROCK, MapColor.BLACK, "pickaxe", 0, NetherizedSounds.SOUND_TYPE_BASALT, Netherized.NETHERIZED_BLOCKS).setHardness(1.25F).setResistance(4.2F));
	public static final Block BLACKSTONE = NetherizedBlocks.addBlock(new BlockBase("blackstone", Material.ROCK, MapColor.BLACK, "pickaxe", 0, SoundType.STONE, Netherized.NETHERIZED_BLOCKS).setHardness(1.5F).setResistance(6.0F));
	public static final Block SOUL_SOIL = NetherizedBlocks.addBlock(new BlockBase("soul_soil", Material.GROUND, MapColor.BROWN, "shovel", 0, NetherizedSounds.SOUND_TYPE_SOUL_SOIL, Netherized.NETHERIZED_BLOCKS).setHardness(0.5F));
	public static final Block CRYING_OBSIDIAN = NetherizedBlocks.addBlock(new BlockCryingObsidian("crying_obsidian", Material.ROCK, MapColor.BLACK, "pickaxe", 0, SoundType.STONE, Netherized.NETHERIZED_BLOCKS).setHardness(50.0F).setResistance(2000.0F).setLightLevel(0.7F));
	public static final Block GLOWING_OBSIDIAN = NetherizedBlocks.addBlock(new BlockGlowingObsidian("glowing_obsidian", Material.ROCK, MapColor.RED, "pickaxe", 0, SoundType.STONE, Netherized.NETHERIZED_BLOCKS).setHardness(50.0F).setResistance(2000.0F).setLightLevel(1F));
	public static final Block CRIMSON_PLANKS = NetherizedBlocks.addBlock(new BlockBase("crimson_planks", NetherizedMaterials.NETHER_WOOD, MapColor.RED, "axe", 0, SoundType.WOOD, Netherized.NETHERIZED_BLOCKS).setHardness(2.0F).setResistance(3.0F));
	public static final Block WARPED_PLANKS = NetherizedBlocks.addBlock(new BlockBase("warped_planks", NetherizedMaterials.NETHER_WOOD, MapColor.RED, "axe", 0, SoundType.WOOD, Netherized.NETHERIZED_BLOCKS).setHardness(2.0F).setResistance(3.0F));
	public static final Block NETHERITE_BLOCK = NetherizedBlocks.addBlock(new BlockBase("netherite_block", Material.ROCK, MapColor.BLACK, "pickaxe", 3, NetherizedSounds.SOUND_TYPE_NETHERITE_BLOCK, Netherized.NETHERIZED_BLOCKS, true).setHardness(50.0F).setResistance(2000.0F));
	
	//Re-registered Blocks
	public static final Block NETHERRACK = NetherizedBlocks.reRegisterBlock(new BlockNewNetherrack());
	
	//Ores
	public static final Block NETHER_IRON_ORE = NetherizedBlocks.addBlock(new BlockNetherOre("nether_iron_ore", Material.ROCK, MapColor.NETHERRACK, "pickaxe", 0, NetherizedSounds.SOUND_TYPE_NETHER_ORE, Netherized.NETHERIZED_BLOCKS).setHardness(3.0F).setResistance(3.0F));
	public static final Block NETHER_GOLD_ORE = NetherizedBlocks.addBlock(new BlockNetherOre("nether_gold_ore", Material.ROCK, MapColor.NETHERRACK, "pickaxe", 0, NetherizedSounds.SOUND_TYPE_NETHER_ORE, Netherized.NETHERIZED_BLOCKS).setHardness(4.0F).setResistance(4.0F));
	public static final Block ANCIENT_DEBRIS = NetherizedBlocks.addBlock(new BlockBase("ancient_debris", Material.IRON, MapColor.BLACK, "pickaxe", 3, NetherizedSounds.SOUND_TYPE_ANCIENT_DEBRIS, Netherized.NETHERIZED_BLOCKS).setHardness(30.0F).setResistance(2000.0F));
	
	//Mechanical Blocks
	public static final Block INFERNO_REACTOR = NetherizedBlocks.addBlock(new BlockInfernoReactor("inferno_reactor", Material.ANVIL, MapColor.GOLD, "pickaxe", 3, SoundType.STONE, Netherized.NETHERIZED_BLOCKS).setHardness(50.0F).setResistance(2000.0F));
	public static final Block RESPAWN_ANCHOR = NetherizedBlocks.addBlock(new BlockRespawnAnchor("respawn_anchor", Material.ANVIL, MapColor.BLACK, "pickaxe", 3, SoundType.STONE, Netherized.NETHERIZED_BLOCKS).setHardness(50.0F).setResistance(2000.0F));
	
	//Nether Vegetation
	public static final Block CRIMSON_NYLIUM = NetherizedBlocks.addBlock(new BlockNetherNylium("crimson_nylium", Material.ROCK, MapColor.RED_STAINED_HARDENED_CLAY, "pickaxe", 0, NetherizedSounds.SOUND_TYPE_NETHER_NYLIUM, Netherized.NETHERIZED_BLOCKS).setHardness(0.4F));
	public static final Block WARPED_NYLIUM = NetherizedBlocks.addBlock(new BlockNetherNylium("warped_nylium", Material.ROCK, MapColor.GREEN_STAINED_HARDENED_CLAY, "pickaxe", 0, NetherizedSounds.SOUND_TYPE_NETHER_NYLIUM, Netherized.NETHERIZED_BLOCKS).setHardness(0.4F));
	
	public static final Block CRIMSON_ROOTS = NetherizedBlocks.addBlock(new BlockNetherRoots("crimson_roots", Material.PLANTS, MapColor.RED_STAINED_HARDENED_CLAY, NetherizedSounds.SOUND_TYPE_NETHER_ROOTS, Netherized.NETHERIZED_BLOCKS));
	public static final Block WARPED_ROOTS = NetherizedBlocks.addBlock(new BlockNetherRoots("warped_roots", Material.PLANTS, MapColor.RED_STAINED_HARDENED_CLAY, NetherizedSounds.SOUND_TYPE_NETHER_ROOTS, Netherized.NETHERIZED_BLOCKS));
	public static final Block WARPED_SPROUTS = NetherizedBlocks.addBlock(new BlockNetherRoots("warped_sprouts", Material.PLANTS, MapColor.RED_STAINED_HARDENED_CLAY, NetherizedSounds.SOUND_TYPE_WARPED_SPROUTS, Netherized.NETHERIZED_BLOCKS));
	
	public static final Block SHROOMLIGHT = NetherizedBlocks.addBlock(new BlockBase("shroomlight", Material.GRASS, MapColor.RED, "axe", 0, NetherizedSounds.SOUND_TYPE_SHROOMLIGHT, Netherized.NETHERIZED_BLOCKS).setHardness(1.0F).setLightLevel(1F));
	
	@SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for(Block blockIn : NetherizedBlocks.BLOCK_LIST) {
			event.getRegistry().register(blockIn);
		}
    }
    
    private static Block addBlock(Block blockIn) {
    	NetherizedItems.ITEM_LIST.add(new ItemBlock(blockIn).setRegistryName(blockIn.getRegistryName()));
		NetherizedBlocks.BLOCK_LIST.add(blockIn);
    	return blockIn;
    }
    
    private static Block reRegisterBlock(Block blockIn) {
    	NetherizedBlocks.BLOCK_LIST.add(blockIn);
    	return blockIn;
    }
    
	public static void registerTileEntities() {
    	GameRegistry.registerTileEntity(TileEntityInfernoReactor.class, new ResourceLocation(Netherized.MODID, "inferno_reactor"));
    }
}