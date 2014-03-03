package pokemane.northernrail.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import pokemane.northernrail.api.rail.RailRegistry;
import pokemane.northernrail.api.rail.RailType;
import pokemane.northernrail.client.render.RailIconProvider;
import pokemane.northernrail.common.block.TileEntityRail;
import pokemane.northernrail.common.block.rail.ItemBlockRail;
import pokemane.northernrail.common.block.rail.RailBaseNR;
import pokemane.northernrail.common.block.rail.RailDefault;
import pokemane.northernrail.core.network.packet.PacketPipeline;
import pokemane.northernrail.testing.TestPoweredRail;
import pokemane.northernrail.testing.TestRail;


/**
 * Created by pokemane on 2/15/14.
 */

@Mod(modid = NorthernRail.CHANNEL, name = NorthernRail.MODNAME, version = NRConfig.VERSION, acceptedMinecraftVersions = "[1.7.2,)")


public class NorthernRailLoader {

    @Instance(NorthernRail.CHANNEL)
    public static NorthernRailLoader instance;

    @SidedProxy(clientSide = "pokemane.northernrail.client.ClientProxy", serverSide = "pokemane.northernrail.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Metadata(NorthernRail.CHANNEL)
    public static ModMetadata metadata;

    public static final PacketPipeline packetPipeline = new PacketPipeline();

    public static CreativeTabs creativeTabNR = new CreativeTabNR(CreativeTabs.getNextID(),NorthernRail.MODNAME);

    public static Block newRail;
    public static Block newPoweredRail;
	public static Block railBaseNR;

    @EventHandler
    public void load(FMLInitializationEvent initializationEvent){
        packetPipeline.initalize();
	    GameRegistry.registerTileEntity(TileEntityRail.class, "railTile");
    }

    @EventHandler
    public void preinit(FMLPreInitializationEvent preInitializationEvent){
	    RailRegistry.addRailType(new RailType(RailRegistry.getNextAvailableRailId(), "rail_normal", new RailDefault(), RailIconProvider.INSTANCE));
	    RailRegistry.addRailType(new RailType(RailRegistry.getNextAvailableRailId(), "rail_golden", new RailDefault(), RailIconProvider.INSTANCE));
	    RailRegistry.addRailType(new RailType(RailRegistry.getNextAvailableRailId(), "rail_activator", new RailDefault(), RailIconProvider.INSTANCE));
		//todo get these specific instances saved to my mod class for later reference.
        newRail = (new TestRail()).setBlockName("NewRail").setBlockTextureName("rail_normal").setCreativeTab(creativeTabNR);
        GameRegistry.registerBlock(newRail,"New Rail");
        newPoweredRail = (new TestPoweredRail()).setBlockName("NewPoweredRail").setBlockTextureName("rail_golden").setCreativeTab(creativeTabNR);
        GameRegistry.registerBlock(newPoweredRail, "New Powered Rail");
	    railBaseNR = (new RailBaseNR().setBlockName("testBlock").setBlockTextureName("rail_normal"));
	    GameRegistry.registerBlock(railBaseNR, ItemBlockRail.class, "TestRailBlock");

	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(railBaseNR,1,0),"xy","yx",'x', new ItemStack(Blocks.dirt),'y',new ItemStack(Blocks.stone)).setMirrored(false));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(railBaseNR,1,1),"xy","yx",'x', new ItemStack(Blocks.stone),'y',new ItemStack(Blocks.dirt)).setMirrored(false));
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent postInitializationEvent){
        packetPipeline.postInitialize();
    }

}
