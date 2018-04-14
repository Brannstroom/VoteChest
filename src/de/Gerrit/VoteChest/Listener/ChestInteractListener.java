package de.Gerrit.VoteChest.Listener;

import de.Gerrit.VoteChest.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ChestInteractListener extends SuperChestListener implements Listener {

    @EventHandler
    public void onPlayerChestClickEvent(PlayerInteractEvent chestClick) {

        if (((chestClick.getAction() == Action.LEFT_CLICK_BLOCK ? true : false) |
                (chestClick.getAction() == Action.RIGHT_CLICK_BLOCK ? true : false)) != false) {

            if (chestClick.getClickedBlock().getType() == Material.CHEST) {
                if (chestClick.getClickedBlock().getLocation().equals(getVoteChestLocation())) {
                    chestClick.setCancelled(true);
                    if(isClickedItemAVaildVoteChestKey(chestClick.getPlayer())){
                    chestClick.getPlayer().sendMessage(Utils.PREFIX + "VoteChest gedrückt");

                    ArrayList<ItemStack> itemsToAddToInventory = getItemStackArrayList();
                    for (int i = 0; i < getItemListSize(); i++) {
                        chestClick.getPlayer().getInventory().addItem(itemsToAddToInventory.get(i));
                    }
                    chestClick.getPlayer().updateInventory();
                } else {
                        chestClick.getPlayer().sendMessage(Utils.PREFIX + "Du verwendest keinen gültigen Vote Schlüssel");
                    }
                }
            }
        }
    }
    private boolean isClickedItemAVaildVoteChestKey(Player player){
        try {
            if(player.getInventory().getItemInMainHand().getType().equals(Material.TRIPWIRE_HOOK)){
                if(player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("Vote Schlüssel")) {
                    return true;
                }
            }
        } catch (NullPointerException exception){return false;}
        return false;
    }
}
