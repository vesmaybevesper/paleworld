package vesper.pw.world.gen;

public class PaleWorldWorldGen {
    public static void genWorld(){
        RockGen.rockGenerator();
        TreeGen.generateTrees();
    }
}
