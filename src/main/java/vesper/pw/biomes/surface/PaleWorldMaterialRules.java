package vesper.pw.biomes.surface;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import vesper.pw.biomes.PaleWorldBiomes;

public class PaleWorldMaterialRules {

    static MaterialRules.MaterialRule MOSS = makeStateRule(Blocks.PALE_MOSS_BLOCK);
    static MaterialRules.MaterialRule CLAY = makeStateRule(Blocks.CLAY);
    static MaterialRules.MaterialRule LOG = makeStateRule(Blocks.PALE_OAK_LOG);
    static  MaterialRules.MaterialRule LEAVES = makeStateRule(Blocks.PALE_OAK_LEAVES);


    public static MaterialRules.MaterialRule makeRules(){
        return MaterialRules.sequence(
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(PaleWorldBiomes.PALE_CAVE), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MOSS)))
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
            return MaterialRules.block(block.getDefaultState());
    }
}
