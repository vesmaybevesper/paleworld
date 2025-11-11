package dev.vesper.paleworld;

import net.minecraft.server.level.ServerPlayer;

public class ExampleEventHandler {

    public static void onPlayerHurt(ServerPlayer player) {
        // MinecraftServer.pvp is private... only here to test ATs/AWs
        if (player.getServer().pvp) {
            PaleWorld.LOG.info("{} took damage. PVP is allowed.", player.getDisplayName());
        } else {
            PaleWorld.LOG.info("{} took damage. PVP is disallowed.", player.getDisplayName());
        }
    }

}
