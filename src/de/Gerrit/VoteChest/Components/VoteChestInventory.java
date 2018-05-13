package de.Gerrit.VoteChest.Components;

import de.Gerrit.VoteChest.Thread.ItemForwardThread;
import de.Gerrit.VoteChest.Listener.SuperChestListener;
import de.Gerrit.VoteChest.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;

public class VoteChestInventory extends SuperChestListener {
    private Player player;
    private Inventory voteChestInventory;
    private ItemStack outerVoteChestInventoryItemStack;
    private ItemStack winSelecterItemStack;



    public VoteChestInventory(Player playerWhoClickedTheVoteChest) {
        this.player = playerWhoClickedTheVoteChest;


        createOuterVoteChestInventoryItemStack();
        createVoteChestInventory();
        displayInventory();

        rolling();
    }

    private void createVoteChestInventory() {
        voteChestInventory = Bukkit.createInventory(player, 27, Utils.PREFIX);

        setOuterVoteChestItemStack();
    }

    private void displayInventory() {
        player.openInventory(voteChestInventory);
    }

    private void createOuterVoteChestInventoryItemStack() {
        outerVoteChestInventoryItemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemMeta meta = outerVoteChestInventoryItemStack.getItemMeta();
        meta.setDisplayName(" ");
        meta.setLore(null);
        outerVoteChestInventoryItemStack.setItemMeta(meta);
    }

    private void createWinSelectorItemStack() {
        winSelecterItemStack = new ItemStack(Material.HOPPER, 1);
        ItemMeta meta = winSelecterItemStack.getItemMeta();
        meta.setDisplayName("§7Your price");
        meta.setLore(null);
        winSelecterItemStack.setItemMeta(meta);
    }

    private void setOuterVoteChestItemStack() {
        createWinSelectorItemStack();
        for (int i = 0; i < 10; i++) {
            voteChestInventory.setItem(i, outerVoteChestInventoryItemStack);
            if (i == 4) {
                voteChestInventory.setItem(i, winSelecterItemStack);
            }
        }

        for (int i = 17; i < 27; i++) {
            voteChestInventory.setItem(i, outerVoteChestInventoryItemStack);
        }
    }

    //11 - 16

    private void rolling() {
        int amount = 0;
        int[] percentage = new int[Utils.getItemListSize()];

        for (int i = 0; i < Utils.getItemListSize(); i++) {
            int numberToGetFromConfig = Integer.valueOf(Utils.getPlugin().getConfig().getStringList("items").
                    get(i).split("/")[1].replaceAll(" ", ""));

            percentage[i] = numberToGetFromConfig;
            amount += numberToGetFromConfig;
        }

        ArrayList<Integer> percentageVariationArray = new ArrayList();

        int currentNumber = 0;
        for (int i = 0; i < Utils.getItemListSize(); i++) {

            for (int c = 0; c < percentage[i]; c++) {
                percentageVariationArray.add(currentNumber);
            }
            currentNumber++;
        }

        Collections.shuffle(percentageVariationArray);


        ItemForwardThread thread = new ItemForwardThread(player, amount, getArrayIndexByChance(amount, percentageVariationArray),
                5, voteChestInventory,
                percentageVariationArray, 10, 17);

        thread.start();


    }
}
