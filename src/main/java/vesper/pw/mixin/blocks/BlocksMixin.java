package vesper.pw.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;
import vesper.pw.PaleWorldConfig;
import vesper.pw.sound.CustomSounds;

@Environment(EnvType.CLIENT)
@Mixin(Blocks.class)
public class BlocksMixin {
    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractBlock$Settings;create()Lnet/minecraft/block/AbstractBlock$Settings;"), slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=open_eyeblossom")))
    private static AbstractBlock.Settings openEyeblossom(AbstractBlock.Settings settings){
        if (PaleWorldConfig.horrorMode){
            return settings.luminance(blockstate -> 3);
        }
        return settings.luminance(blockstate -> 5);
    }

    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Blocks;createFlowerPotSettings()Lnet/minecraft/block/AbstractBlock$Settings;"), slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=potted_open_eyeblossom")))
    private static AbstractBlock.Settings pottedOpenEyeblossom(AbstractBlock.Settings settings){
        if (PaleWorldConfig.horrorMode){
                return settings.luminance(blockstate -> 3);
        }
        return settings.luminance(blockstate -> 5);
    }
}
