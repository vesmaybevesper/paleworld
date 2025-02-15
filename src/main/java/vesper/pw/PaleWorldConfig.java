package vesper.pw;

import eu.midnightdust.lib.config.MidnightConfig;

public class PaleWorldConfig extends MidnightConfig {

    public static final String Mobs = "Mobs";
    public static final String Gen = "Generation";

    @Comment(category = Gen, centered = true) public static Comment warn;
    @Entry(category = Gen) public static int palecaveweight = 4;
    @Entry(category = Gen) public static int strippedOakCount = 1;
    @Entry(category = Gen) public static float strippedOakExtraChance = 0.05F;
    @Entry(category = Gen) public static int strippedOakExtraCount = 2;

}
