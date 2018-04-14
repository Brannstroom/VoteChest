package de.Gerrit.VoteChest.Listener;

import de.Gerrit.VoteChest.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SuperChestListener {

    private ArrayList<ItemStack> itemStackArrayList = new ArrayList<>();
    private int currentItemListSize = 0;


    protected Location getVoteChestLocation() {
        double x = Utils.getPlugin().getConfig().getDouble("VoteChest.x");
        double y = Utils.getPlugin().getConfig().getDouble("VoteChest.y");
        double z = Utils.getPlugin().getConfig().getDouble("VoteChest.z");
        World w = Bukkit.getWorld(Utils.getPlugin().getConfig().getString("VoteChest.world"));

        return new Location(w, x, y, z);
    }

    public void resetVoteChestLocation() {
        saveChestLocationToYaml(0, 0, 0, "");
    }

    protected void saveChestLocationToYaml(double x, double y, double z, String world) {
        Utils.getPlugin().getConfig().set("VoteChest.x", Double.valueOf(x));
        Utils.getPlugin().getConfig().set("VoteChest.y", Double.valueOf(y));
        Utils.getPlugin().getConfig().set("VoteChest.z", Double.valueOf(z));
        Utils.getPlugin().getConfig().set("VoteChest.world", world);
        Utils.getPlugin().saveConfig();
    }

    protected ArrayList<ItemStack> getItemStackArrayList() {
        clearItemListIfUpdated();
        if (itemStackArrayList.isEmpty()) {
            currentItemListSize = Utils.getPlugin().getConfig().getStringList("items").size();

            for (int i = 0; i < currentItemListSize; i++) {

                itemStackArrayList.add(new ItemStack(Material.getMaterial(Utils.getPlugin().getConfig().
                        getStringList("items").get(i).toUpperCase()), 1));
            }
        } else {
            return itemStackArrayList;
        }
        return itemStackArrayList;
    }

    protected int getItemListSize() {
        return Utils.getPlugin().getConfig().getStringList("items").size();
    }

    private void clearItemListIfUpdated() {
        if (currentItemListSize == getItemListSize()) {
            if (!itemStackArrayList.isEmpty()) {
                for (int i = 0; i < getItemListSize(); i++) {
                    if (itemStackArrayList.get(i).getType().equals((new ItemStack(Material.getMaterial(Utils.getPlugin().getConfig().
                            getStringList("items").get(i).toUpperCase())).getType()))) {

                    } else {
                        itemStackArrayList.clear();
                        return;
                    }
                }
            }
        } else {
            itemStackArrayList.clear();
        }
        return;
    }
}
