package de.Gerrit.VoteChest;


import de.Gerrit.VoteChest.Utils.Utils;
import de.Gerrit.VoteChest.Commands.MainCommand;
import de.Gerrit.VoteChest.Listener.ChestBlockPlaceListener;
import de.Gerrit.VoteChest.Listener.ChestInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by gerritharmeling on 06.12.17.
 */

public class Main extends JavaPlugin {

    public void onEnable(){
        System.out.print("VoteChest Plugin aktiviert");
        init();
    }

    public void onDisable(){
        System.out.print("Plugin deaktiviert");

    }
    private void init(){
       new Utils(this);
       registerEvents();
       registerCommands();
       loadConfig();

    }
    private void registerEvents(){
        //Chest ClickListener Event
        Bukkit.getServer().getPluginManager().registerEvents(new ChestBlockPlaceListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ChestInteractListener(), this);

    }
    private void registerCommands(){
        MainCommand mainCommand = new MainCommand();
        getCommand("VoteChest").setExecutor(mainCommand);
    }
    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
