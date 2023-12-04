package nl.slicegames.whitelistsmp.utils.gui;

import nl.slicegames.whitelistsmp.WhitelistSMP;
import nl.slicegames.whitelistsmp.gui.itemeffects.EditItemEffectGUI;
import nl.slicegames.whitelistsmp.gui.itemeffects.ItemEffectGUI;
import nl.slicegames.whitelistsmp.gui.itemeffects.ItemEffectsGUI;
import nl.slicegames.whitelistsmp.utils.NBTHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class GUIHandler {
    public static Inventory createDefaultInventory(int size, String title){
        Inventory inventory = Bukkit.createInventory(null, size, title);

        ItemStack backgroundItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();
        backgroundItemMeta.setDisplayName(" ");
        backgroundItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        backgroundItem.setItemMeta(backgroundItemMeta);
        for(int i = 0; i < size; i++){
            inventory.setItem(i, backgroundItem);
        }
        return inventory;
    }

    public static void openGui(Player player, Inventory inventory, int id){
        ItemStack itemId = inventory.getItem(0);
        if(itemId == null){
            System.out.println("There is no item in slot 0 for gui id: " + id + ". This gui does not function.");
            return;
        }
        NBTHandler.setInt(itemId, "gui-id", id);
        inventory.setItem(0, itemId);

        player.openInventory(inventory);
    }

    public static void handleClickEvent(InventoryClickEvent event){
        if(event.getClickedInventory() != event.getView().getTopInventory()) return;
        if(event.getClickedInventory() != null && event.getClickedInventory().getItem(0) != null){
            ItemStack itemStack = event.getClickedInventory().getItem(0);
            Integer id = NBTHandler.getInt(itemStack, "gui-id");

            if(id != null) {
                event.setCancelled(true);

                switch (id){
                    case 0 -> ItemEffectsGUI.handleClick(event);
                    case 1 -> ItemEffectGUI.handleClick(event);
                    case 2 -> EditItemEffectGUI.handleClick(event);
                }
            }
        }
    }
}
