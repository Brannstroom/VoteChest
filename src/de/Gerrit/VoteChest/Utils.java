package de.Gerrit.VoteChest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class Utils {

    private static Main plugin;

    public static String PREFIX;
    public static  String NOPERMISSIONS;

    public Utils(Main plugin){
        this.plugin = plugin;
    }

    public static final Main getPlugin(){
        return plugin;
    }

    public static void saveChestLocationToPluginYml(Player p, Block placedBlock){
        if (placedBlock.getType() == Material.CHEST) {
            if (p.isOp() || p.hasPermission("VoteChest.Admin")) {
                saveVoteChestLocationToYaml(placedBlock.getX(), placedBlock.getY(), placedBlock.getZ(),
                        p.getPlayer().getWorld().getName());
            }
        }
    }

    public static Location getVoteChestLocationFromYaml(){
        double x = getPlugin().getConfig().getDouble("VoteChest.x");
        double y = getPlugin().getConfig().getDouble("VoteChest.y");
        double z = getPlugin().getConfig().getDouble("VoteChest.z");
        World w =  Bukkit.getWorld(getPlugin().getConfig().getString("VoteChest.world"));

        return new Location(w, x, y, z);
    }

    public static void saveVoteChestLocationToYaml(double x, double y, double z, String world){
        getPlugin().getConfig().set("VoteChest.x", Double.valueOf(x));
        getPlugin().getConfig().set("VoteChest.y", Double.valueOf(y));
        getPlugin().getConfig().set("VoteChest.z", Double.valueOf(z));
        getPlugin().getConfig().set("VoteChest.world", world);
        getPlugin().saveConfig();

    }

    public static  Player getPlayerbyPlayername(String playername) {
        return Bukkit.getPlayer(playername);
    }

    public static String getVoteKeyItemNameFromConfig(){
        return Utils.getPlugin().getConfig().getString("itemname");
    }

    public static ItemStack createVoteChestKey(){
        ItemStack voteChestKey = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta meta = voteChestKey.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(getVoteKeyItemNameFromConfig());
        meta.setLore(lore);
        voteChestKey.setItemMeta(meta);
        voteChestKey.setAmount(1);
        return voteChestKey;
    }

    /**
     * Method to get the Amount of Items in the Config List
     * @return the Amount of Items contained in the Config
     */
    public static int getItemListSize() {
        return Utils.getPlugin().getConfig().getStringList("items").size();
    }
}

