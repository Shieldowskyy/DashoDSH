package com.dasho;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.logging.Level;

public final class DashoDSH extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("OnEnableInvoked");
        getServer().getPluginManager().registerEvents(this, this);

        FileConfiguration config = this.getConfig();
        config.addDefault("blokowacPaczki", false);
        config.addDefault("wybuchPoZniszczeniuBloku", false);
        config.addDefault("silaWybuchuPoZniszczeniuBloku", Float.valueOf(1));
        config.options().copyDefaults(true);
        saveConfig();

    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        getLogger().log(Level.INFO, "Player " + event.getPlayer().getName() + " is logging in!");

        Player gracz = event.getPlayer();

        gracz.sendMessage("Witaj na serwerze DSHcraft");
        var latanie = gracz.getAllowFlight();
        getLogger().log(Level.INFO, "Gracz " + event.getPlayer().getName() + " ma latanie ustawione na " + latanie);

        FileConfiguration config = this.getConfig();

        if (config.getBoolean("blokowacPaczki")) {

            if (gracz.hasResourcePack()) {
                gracz.kickPlayer("Paczki nie są dozwolone na tym serwerze");
            }

        }

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public boolean onCommand(@NotNull CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("latanie")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
            // doSomething
            Player gracz = (Player) sender;

            if (gracz.getAllowFlight() == true) {
                String moznalatac = ("Tak");
                sender.sendMessage("Twoje latanie jest ustawione na " + moznalatac);
            } else if (gracz.getAllowFlight() == false) {
                String moznalatac = ("Nie");
                sender.sendMessage("Twoje latanie jest ustawione na " + moznalatac);

            }
            return true;
        }
// jakiping
        if (cmd.getName().equalsIgnoreCase("jakiping")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
            // doSomething
            Player gracz = (Player) sender;

            sender.sendMessage("Twój obecny ping to: " + ChatColor.RED + "" + ChatColor.BOLD + gracz.getPing() + "ms");

            return true;

        }
// setlatanie
        if (cmd.getName().equalsIgnoreCase("setlatanie")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
            // doSomething
            Player gracz = (Player) sender;

            float speed = Float.valueOf(Integer.parseInt(args[0]));

            if (speed > 1) {
                sender.sendMessage("Prędkość lotu jest zbyt duża! Max: 1");

            }
            if (args[0] != null) {
                gracz.setFlySpeed(speed);
                sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Ustawiono prędkość lotu gracza na: " + args);

            }
            if (args[0] == null)
                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Błędnie wpsiana komenda! Nie zrozumiano argumentu: " + args);


        }
// mobek
        if (cmd.getName().equalsIgnoreCase("mobek")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
            // doSomething
            Player gracz = (Player) sender;

            var swiat = gracz.getWorld();
            var lokacja = gracz.getLocation();

            String ent = args[0].toUpperCase();

            swiat.spawnEntity(lokacja, EntityType.valueOf(ent));

            sender.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Zespawnowano byt: " + ent);


            return true;
        }
// eksploduj
        if (cmd.getName().equalsIgnoreCase("eksploduj")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
            // doSomething
            Player gracz = (Player) sender;

            var swiat = gracz.getWorld();
            var lokacja = gracz.getLocation();
            float sila = Float.valueOf(Integer.parseInt(args[0]));

            swiat.createExplosion(lokacja, sila);

            sender.sendMessage(ChatColor.RED + "" + "Stworzono eksplozję o sile: " + ChatColor.BOLD + sila);


            return true;
        }
// dzien
        if (cmd.getName().equalsIgnoreCase("dzien")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
            // doSomething
            Player gracz = (Player) sender;

            var swiat = gracz.getWorld();

            swiat.setTime(3000);
            sender.sendMessage(ChatColor.AQUA + "" + "Ustawiono czas na Dzień!");

            return true;
        }
// noc
        if (cmd.getName().equalsIgnoreCase("noc")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
            // doSomething
            Player gracz = (Player) sender;

            var swiat = gracz.getWorld();

            swiat.setTime(13800);
            sender.sendMessage(ChatColor.AQUA + "" + "Ustawiono czas na Noc!");

            return true;
        }
// setpvp
        if (cmd.getName().equalsIgnoreCase("setpvp")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
            // doSomething
            Player gracz = (Player) sender;

            var swiat = gracz.getWorld();

            boolean co = Boolean.parseBoolean(args[0]);

            swiat.setPVP(co);

            swiat.setPVP(co);

            if (Objects.equals(args[0], "true")) {
                swiat.setPVP(true);
                sender.sendMessage(ChatColor.AQUA + "" + "Pvp zostało wyłączone");
                return true;
            }
            if (Objects.equals(args[0], "false")) {
                swiat.setPVP(false);
                sender.sendMessage(ChatColor.AQUA + "" + "Pvp zostało włączone");
                return true;
            } else {
                sender.sendMessage(ChatColor.AQUA + "" + "Nieobsługiwany argument: " + args + ". Obsługiwane argumenty: True, False.");
                return false;
            }


        }

        if (cmd.getName().equalsIgnoreCase("piorun")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
            // doSomething
            Player gracz = (Player) sender;

            var swiat = gracz.getWorld();


            var blok = gracz.getTargetBlock(10);

            var lokablok = blok.getLocation();

            // var lokacja = Bukkit.getPlayer(args[0]).getLocation();
            // TO DO: Jak jest argument to jebnij w określonego gracza

            swiat.strikeLightning(lokablok);
            sender.sendMessage(ChatColor.AQUA + "" + "Zeus się dobrze bawi chyba...");

            return true;
        }

        if (cmd.getName().equalsIgnoreCase("przeladuj")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
            // doSomething

            sender.sendMessage(ChatColor.AQUA + "" + "Aby przeładować config plugina DashoDSH musisz zrobić restart serwera! Jest to wymagane do prawidłowego działania plugina.");

            return true;
        }

        if (cmd.getName().equalsIgnoreCase("paczka")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
            // doSomething
            Player gracz = (Player) sender;

            Player osoba = Bukkit.getPlayer(args[0]);

            sender.sendMessage(ChatColor.AQUA + "" + "Status paczki zasobów gracza " + args[0] + " = " + osoba.hasResourcePack());

            if (gracz.hasResourcePack()) {
                gracz.kickPlayer("Paczki nie są dozwolone na tym serwerze");
            }

            return true;
        }

        return true;

    }

    @EventHandler
    public void jakZniszczyBlok(BlockBreakEvent event) {

        FileConfiguration config = this.getConfig();

        Float sila = Float.valueOf((1));

        sila = (Float) config.get("silaWybuchuPoZniszczeniuBloku");
        if (config.getBoolean("wybuchPoZniszczeniuBloku")) {
            Player gracz = event.getPlayer();
            gracz.getLocation().createExplosion(sila);


        }
    }
}

