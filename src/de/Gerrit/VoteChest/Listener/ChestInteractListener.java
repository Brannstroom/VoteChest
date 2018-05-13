package de.Gerrit.VoteChest.Listener;

import de.Gerrit.VoteChest.Components.VoteChestInventory;
import de.Gerrit.VoteChest.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ChestInteractListener extends SuperChestListener implements Listener {

    @EventHandler
    public void onPlayerChestClickEvent(PlayerInteractEvent chestClick) {

        if (((chestClick.getAction() == Action.LEFT_CLICK_BLOCK ? true : false) |
                (chestClick.getAction() == Action.RIGHT_CLICK_BLOCK ? true : false)) != false) {

            if (chestClick.getClickedBlock().getType() == Material.CHEST) {
                if (chestClick.getClickedBlock().getLocation().equals(Utils.getVoteChestLocationFromYaml())) {
                    chestClick.setCancelled(true);
                    if(isClickedItemAVaildVoteChestKey(chestClick.getPlayer())){


                        //Give Items to Player
                        ItemStack itemStackThePlayerWon = new ItemStack(Material.WOOD); // REMOVE
                        chestClick.getPlayer().getInventory().addItem(itemStackThePlayerWon);
                        chestClick.getPlayer().sendMessage(Utils.PREFIX + Utils.getPlugin().getConfig().
                            getString("msg.won_message").replace("<item>", itemStackThePlayerWon.getType().toString()));

                            chestClick.getPlayer().updateInventory();
                           chestClick.getPlayer().getInventory().removeItem(Utils.createVoteChestKey());

                           new VoteChestInventory(chestClick.getPlayer(), 2, 5);

                    } else {
                        chestClick.getPlayer().sendMessage(Utils.PREFIX + Utils.getPlugin().getConfig().
                                getString("msg.must_use_key"));
                    }
                    }
                }
            }
        }
    private boolean isClickedItemAVaildVoteChestKey(Player player){
        try {
            if(player.getInventory().getItemInMainHand().getType().equals(Material.TRIPWIRE_HOOK)){
                if(player.getInventory().getItemInMainHand().getItemMeta().getLore().
                        contains(Utils.getVoteKeyItemNameFromConfig())) {
                    return true;
                }
            }
        } catch (NullPointerException exception){return false;}
        return false;
    }
}
