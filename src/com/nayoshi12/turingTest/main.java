package com.nayoshi12.turingTest;

import com.nayoshi12.turingTest.events.chatStuff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Matthew on 8/31/2016.
 */
public class main extends JavaPlugin implements Listener {
    public static File configFolder;
    public static File configFile;

    public static FileConfiguration configConfig;
    @Override
    public void onEnable() {
        getLogger().info("hey");
        registerConfigs();
        registerEvents();
    }


    @Override
    public void onDisable() {
        getLogger().info("hi");
    }

    private void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this,this);
        //pm.registerEvents(new chatStuff(),this);

    }

    @EventHandler
    public void chatAI(AsyncPlayerChatEvent e) {
       String namerino = format(configConfig.getString("Turing.main.chat")) + format(configConfig.getString("Turing.main.name"));

        String message = e.getMessage();
        Player player = e.getPlayer();
        long bleh = System.currentTimeMillis() + 20;

        String[] checker = {"hi","hello", "potato", "wassup?"};
        String[] responses = {"hello","hi", "eww", "nm, hbu?"};

        for (int i = 0; i < checker.length; i++){


            if (message.contains("hi") && message.contains("how") && message.contains("you")){
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        getServer().broadcastMessage(namerino + " I'm doing good, hbu?");

                    }
                }, 20);
                break;
            }
            if (message.contains("how") && message.contains("you")){
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        getServer().broadcastMessage(namerino + " I'm fine, how are you?");

                    }
                }, 20);
                break;
            }
            if (message.contains("what") && message.contains("up")){
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        getServer().broadcastMessage(namerino +" not much how about you?");

                    }
                }, 20);
                break;
            }
            if (message.contains("good") || message.contains("fine")){
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        getServer().broadcastMessage(namerino + "ayy, that's pretty good!");
                    }
                }, 20);
                break;
            }
            final int asd = i;
            if (message.equals(checker[i])) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        getServer().broadcastMessage( namerino + " "  + responses[asd]);

                    }
            }, 20);
            break;
        }

        }



    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String namerino = format(configConfig.getString("Turing.main.chat")) + format(configConfig.getString("Turing.main.name"));
        if(command.getName().equalsIgnoreCase("chatm")){
            String derp = Arrays.toString(args).replace("[","").replace("]","").replace(",","");

            Bukkit.broadcastMessage(namerino + " " + derp );
        }
        return true;
    }

    public static String format(String string) {
        String s = string;
        for (ChatColor color : ChatColor.values()) {
            s = s.replaceAll("(?i)<" + color.name() + ">", "" + color);
        }
        return s;
    }
    public void registerConfigs(){
        configFolder = getDataFolder();
        configFile = new File(configFolder, "config.yml");

        configConfig = new YamlConfiguration();

        if (configFolder.exists() == false) {
            try {
                configFolder.mkdir();

            }
            catch (Exception ex){

            }
        }

        if(configFile.exists() == false){

            try{
                configFile.createNewFile();
                configConfig.set("Turing.main.chat","<dark_gray>[<dark_aqua>C-Mod<dark_gray>]<dark_aqua>");
                configConfig.set("Turing.main.name","MarshallMathers <reset><light_purple><magic>asd<reset> <dark_aqua>");
                configConfig.save(configFile);
            }
            catch(IOException ex){

            }
        }

        try{
            configConfig.load(configFile);
        }
        catch(Exception ex){

        }


    }
}
