package nl.slicegames.whitelistsmp.listeners;

import nl.slicegames.whitelistsmp.utils.gui.GUIHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        GUIHandler.handleClickEvent(event);
    }
}
