package dev.vesper.paleworld.platform.fabric;

//? fabric{
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.vesper.paleworld.common.config.PaleWorldConfig;

public class ModMenuInit implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return PaleWorldConfig::config;
	}
}
//?}
