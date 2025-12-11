package dev.vesper.paleworld.common.properties;

import net.minecraft.world.food.FoodProperties;

public class PaleWorldFoodProperties {
	public static FoodProperties PALE_BERRIES = (new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build());
	public static FoodProperties PALE_APPLE = (new FoodProperties.Builder().nutrition(3).saturationModifier(5f).alwaysEdible().build());
}
