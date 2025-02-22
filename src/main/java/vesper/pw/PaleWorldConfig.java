package vesper.pw;

import eu.midnightdust.lib.config.MidnightConfig;

public class PaleWorldConfig extends MidnightConfig {

    private enum horrorVals {FALSE, TRUE}

    public static final String Mobs = "Mobs";
    public static final String Gen = "Generation";
    public static final String Cos = "Cosmetic";

    @Comment(category = Gen, centered = true) public static Comment warn;
    @Entry(category = Gen) public static int palecaveweight = 4;
    /*@Entry(category = Cos) public horrorVals horrorModeSelect = horrorVals.FALSE;*/
    public static Boolean horrorMode = Boolean.FALSE;
    @Entry(category = Gen) public static int strippedOakCount = 1;
    @Entry(category = Gen) public static float strippedOakExtraChance = 0.05F;
    @Entry(category = Gen) public static int strippedOakExtraCount = 2;

}
