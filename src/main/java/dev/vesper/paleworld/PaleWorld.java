package dev.vesper.paleworld;

import dev.vesper.paleworld.platform.Platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import dev.vesper.paleworld.platform.fabric.FabricPlatform;
//?} neoforge {
/*import com.example.modtemplate.platform.neoforge.NeoforgePlatform;
 *///?}

@SuppressWarnings("LoggingSimilarMessage")
public class PaleWorld {

	public static final String MOD_ID = /*$ mod_id*/ "paleworld";
	public static final String MOD_VERSION = /*$ mod_version*/ "2.1.2";
	public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/ "Pale World";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	public static void onInitialize() {
		LOGGER.info("Initializing {} on {}", MOD_ID, PaleWorld.xplat().loader());
	}

	public static void onInitializeClient() {
		LOGGER.info("Initializing {} Client on {}", MOD_ID, PaleWorld.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
	}

	static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		 *///?}
	}
}
