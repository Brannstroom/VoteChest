package de.Gerrit.VoteChest.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChestClickListener implements Listener{

    Player p;


    @EventHandler
    public void onPlayerChestClickEvent(PlayerInteractEvent chestClick){

            chestClick.getPlayer().sendMessage("Du hast eine VoteChest angeklickt" + chestClick.getClickedBlock().getMetadata("VoteChest"));





    }
    @EventHandler
    public void onPlayerPlaceChestEvent(BlockPlaceEvent blockPlaceEvent){
        if(blockPlaceEvent.getBlock().getType() == Material.CHEST){



        }
    }
}
