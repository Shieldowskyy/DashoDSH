package com.dasho;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class DashoDSH1 extends JavaPlugin implements Listener {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("jakiping")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
            // doSomething
            Player gracz = (Player) sender;

            sender.sendMessage("Twój obecny ping to: " + ChatColor.RED + "" + ChatColor.BOLD + gracz.getPing() + "ms");

        }
        return true;
    }

}