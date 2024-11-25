package nl.slicegames.whitelistsmp.gui.itemeffects;

import nl.slicegames.whitelistsmp.WhitelistSMP;
import nl.slicegames.whitelistsmp.utils.ConfigHandler;
import nl.slicegames.whitelistsmp.utils.NBTHandler;
import nl.slicegames.whitelistsmp.utils.armoreffects.*;
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

public class ItemEffectGUI {
    static Plugin plugin = WhitelistSMP.getPlugin(WhitelistSMP.class);

    public static void openGui(Player player, Integer effectid, ArmorType armorType, TrimType trimType, EffectType effectType, Integer strength){
        Inventory inventory = GUIHandler.createDefaultInventory(54, ChatColor.RED + "ArmorEffect");

        ItemStack helmet = new ItemStack(armorType.getHelmet());
        ItemStack trim = TypeToItemStack.convertTrim(trimType);
        ItemStack potion = TypeToItemStack.convertEffectToPotion(effectType, strength);
        ArmorEffectsNBTDefaultData.addNBTDataToItems(helmet, effectid, armorType, trimType, effectType, strength);
        ArmorEffectsNBTDefaultData.addNBTDataToItems(trim, effectid,armorType, trimType, effectType, strength);
        ArmorEffectsNBTDefaultData.addNBTDataToItems(potion, effectid,armorType, trimType, effectType, strength);
        inventory.setItem(10, helmet);
        inventory.setItem(13, trim);
        inventory.setItem(16, potion);

        ItemStack item1 = new ItemStack(Material.BARRIER);
        ItemMeta item1Meta = item1.getItemMeta();
        item1Meta.setDisplayName(ChatColor.RED + "Back");
        item1.setItemMeta(item1Meta);
        inventory.setItem(40, item1);

        if(player.hasPermission("whitelistsmp.armoreffects")){
            ItemStack newItemEffect = new ItemStack(Material.GREEN_CONCRETE);
            ItemMeta newItemEffectMeta = newItemEffect.getItemMeta();
            newItemEffectMeta.setDisplayName(ChatColor.GREEN + "Save ArmorEffect");
            newItemEffect.setItemMeta(newItemEffectMeta);
            ArmorEffectsNBTDefaultData.addNBTDataToItems(newItemEffect, effectid, armorType, trimType, effectType, strength);

            inventory.setItem(37, newItemEffect);

            if(effectid != null){
                ItemStack deleteItemEffect = new ItemStack(Material.TNT);
                ItemMeta deleteItemEffectMeta = deleteItemEffect.getItemMeta();
                deleteItemEffectMeta.setDisplayName(ChatColor.RED + "Delete ArmorEffect");
                deleteItemEffect.setItemMeta(deleteItemEffectMeta);
                NBTHandler.setInt(deleteItemEffect, "armoreffect-id", effectid);
                inventory.setItem(43, deleteItemEffect);
            }
        }

        GUIHandler.openGui(player, inventory, 1);
    }

    public static void openGui(Player player, Integer effectid) {
        FileConfiguration config = plugin.getConfig();

        ArmorType armorType = ArmorType.valueOf(config.getInt("armoreffects." + effectid + ".type"));
        TrimType trimType = TrimType.valueOf(config.getInt("armoreffects." + effectid + ".trim"));
        EffectType effectType = EffectType.valueOf(config.getInt("armoreffects." + effectid + ".effect"));
        Integer strength = config.getInt("armoreffects." + effectid + ".strength");
        openGui(player, effectid, armorType, trimType, effectType, strength);
    }

    public static void handleClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();
        Integer id = NBTHandler.getInt(clickedItem, "armoreffect-id");
        if (clickedItem.getType() == Material.BARRIER && event.getSlot() == 40) {
            ItemEffectsGUI.openGui((Player) event.getWhoClicked());
        } else {
            if(!event.getWhoClicked().hasPermission("whitelistsmp.armoreffects")) return;
            if (clickedItem.getType() == Material.TNT) {
                ConfigHandler.deleteEffect(id);
                ItemEffectsGUI.openGui((Player) event.getWhoClicked());
            }else if (event.getSlot() == 10){
                EditItemEffectGUI.openGui((Player) event.getWhoClicked(), id, ArmorType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-type")), TrimType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-trim")), EffectType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-effect")), NBTHandler.getInt(clickedItem, "armoreffect-strength"), 0);
            } else if (event.getSlot() == 13){
                EditItemEffectGUI.openGui((Player) event.getWhoClicked(), id, ArmorType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-type")), TrimType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-trim")), EffectType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-effect")), NBTHandler.getInt(clickedItem, "armoreffect-strength"), 1);
            } else if (event.getSlot() == 16){
                EditItemEffectGUI.openGui((Player) event.getWhoClicked(), id, ArmorType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-type")), TrimType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-trim")), EffectType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-effect")), NBTHandler.getInt(clickedItem, "armoreffect-strength"), 2);
            } else if (event.getSlot() == 37){
                ConfigHandler.updateEffect(id, ArmorType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-type")), TrimType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-trim")), EffectType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-effect")), NBTHandler.getInt(clickedItem, "armoreffect-strength"));
                ItemEffectsGUI.openGui((Player) event.getWhoClicked());
            }
        }
    }
}
