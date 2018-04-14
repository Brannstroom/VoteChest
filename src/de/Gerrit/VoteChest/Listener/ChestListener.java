package de.Gerrit.VoteChest.Listener;

import Utils.Utils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChestListener implements Listener{

    @EventHandler
    public void onPlayerChestClickEvent(PlayerInteractEvent chestClick){
            if (((chestClick.getAction() == Action.LEFT_CLICK_BLOCK ? true : false) |
                    (chestClick.getAction() == Action.RIGHT_CLICK_BLOCK ? true : false)) != false){

                if(chestClick.getClickedBlock().getType() == Material.CHEST){
                    if(chestClick.getClickedBlock().getLocation().equals(Utils.getVoteChestLocation())){
                        chestClick.setCancelled(true);
                        chestClick.getPlayer().sendMessage(Utils.PREFIX + "VoteChest gedrückt");
                    }
                }
            }

    }
    @EventHandler
    public void onPlayerPlaceChestEvent(BlockPlaceEvent blockPlaceEvent){
        if(blockPlaceEvent.getBlock().getType() == Material.CHEST){
            if(blockPlaceEvent.getPlayer().hasPermission("VoteChest.Admin") || blockPlaceEvent.getPlayer().isOp()){
                if(Utils.voteChestCreationToggle) {
                    Utils.saveChestLocationToPluginYml(blockPlaceEvent.getPlayer(), blockPlaceEvent.getBlockPlaced());
                }
            }
        }
    }
}
