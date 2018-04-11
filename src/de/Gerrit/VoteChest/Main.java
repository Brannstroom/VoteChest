package de.Gerrit.VoteChest;


import Utils.Utils;
import de.Gerrit.VoteChest.Commands.MainCommand;
import de.Gerrit.VoteChest.Listener.ChestListener;
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
       Utils utils = new Utils(this);
       registerEvents();
       registerCommands();
       loadConfig();

    }
    private void registerEvents(){
        //Chest ClickListener Event
        Bukkit.getServer().getPluginManager().registerEvents(new ChestListener(), this);

    }
    private void registerCommands(){
        MainCommand mainCommand = new MainCommand();
        getCommand("VoteChest").setExecutor(mainCommand);
    }
    private void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
