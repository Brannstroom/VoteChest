package de.Gerrit.VoteChest.Commands;

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

    private ItemStack createVoteChestKey(){
        ItemStack voteChestKey = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta meta = voteChestKey.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Vote Schlüssel");
        meta.setLore(lore);
        voteChestKey.setItemMeta(meta);
        return voteChestKey;
    }

    private void giveVoteChestKeyToPlayer() throws NullPointerException{
        Player playerWhoGetsTheVoteChestKey = getPlayerbyPlayername(playername);
        playerWhoGetsTheVoteChestKey.getInventory().addItem(createVoteChestKey());
        playerWhoGetsTheVoteChestKey.updateInventory();
    }

    private Player getPlayerbyPlayername(String playername) {
       return Bukkit.getPlayer(playername);
    }
}
