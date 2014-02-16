package pokemane.northernrail.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import pokemane.northernrail.util.network.packet.PacketPipeline;

/**
 * Created by pokemane on 2/15/14.
 */

@Mod(modid = NorthernRail.CHANNEL, name = NorthernRail.MODNAME, version = NRConfig.VERSION, acceptedMinecraftVersions = "[1.7.2,)")


public class NorthernRailLoader {

    @Instance(NorthernRail.CHANNEL)
    public static NorthernRail instance;

    @SidedProxy(clientSide = "pokemane.northernrail.client.ClientProxy", serverSide = "pokemane.northernrail.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Metadata(NorthernRail.CHANNEL)
    public static ModMetadata metadata;

    public static final PacketPipeline packetPipeline = new PacketPipeline();

    public static CreativeTabs creativeTabNR = new CreativeTabNR(CreativeTabs.getNextID(),NorthernRail.MODNAME);

    @EventHandler
    public void load(FMLInitializationEvent initializationEvent){
        packetPipeline.initalize();

    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent preInitializationEvent){

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent postInitializationEvent){
        packetPipeline.postInitialize();
    }

}
