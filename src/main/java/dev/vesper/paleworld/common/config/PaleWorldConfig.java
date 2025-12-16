package dev.vesper.paleworld.common.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.autogen.FloatField;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;

public class PaleWorldConfig {
	public static ConfigClassHandler<PaleWorldConfig> CONFIG = ConfigClassHandler.createBuilder(PaleWorldConfig.class)
			.id(ResourceLocation.fromNamespaceAndPath("paleworld", "config"))
			.serializer(config -> GsonConfigSerializerBuilder.create(config)
					.setPath(YACLPlatform.getConfigDir().resolve("paleworld.json5"))
					.setJson5(true)
					.build())
			.build();

	public static Screen config(Screen parent){
		return CONFIG.generateGui().generateScreen(parent);
	}

	@AutoGen(category = "Visual")
	@FloatField
	@SerialEntry
	public static float fogStart = 0.5F;
	@AutoGen(category = "Visual")
	@FloatField
	@SerialEntry
	public static float fogEnd = 20F;
	@AutoGen(category = "Visual")
	@FloatField
	@SerialEntry
	public static float fogTransparency = 0.7F;
	@AutoGen(category = "Visual")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean horrorMode = false;
}
