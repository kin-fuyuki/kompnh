package kn.kinfuyuki.gtnh.kompnh;

import net.minecraft.client.Minecraft;

public class clientp extends serverp {
    @Override
    public boolean sp(){return Minecraft.getMinecraft().isSingleplayer();}
}
