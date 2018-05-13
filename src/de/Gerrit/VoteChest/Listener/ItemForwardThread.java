package de.Gerrit.VoteChest.Listener;

import de.Gerrit.VoteChest.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class ItemForwardThread extends Thread{

    int time;
    int timeValue;// Milliseconds
    Inventory votechestInventory;
    ArrayList<Integer> itemArray;
    int startPoint;
    int endPoint;
    int positionToStop;
    boolean firstTime = true;
    int itemIndex = 0;
    int maxAmount;
    boolean running = true;

    public ItemForwardThread(int maxAmount, int positionToStop, int time, int timeValue, Inventory voteChestInventory, ArrayList<Integer> itemArray, int startPoint, int endPoint){
        this.time = time;
        this.timeValue = timeValue;
        this.votechestInventory = voteChestInventory;
        this.itemArray = itemArray;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.positionToStop = positionToStop;
        this.maxAmount = maxAmount;

    }

    public void run(){

        for(int i = 0; i < 6; i++){
            Random random = new Random();
            itemArray.add(random.nextInt(Utils.getItemListSize()));
        }

        for(int i = 0; i < 3; i++){
            Random random = new Random();
            itemArray.add(i, random.nextInt(Utils.getItemListSize()));
        }

        displayItemsFirst(itemArray);

        positionToStop = 69;

        System.out.print("ITEM DAS GEWONNEN WURDE" + positionToStop);

        for(int i = 0; i < positionToStop ; i++){

            try {
                Thread.sleep(timeValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(firstTime){
                itemForward(itemIndex, itemArray, startPoint, endPoint);
                firstTime = false;
            } else {
                itemIndex ++;
                itemForward(itemIndex, itemArray, startPoint, endPoint);
            }
            }

        }
        /*

        while(running == true){
            maxAmount --;

            try {
                Thread.sleep(timeValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(firstTime){
                itemForward(itemIndex, itemArray, startPoint, endPoint);
                firstTime = false;
            } else {
                itemIndex ++;
                itemForward(itemIndex, itemArray, startPoint, endPoint);
            }

            if(maxAmount <= 1){
                running = false;
            }

        }

    }
       */
    private void itemForward(int itemIndex, ArrayList<Integer> ItemArray, int startPoint, int endpoint){
        for(int i = startPoint; i < endpoint; i++){

                votechestInventory.setItem(i, new ItemStack(Material.getMaterial(Utils.getPlugin().getConfig().
                        getStringList("items").get(ItemArray.get(itemIndex + 1)).split("/")[0].
                        replaceAll(" ", "").toUpperCase()), 1));

            itemIndex ++;
        }
    }

    private void displayItemsFirst(ArrayList<Integer> possibleItemArray) {
        int counter = 0;
        for (int i = 10; i < 17; i++) {

            votechestInventory.setItem(i, new ItemStack(Material.getMaterial(Utils.getPlugin().getConfig().
                    getStringList("items").get(possibleItemArray.get(counter)).split("/")[0].
                    replaceAll(" ", "").toUpperCase()), 1));

            counter++; // Counter counts to 6 with 0 so to 7
        }

    }
}
