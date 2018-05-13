package de.Gerrit.VoteChest.Listener;

import de.Gerrit.VoteChest.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ChestInventoryCancelPlayerItemPickupListener implements Listener{

    @EventHandler
    public void onPlayerChestClickEvent(InventoryClickEvent chestClick) {
        if(chestClick.getInventory().getName().equalsIgnoreCase(Utils.PREFIX)){
            chestClick.getResult();
            chestClick.setResult(Event.Result.DENY);
        }
    }
}
