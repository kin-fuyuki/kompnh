package kn.kinfuyuki.gtnh.kompnh;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class serverp {

    public void preInit(FMLPreInitializationEvent event) {}
    public void init(FMLInitializationEvent event) {}
    public void postInit(FMLPostInitializationEvent event) {}
    public void serverStarting(FMLServerStartingEvent event) {}
	public static String datapath;
	public void updpath(){
		datapath=sp()?
			Minecraft.getMinecraft().mcDataDir.getAbsolutePath()+"/"+
				Minecraft.getMinecraft().theWorld.getWorldInfo().getWorldName()+"/kompnh":
			MinecraftServer.getServer().getFile("")+"/"+
				MinecraftServer.getServer().getWorldName()+"kompnh";
	}
    public boolean sp(){return false;}
}
