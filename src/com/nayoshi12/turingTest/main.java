package com.nayoshi12.turingTest;

import com.nayoshi12.turingTest.events.chatStuff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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
            }
            catch(IOException ex){

            }
        }

            try{
                configConfig.load(configFile);
            }
            catch(Exception ex){

            }

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
        //EDIT THESE SHIT
        if (configConfig.get("potato.name:",))
        configConfig.set("potato.name:",);

        String message = e.getMessage();
        Player player = e.getPlayer();
        long bleh = System.currentTimeMillis() + 20;

        String[] checker = {"hi","hello", "potato", "wassup?"};
        String[] responses = {"hello","hi", "eww", "nm, hbu?"};

        for (int i = 0; i < checker.length; i++){
            String namerino = format(getConfig().get("chat").toString()) + format(getConfig().get("name").toString());
            if (message.contains("hi") && message.contains("how") && message.contains("you")){
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        getServer().broadcastMessage("<jean1243> I'm doing good, hbu?");

                    }
                }, 20);
                break;
            }
            if (message.contains("how") && message.contains("you")){
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        getServer().broadcastMessage("<jean1243> I'm fine, how are you?");

                    }
                }, 20);
                break;
            }
            if (message.contains("what") && message.contains("up")){
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        getServer().broadcastMessage(" not much how about you?");

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
                        getServer().broadcastMessage("<jean1243> " + responses[asd]);

                    }
            }, 20);
            break;
        }

        }



    }

    public static String format(String string) {
        String s = string;
        for (ChatColor color : ChatColor.values()) {
            s = s.replaceAll("(?i)<" + color.name() + ">", "" + color);
        }
        return s;
    }
}
