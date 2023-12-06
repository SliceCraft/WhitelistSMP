package nl.slicegames.whitelistsmp.gui.itemeffects;

import nl.slicegames.whitelistsmp.WhitelistSMP;
import nl.slicegames.whitelistsmp.utils.NBTHandler;
import nl.slicegames.whitelistsmp.utils.armoreffects.ArmorType;
import nl.slicegames.whitelistsmp.utils.armoreffects.TypeToItemStack;
import nl.slicegames.whitelistsmp.utils.armoreffects.TrimType;
import nl.slicegames.whitelistsmp.utils.gui.GUIHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Set;

public class ItemEffectsGUI {

    static Plugin plugin = WhitelistSMP.getPlugin(WhitelistSMP.class);

    public static void openGui(Player player) {
        openGui(player, 0);
    }

    public static void openGui(Player player, int page) {
        FileConfiguration config = plugin.getConfig();
        Set<String> armorKeys = config.getConfigurationSection("armoreffects").getKeys(false);
        ArrayList<String> armorKeyList = new ArrayList<>(armorKeys);

        Inventory inventory = GUIHandler.createDefaultInventory(54, ChatColor.RED + "ArmorEffects" + ((armorKeyList.size() - 1) / 18 >= 1 ? " (Page " + (page + 1) + "/" + (((armorKeyList.size() - 1) / 18) + 1) + ")" : ""));

        for (int i = page * 18; i < ((page + 1) * 18) && (i < armorKeyList.size()); i++) {
            ArmorType armorType = ArmorType.valueOf(config.getInt("armoreffects." + armorKeyList.get(i) + ".type"));
            TrimType trimType = TrimType.valueOf(config.getInt("armoreffects." + armorKeyList.get(i) + ".trim"));
            ItemStack helmet = new ItemStack(armorType.getHelmet());
            ItemStack trim = TypeToItemStack.convertTrim(trimType);
            NBTHandler.setInt(helmet, "armoreffect-id", Integer.parseInt(armorKeyList.get(i)));
            NBTHandler.setInt(trim, "armoreffect-id", Integer.parseInt(armorKeyList.get(i)));
            if (i % 18 >= 0 && i % 18 <= 8) {
                inventory.setItem(i % 18, helmet);
                inventory.setItem(i % 18 + 9, trim);
            } else if (i % 18 >= 9 && i % 18 <= 17) {
                inventory.setItem(i % 18 + 18, helmet);
                inventory.setItem(i % 18 + 27, trim);
            } else {
                if(player.hasPermission("whitelistsmp.armoreffects")){
                    player.sendMessage("This should never happen, if this message appears don't change the config and contact Slice");
                }else{
                    player.sendMessage("This should never happen, if this message appears tell staff to contact Slice");
                }
            }
        }

        if(player.hasPermission("whitelistsmp.armoreffects")){
            ItemStack newItemEffect = new ItemStack(Material.GREEN_CONCRETE);
            ItemMeta newItemEffectMeta = newItemEffect.getItemMeta();
            newItemEffectMeta.setDisplayName(ChatColor.GREEN + "Create ArmorEffect");
            newItemEffect.setItemMeta(newItemEffectMeta);
            inventory.setItem(45, newItemEffect);
        }

        if((armorKeyList.size() - 1) / 18 >= 1){
            if(page > 0){
                ItemStack leftArrowItem = new ItemStack(Material.ARROW);
                ItemMeta leftArrowItemMeta = leftArrowItem.getItemMeta();
                leftArrowItemMeta.setDisplayName(ChatColor.GREEN + "Previous Page");
                leftArrowItem.setItemMeta(leftArrowItemMeta);
                NBTHandler.setInt(leftArrowItem, "armoreffect-page", page);
                inventory.setItem(48, leftArrowItem);
            }

            if((armorKeyList.size() - 1) / 18 >= page + 1){
                ItemStack rightArrowItem = new ItemStack(Material.ARROW);
                ItemMeta rightArrowItemMeta = rightArrowItem.getItemMeta();
                rightArrowItemMeta.setDisplayName(ChatColor.GREEN + "Next Page");
                rightArrowItem.setItemMeta(rightArrowItemMeta);
                NBTHandler.setInt(rightArrowItem, "armoreffect-page", page);
                inventory.setItem(50, rightArrowItem);
            }
        }

        ItemStack item1 = new ItemStack(Material.BARRIER);
        ItemMeta item1Meta = item1.getItemMeta();
        item1Meta.setDisplayName(ChatColor.RED + "Close");
        item1.setItemMeta(item1Meta);
        inventory.setItem(53, item1);

        GUIHandler.openGui(player, inventory, 0);
    }

    public static void handleClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();
        Integer id = NBTHandler.getInt(clickedItem, "armoreffect-id");
        if (id != null) {
            ItemEffectGUI.openGui((Player) event.getWhoClicked(), id);
        } else if (clickedItem.getType() == Material.BARRIER) {
            event.getWhoClicked().closeInventory();
        } else if (clickedItem.getType() == Material.GREEN_CONCRETE) {
            ItemEffectGUI.openGui((Player) event.getWhoClicked(), null);
        } else if(clickedItem.getType() == Material.ARROW){
            FileConfiguration config = plugin.getConfig();
            Set<String> armorKeys = config.getConfigurationSection("armoreffects").getKeys(false);
            ArrayList<String> armorKeyList = new ArrayList<>(armorKeys);
            int page = NBTHandler.getInt(clickedItem, "armoreffect-page");
            if((armorKeyList.size() - 1) / 18 >= 1){
                if(page > 0 && event.getSlot() == 48){
                    openGui((Player) event.getWhoClicked(), page - 1);
                }
                if((armorKeyList.size() - 1) / 18 >= page + 1 && event.getSlot() == 50){
                    openGui((Player) event.getWhoClicked(), page + 1);
                }
            }
        }
    }
}
