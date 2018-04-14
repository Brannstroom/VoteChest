package de.Gerrit.VoteChest.Listener;

import de.Gerrit.VoteChest.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class ChestBlockPlaceListener extends SuperChestListener implements Listener {

    @EventHandler
    public void onPlayerPlaceChestEvent(BlockPlaceEvent blockPlaceEvent){
        if(blockPlaceEvent.getBlock().getType() == Material.CHEST){
            if(blockPlaceEvent.getPlayer().hasPermission("VoteChest.Admin") || blockPlaceEvent.getPlayer().isOp()){
                if(Utils.voteChestCreationToggle) {
                    saveChestLocationToYaml(blockPlaceEvent.getBlockPlaced().getX(),
                            blockPlaceEvent.getBlockPlaced().getY(), blockPlaceEvent.getBlockPlaced().getZ(),
                            blockPlaceEvent.getPlayer().getWorld().getName());
                }
            }
        }
    }
}

