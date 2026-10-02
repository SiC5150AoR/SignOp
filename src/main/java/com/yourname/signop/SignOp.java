package com.yourname.signop;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SignOp extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onSignClose(SignChangeEvent event) {
        Player player = event.getPlayer();

        if (!player.isOp()) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            Component lineComponent = event.line(i);
            if (lineComponent == null) continue;

            String lineText = PlainTextComponentSerializer.plainText().serialize(lineComponent).trim();

            if (lineText.startsWith("/")) {
                String commandToRun = lineText.substring(1);
                if (!commandToRun.isEmpty()) {
                    Bukkit.dispatchCommand(player, commandToRun);
                }
            }
        }
    }
}
