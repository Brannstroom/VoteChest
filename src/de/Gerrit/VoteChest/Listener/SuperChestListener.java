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

    public static void resetVoteChestLocation() {
        Utils.saveVoteChestLocationToYaml(0, 0, 0, "");
    }

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
    }
}
