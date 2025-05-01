package vesper.pw.world.gen;

public class PaleWorldWorldGen {
    public static void genWorld(){
        VegGen.generateVeg();
        RockGen.rockGenerator();
        TreeGen.generateTrees();
    }
}
