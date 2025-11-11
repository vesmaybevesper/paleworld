package dev.vesper.paleworld.fabric;

//? fabric {
import dev.vesper.paleworld.PaleWorld;
import dev.vesper.paleworld.common.config.RegConfig;
import net.fabricmc.api.ClientModInitializer;

public class FabricClientEntrypoint implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        PaleWorld.LOG.info("Initializing {} Client", PaleWorld.MOD_ID);
    }

}
//?}