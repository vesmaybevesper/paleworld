package dev.vesper.paleworld.platform.fabric;

//? fabric {

import dev.vesper.paleworld.PaleWorld;
import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		PaleWorld.onInitialize();
		FabricEventSubscriber.registerEvents();
	}
}
//?}
