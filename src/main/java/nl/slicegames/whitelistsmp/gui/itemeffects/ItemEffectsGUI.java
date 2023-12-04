package nl.slicegames.whitelistsmp.gui.itemeffects;

import nl.slicegames.whitelistsmp.WhitelistSMP;
import nl.slicegames.whitelistsmp.utils.NBTHandler;
import nl.slicegames.whitelistsmp.utils.armoreffects.ArmorType;
import nl.slicegames.whitelistsmp.utils.armoreffects.EffectType;
import nl.slicegames.whitelistsmp.utils.armoreffects.TrimPatternToMaterial;
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
        Inventory inventory = GUIHandler.createDefaultInventory(54, ChatColor.RED + "ArmorEffects");

        FileConfiguration config = plugin.getConfig();

        Set<String> armorKeys = config.getConfigurationSection("armoreffects").getKeys(false);
        ArrayList<String> armorKeyList = new ArrayList<>(armorKeys);
        for (int i = 0; i < armorKeyList.size(); i++) {
            ArmorType armorType = ArmorType.valueOf(config.getInt("armoreffects." + armorKeyList.get(i) + ".type"));
            TrimType trimType = TrimType.valueOf(config.getInt("armoreffects." + armorKeyList.get(i) + ".trim"));
            ItemStack helmet = new ItemStack(armorType.getHelmet());
            ItemStack trim = new ItemStack(TrimPatternToMaterial.convert(trimType));
            NBTHandler.setInt(helmet, "armoreffect-id", i);
            NBTHandler.setInt(trim, "armoreffect-id", i);
            if (i >= 0 && i <= 8) {
                System.out.println(i);
                inventory.setItem(i, helmet);
                inventory.setItem(i + 9, trim);
            } else if (i >= 9 && i <= 17) {
                inventory.setItem(i + 9, helmet);
                inventory.setItem(i + 18, trim);
            } else {
                System.out.println("Too many armor effects, can't display them all");
            }
        }

        if(player.hasPermission("whitelistsmp.armoreffects")){
            ItemStack newItemEffect = new ItemStack(Material.GREEN_CONCRETE);
            ItemMeta newItemEffectMeta = newItemEffect.getItemMeta();
            newItemEffectMeta.setDisplayName(ChatColor.GREEN + "Create ArmorEffect");
            newItemEffect.setItemMeta(newItemEffectMeta);
            inventory.setItem(45, newItemEffect);
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
        }
    }
}
