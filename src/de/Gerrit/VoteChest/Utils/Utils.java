package Utils;

import de.Gerrit.VoteChest.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Utils {

    private static Main plugin;
    public static final String PREFIX = " §7[§3§LVoteChest§7]§f ";
    public static final String NOPERMISSIONS = PREFIX + "§c§LKeine Berechtigung";
    public static boolean voteChestCreationToggle = false;

    public Utils(Main plugin){
        this.plugin = plugin;
    }

    public static final Main getPlugin(){
        return plugin;
    }

    public static Location getVoteChestLocation(){
        double x = getPlugin().getConfig().getDouble("VoteChest.x");
        double y = getPlugin().getConfig().getDouble("VoteChest.y");
        double z = getPlugin().getConfig().getDouble("VoteChest.z");
        World w =  Bukkit.getWorld(getPlugin().getConfig().getString("VoteChest.world"));

        return new Location(w, x, y, z);
    }

    public static void resetVoteChestLocation(){
        saveChestLocationToYaml(0,0,0, "");
    }

    public static void saveChestLocationToYaml(double x, double y, double z, String world){
        getPlugin().getConfig().set("VoteChest.x", Double.valueOf(x));
        getPlugin().getConfig().set("VoteChest.y", Double.valueOf(y));
        getPlugin().getConfig().set("VoteChest.z", Double.valueOf(z));
        getPlugin().getConfig().set("VoteChest.world", world);
        getPlugin().saveConfig();
    }
}
