package vesper.pw.biomes.surface;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import vesper.pw.biomes.PaleWorldBiomes;

public class PaleWorldMaterialRules {

    static MaterialRules.MaterialRule MOSS = makeStateRule(Blocks.PALE_MOSS_BLOCK);



    public static MaterialRules.MaterialRule makeRules(){
        return MaterialRules.sequence(
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(PaleWorldBiomes.PALE_CAVE), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MOSS))),
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(PaleWorldBiomes.PALE_CAVE), MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, MOSS)))
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
            return MaterialRules.block(block.getDefaultState());
    }
}
