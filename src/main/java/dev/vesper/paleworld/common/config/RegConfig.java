package dev.vesper.paleworld.common.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class RegConfig {
    public static void init(){
        AutoConfig.register(Config.class, JanksonConfigSerializer::new);
    }
}
