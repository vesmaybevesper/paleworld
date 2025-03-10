package vesper.pw;

import eu.midnightdust.lib.config.MidnightConfig;

public class PaleWorldConfig extends MidnightConfig {

    public static boolean horrorMode = false;

    public enum horrorVals {FALSE, TRUE}

    public static final String Mobs = "C_Mobs";
    public static final String Gen = "B_Generation";
    public static final String Cos = "A_Cosmetic";

    @Comment(category = Gen, centered = true) public static Comment warn;
    @Entry(category = Gen) public static int palecaveweight = 4;
    @Entry(category = Cos) public static float fogStart = 0.5F; // original 0.5F
    @Entry(category = Cos) public static float fogEnd = 20F; // original 20F
    @Entry(category = Cos) public static float fogTransparency = 0.7F;
    @Entry(category = Cos) public static horrorVals horrorModeSelect = horrorVals.FALSE;
    }

