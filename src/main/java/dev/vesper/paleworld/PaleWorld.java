package dev.vesper.paleworld;

import dev.vesper.paleworld.common.config.RegConfig;
import me.shedaniel.autoconfig.AutoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaleWorld {

    public static final String MOD_ID = "paleworld";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        LOG.info("Initializing {} on {}", MOD_ID, Platform.INSTANCE.loader());
    }
}
