package de.Gerrit.VoteChest.Listener;

import de.Gerrit.VoteChest.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SuperChestListener {

    public static boolean voteChestCreationToggle = false;
    private int currentItemListSize = 0;


    public static void resetVoteChestLocation() {
        Utils.saveVoteChestLocationToYaml(0, 0, 0, "");
    }

    /**
     * Method to return the items that the player gets when he pressed the VoteChest
     * @return ArrayList of ItemsStacks Containing the items that will be given to the Player
     */
    protected ItemStack getPlayersWonItemSTack() {
            currentItemListSize = getItemListSize();

                return (new ItemStack(Material.getMaterial(Utils.getPlugin().getConfig().
                        getStringList("items").get(getItemIndexByChance()).split("/")[0].
                        replaceAll(" ", "").toUpperCase()), 1));

    }

    /**
     * Method to get the Amount of Items in the Config List
     * @return the Amount of Items contained in the Config
     */
    protected int getItemListSize() {
        return Utils.getPlugin().getConfig().getStringList("items").size();
    }

    /*

    private void clearItemListIfUpdated() {
        if (currentItemListSize == getItemListSize()) {
            if (!itemStackArrayList.isEmpty()) {
                for (int i = 0; i < getItemListSize(); i++) {
                    if (itemStackArrayList.get(i).getType().equals((new ItemStack(Material.getMaterial(Utils.getPlugin().getConfig().
                            getStringList("items").get(i).split("/")[0].toUpperCase())).getType()))) {

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
    */


    private int getItemIndexByChance(){

        int amount = 0;
        int[] percentage = new int[getItemListSize()];

        for(int i = 0; i < getItemListSize(); i++){
            int numberToGetFromConfig = Integer.valueOf(Utils.getPlugin().getConfig().getStringList("items").
                    get(i).split("/")[1].replaceAll(" ", ""));

            percentage[i] = numberToGetFromConfig;
            amount += numberToGetFromConfig;
        }

        ArrayList<Integer> percentageVariationArray = new ArrayList();

        int currentNumber = 0;
        for(int i = 0; i < getItemListSize(); i ++){


            for(int c = 0; c < percentage[i]; c ++){
                percentageVariationArray.add(currentNumber);
            }
            currentNumber++;
        }

        Random random = new Random();
        return percentageVariationArray.get(random.nextInt(amount));

    }
}
