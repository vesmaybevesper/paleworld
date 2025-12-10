package dev.vesper.paleworld.platform.fabric;

//? fabric {

import dev.vesper.paleworld.PaleWorld;
import net.fabricmc.api.ClientModInitializer;

public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		PaleWorld.onInitializeClient();
	}

}
//?}
