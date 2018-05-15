package de.Gerrit.VoteChest;


import de.Gerrit.VoteChest.Commands.MainCommand;
import de.Gerrit.VoteChest.Listener.ChestBlockPlaceListener;
import de.Gerrit.VoteChest.Listener.ChestInteractListener;
import de.Gerrit.VoteChest.Listener.ChestInventoryCancelPlayerItemPickupListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by gerritharmeling on 06.12.17.
 */

public class Main extends JavaPlugin {

    public void onEnable(){
        System.out.print("VoteChest Plugin enabled");
        init();
    }

    public void onDisable(){
        System.out.print("Plugin disabled");

    }
    private void init(){
        loadConfig();
        new Utils(this);
       registerEvents();
       registerCommands();
        setUtilsMessages();

    }
    private void registerEvents(){
        //Chest ClickListener Event
        Bukkit.getServer().getPluginManager().registerEvents(new ChestBlockPlaceListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ChestInteractListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ChestInventoryCancelPlayerItemPickupListener(), this);

    }
    private void registerCommands(){
        MainCommand mainCommand = new MainCommand();
        getCommand("VoteChest").setExecutor(mainCommand);
    }
    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    private void setUtilsMessages(){
        Utils.PREFIX = Utils.getPlugin().getConfig().getString("msg.prefix");
        Utils.NOPERMISSIONS = Utils.PREFIX + Utils.getPlugin().getConfig().getString("msg.no_permission");
    }
}
