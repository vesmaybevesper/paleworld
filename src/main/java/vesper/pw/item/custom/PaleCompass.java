package vesper.pw.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LodestoneTrackerComponent;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class PaleCompass extends Item {
    private static final Text PALE_COMPASS = Text.translatable("item.pale-world.pale_compass");

    public PaleCompass(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return super.hasGlint(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world instanceof ServerWorld serverWorld){
            LodestoneTrackerComponent lodestoneTrackerComponent = stack.get(DataComponentTypes.LODESTONE_TRACKER);
            if (lodestoneTrackerComponent != null){
                LodestoneTrackerComponent lodestoneTrackerComponent1 = lodestoneTrackerComponent.forWorld(serverWorld);
                if (lodestoneTrackerComponent1 != lodestoneTrackerComponent) {
                    stack.set(DataComponentTypes.LODESTONE_TRACKER, lodestoneTrackerComponent1);
                }
            }
        }
    }

    @Override
    public Text getName(ItemStack stack) {
        return stack.contains(DataComponentTypes.LODESTONE_TRACKER) ? PALE_COMPASS : super.getName(stack);
    }
}
