package kn.kinfuyuki.gtnh.kompnh;

import com.myname.mymodid.Tags;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = mod.ID, version = Tags.VERSION, name = "kompnh", acceptedMinecraftVersions = "[1.7.10]")
public class mod {
    public static final String ID = "kompnh";
    public static final Logger LOG = LogManager.getLogger(ID);

    @SidedProxy(clientSide = "kn.kinfuyuki.gtnh.client", serverSide = "kn.kinfuyuki.gtnh.server")
    public static serverp srv;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        srv.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        srv.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        srv.postInit(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        srv.serverStarting(event);
    }

}
