package pokemane.northernrail.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import pokemane.northernrail.util.network.packet.PacketPipeline;

/**
 * Created by pokemane on 2/15/14.
 */

@Mod(modid = NorthernRail.CHANNEL, name = NorthernRail.MODNAME, version = NRConfig.VERSION, acceptedMinecraftVersions = "[1.7.2,)")


public class NorthernRailLoader {

    @Instance(NorthernRail.CHANNEL)
    public static NorthernRail instance;

    public static final PacketPipeline packetPipeline = new PacketPipeline();

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
