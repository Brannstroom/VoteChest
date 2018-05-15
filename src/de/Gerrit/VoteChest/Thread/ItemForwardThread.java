package de.Gerrit.VoteChest.Thread;

import de.Gerrit.VoteChest.Utils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class ItemForwardThread extends Thread implements Listener {

    int time;
    int timeValue = 50;
    Inventory votechestInventory;
    ArrayList<Integer> itemArray;
    ArrayList<Integer> itemArrayCopy;
    int startPoint;
    int endPoint;
    int positionToStop;
    boolean firstTime = true;
    int itemIndex = 0;
    int maxAmount;
    int diffToWonItem;
    Player playerWhoPressedTheVoteChest;

    public ItemForwardThread(Player playWhoPressedTheVoteChest, int maxAmount, int positionToStop, int time, Inventory voteChestInventory,
                             ArrayList<Integer> itemArray, int startPoint, int endPoint) {

        this.time = time;
        this.votechestInventory = voteChestInventory;
        this.itemArray = itemArray;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.positionToStop = positionToStop;
        this.maxAmount = maxAmount;
        this.playerWhoPressedTheVoteChest = playWhoPressedTheVoteChest;
        this.itemArrayCopy = new ArrayList<>(itemArray);

    }

    public void run(){

        if (!(Thread.currentThread().isInterrupted())) {
            //Add 6 Random Items to the End so that if the last item wins no IndexOutOfBoundException appears
            for (int i = 0; i < 6; i++) {
                Random random = new Random();
                itemArray.add(random.nextInt(Utils.getItemListSize()));
            }

            //add 3 Items Random to the beginning so that first item under the Hopper is the item with index 0
            for (int i = 0; i < 3; i++) {
                Random random = new Random();
                itemArray.add(i, random.nextInt(Utils.getItemListSize()));
            }

            displayItemsFirst(itemArray);

            for (int i = 0; i < positionToStop; i++) {
                ItemFastForwardTime(i);

                try {
                    Thread.sleep(timeValue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (firstTime) {
                    itemForward(itemIndex, itemArray, startPoint, endPoint);
                    firstTime = false;
                } else {
                    itemIndex++;
                    itemForward(itemIndex, itemArray, startPoint, endPoint);
                }


            }
            playerWhoPressedTheVoteChest.playSound(playerWhoPressedTheVoteChest.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            ItemStack itemStackThePlayerWon = new ItemStack(Material.getMaterial(Utils.getPlugin().getConfig().
                    getStringList("items").get(itemArrayCopy.get(positionToStop)).split("/")[0].
                    replaceAll(" ", "").toUpperCase()), 1);

            playerWhoPressedTheVoteChest.getInventory().addItem(itemStackThePlayerWon);


            playerWhoPressedTheVoteChest.sendMessage(Utils.PREFIX + Utils.getPlugin().getConfig().
                    getString("msg.won_message").replace("<item>", itemStackThePlayerWon.getType().toString()));

            playerWhoPressedTheVoteChest.updateInventory();

            InventoryCloseEvent.getHandlerList().unregister(this);

        }

    }

    private void itemForward(int itemIndex, ArrayList<Integer> ItemArray, int startPoint, int endpoint){
        for(int i = startPoint; i < endpoint; i++){

                votechestInventory.setItem(i, new ItemStack(Material.getMaterial(Utils.getPlugin().getConfig().
                        getStringList("items").get(ItemArray.get(itemIndex + 1)).split("/")[0].
                        replaceAll(" ", "").toUpperCase()), 1));

            playerWhoPressedTheVoteChest.playSound(playerWhoPressedTheVoteChest.getLocation(),
                    Sound.BLOCK_DISPENSER_DISPENSE, 10, 1);

            itemIndex ++;
        }
    }

    private void displayItemsFirst(ArrayList<Integer> possibleItemArray) {
        int counter = 0;
        for (int i = 10; i < 17; i++) {

            votechestInventory.setItem(i, new ItemStack(Material.getMaterial(Utils.getPlugin().getConfig().
                    getStringList("items").get(possibleItemArray.get(counter)).split("/")[0].
                    replaceAll(" ", "").toUpperCase()), 1));

            counter++;
        }
    }
    private void ItemFastForwardTime(int currentPosition){
        diffToWonItem = positionToStop - currentPosition;
        int percentage = ((diffToWonItem * 100) / positionToStop);

        if(percentage <=75 && percentage >= 15) {
            timeValue = 100;

        } else if(percentage <=15 && percentage >= 10){
            timeValue = 200;
        } else if(percentage <=10 && percentage >= 7){
            timeValue = 300;
        } else if(percentage <=7 && percentage >=5){
            timeValue = 700;
        } else if(percentage <=5 && percentage >=3){
            timeValue = 800;
        } else if(percentage <=3 && percentage >= 1) {
            timeValue = 1100;
        }
    }
}
