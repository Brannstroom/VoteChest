package de.Gerrit.VoteChest.Listener;

import de.Gerrit.VoteChest.Utils;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;


public class InventoryCancelCloseDuringRollListener  implements Listener {

        @EventHandler
        public void onPlayerCoseInventory(InventoryCloseEvent chestClick) {
            if(chestClick.getInventory().getName().equalsIgnoreCase(Utils.PREFIX)){
                
            }
        }
}
