package de.Gerrit.VoteChest.Utils;

import de.Gerrit.VoteChest.Main;

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
<<<<<<< Updated upstream:src/Utils/Utils.java

    public static void saveChestLocationToPluginYml(Player p, Block placedBlock){
        if (placedBlock.getType() == Material.CHEST) {
            if (p.isOp() || p.hasPermission("VoteChest.Admin")) {
                saveLocationToYaml(placedBlock.getX(), placedBlock.getY(), placedBlock.getZ(), p.getPlayer().getWorld().getName());
            }
        }
    }

    public static Location getVoteChestLocation(){
        double x = getPlugin().getConfig().getDouble("VoteChest.x");
        double y = getPlugin().getConfig().getDouble("VoteChest.y");
        double z = getPlugin().getConfig().getDouble("VoteChest.z");
        World w =  Bukkit.getWorld(getPlugin().getConfig().getString("VoteChest.world"));

        return new Location(w, x, y, z);
    }

    public static void resetVoteChestLocation(){
        saveLocationToYaml(0,0,0, "");
    }

    private static void saveLocationToYaml(double x, double y, double z, String world){
        getPlugin().getConfig().set("VoteChest.x", Double.valueOf(x));
        getPlugin().getConfig().set("VoteChest.y", Double.valueOf(y));
        getPlugin().getConfig().set("VoteChest.z", Double.valueOf(z));
        getPlugin().getConfig().set("VoteChest.world", world);
        getPlugin().saveConfig();
    }
}
=======
}
>>>>>>> Stashed changes:src/de/Gerrit/VoteChest/Utils/Utils.java
