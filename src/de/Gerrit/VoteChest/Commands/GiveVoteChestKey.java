package de.Gerrit.VoteChest.Commands;

import de.Gerrit.VoteChest.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GiveVoteChestKey {

    private String playername;

    public GiveVoteChestKey(String playername){
        this.playername = playername;

            giveVoteChestKeyToPlayer();

    }


    private void giveVoteChestKeyToPlayer() throws NullPointerException{
        Player playerWhoGetsTheVoteChestKey = Utils.getPlayerbyPlayername(playername);
        playerWhoGetsTheVoteChestKey.getInventory().addItem(Utils.createVoteChestKey());
        playerWhoGetsTheVoteChestKey.updateInventory();
        playerWhoGetsTheVoteChestKey.sendMessage(Utils.getPlugin().getConfig().getString(Utils.PREFIX + "msg.done"));

    }
}
