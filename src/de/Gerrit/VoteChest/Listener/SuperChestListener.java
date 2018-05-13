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

   /*
    protected ItemStack getPlayersWonItemSTack() {
            currentItemListSize = getItemListSize();

                return (new ItemStack(Material.getMaterial(Utils.getPlugin().getConfig().
                        getStringList("items").get(getItemIndexByChance()).split("/")[0].
                        replaceAll(" ", "").toUpperCase()), 1));

    }
    */



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


    protected int getArrayIndexByChance(int amount, ArrayList<Integer> percentageVariationArray){

        int[] percentage = new int[Utils.getItemListSize()];


        int currentNumber = 0;
        for(int i = 0; i < Utils.getItemListSize(); i ++){


            for(int c = 0; c < percentage[i]; c ++){
                percentageVariationArray.add(currentNumber);
            }
            currentNumber++;
        }

        Random random = new Random();
        return random.nextInt(amount);

        //percentageVariationArray.get(random.nextInt(amount));

    }
}
