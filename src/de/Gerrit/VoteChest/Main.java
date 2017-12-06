package de.Gerrit.VoteChest;

import de.Gerrit.VoteChest.Listener.ChestClickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
       registerEvents();

        File folder = new File(this.getDataFolder() + "/");
        if(!folder.exists())
            folder.mkdir();

        File jsonFile = new File(folder.getPath() + "/jsonFile.txt");
        if(!jsonFile.exists()) {

            try {

                jsonFile.createNewFile();
            } catch (IOException e) {

            }
        }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Alter", 18);
        jsonObject.put("Name", "Gerrit");


        JSONArray company = new JSONArray();
        company.add("Schule: Realschule Rhede");
        company.add("Schule: Montesorie");

        try (FileWriter file = new FileWriter(this.getDataFolder() + "/jsonFile.txt")) {
            file.write(jsonObject.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jsonObject);
        } catch (IOException e){}



    }
    private void registerEvents(){
        //Chest ClickListener Event
        Bukkit.getServer().getPluginManager().registerEvents(new ChestClickListener(), this);

    }
}
