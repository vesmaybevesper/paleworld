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
    @Entry(category = Cos) public static float fogStart = 0.5F;
    @Entry(category = Cos) public static float fogEnd = 20F;
    @Entry(category = Cos) public static float fogTransparency = 0.7F;
    @Entry(category = Cos) public static horrorVals horrorModeSelect = horrorVals.FALSE;
     public static int strippedOakCount = 3;
     public static float strippedOakExtraChance = 0.05F;
     public static int strippedOakExtraCount = 2;
     public static int bareOakCount = 2;
     public static float bareOakExtraChance = 0.05F;
     public static int bareOakExtraCount = 2;
     public static int barestrippedOakCount = 1;
     public static float barestrippedOakExtraChance = 0.05F;
     public static int barestrippedOakExtraCount = 2;
     public static int smallPaleOakChance = 4;
     public static float smallPaleOakExtraChance = 0.05F;
     public static int smallPaleOakExtraCount = 2;
     public static int strippedSmallPaleOakChance = 3;
     public static float strippedSmallPaleOakExtraChance = 0.05F;
     public static int strippedSmallPaleOakExtraCount = 2;
     public static int bareSmallPaleOakChance = 5;
     public static float bareSmallPaleOakExtraChance = 0.05F;
     public static int bareSmallPaleOakExtraCount = 2;
     public static int bareStrippedSmallPaleOakChance = 4;
     public static float bareStrippedSmallPaleOakExtraChance = 0.05F;
     public static int bareStrippedSmallPaleOakExtraCount = 3;
    }

