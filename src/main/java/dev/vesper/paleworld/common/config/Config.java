package dev.vesper.paleworld.common.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@me.shedaniel.autoconfig.annotation.Config(name = "paleworld")
public class Config implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    boolean horrorMode = false;
    @ConfigEntry.Gui.Tooltip
    public static float fogStart = 0.5F; // original 0.5F
    @ConfigEntry.Gui.Tooltip
    public static float fogEnd = 20F; // original 20F
    @ConfigEntry.Gui.Tooltip
    public static float fogTransparency = 0.7F;

    @ConfigEntry.Category("Pale World")
    @ConfigEntry.Gui.TransitiveObject
    public Config config = new Config();
}
