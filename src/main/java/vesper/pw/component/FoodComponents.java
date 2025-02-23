package vesper.pw.component;

import net.minecraft.component.type.FoodComponent;

public class FoodComponents {

    public static FoodComponent PALE_BERRIES = (new FoodComponent.Builder().nutrition(2).saturationModifier(0.1F).build());
    public static FoodComponent PALE_APPLE = (new FoodComponent.Builder().nutrition(3).saturationModifier(5F).alwaysEdible().build());
}
