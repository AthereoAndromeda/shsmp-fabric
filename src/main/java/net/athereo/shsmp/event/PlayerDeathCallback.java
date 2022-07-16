package net.athereo.shsmp.event;

import net.athereo.shsmp.Main;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;

public interface PlayerDeathCallback {
    Event<PlayerDeathCallback> EVENT = EventFactory.createArrayBacked(PlayerDeathCallback.class,
            (listeners) -> (player, source) -> {
                for (PlayerDeathCallback listener : listeners) {
                    Main.LOGGER.info("aha");
                    listener.kill(player, source);
                }
            });

    void kill(ServerPlayerEntity player, DamageSource source);
}
