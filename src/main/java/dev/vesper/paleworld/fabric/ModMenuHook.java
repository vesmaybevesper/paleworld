package dev.vesper.paleworld.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.vesper.paleworld.common.config.Config;
import me.shedaniel.autoconfig.AutoConfig;

public class ModMenuHook implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(Config.class, parent).get();
    }
}
