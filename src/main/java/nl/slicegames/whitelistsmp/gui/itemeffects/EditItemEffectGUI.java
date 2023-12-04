package nl.slicegames.whitelistsmp.gui.itemeffects;

import nl.slicegames.whitelistsmp.utils.NBTHandler;
import nl.slicegames.whitelistsmp.utils.armoreffects.*;
import nl.slicegames.whitelistsmp.utils.gui.GUIHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class EditItemEffectGUI {
    public static void openGui(Player player, Integer effectid, ArmorType armorType, TrimType trimType, EffectType effectType, Integer strength, Integer edit){
        Inventory inventory = GUIHandler.createDefaultInventory(54, ChatColor.RED + "ArmorEffect");

        ItemStack firstitem = inventory.getItem(0);
        NBTHandler.setInt(firstitem, "armoreffect-edit", edit);
        inventory.setItem(0, firstitem);

        if(edit == 0){
            listArmorTypes(inventory, effectid, trimType, effectType, strength);
        }else if(edit == 1){
            // TODO: Show all trim types
        }else if(edit == 2){
            // TODO: Show all effect types
        }else if(edit == 3){
            // TODO: Show edit level gui
        }

        ItemStack item1 = new ItemStack(Material.BARRIER);
        ItemMeta item1Meta = item1.getItemMeta();
        item1Meta.setDisplayName(ChatColor.RED + "Back");
        item1.setItemMeta(item1Meta);
        inventory.setItem(53, item1);

        GUIHandler.openGui(player, inventory, 2);
    }

    public static void handleClick(InventoryClickEvent event){
        ItemStack clickedItem = event.getCurrentItem();
        Integer edit = NBTHandler.getInt(event.getClickedInventory().getItem(0), "armoreffect-edit");
        if(edit == 4){
            // TODO: Open level gui
            return;
        }
        Integer id = NBTHandler.getInt(event.getClickedInventory().getItem(0), "armoreffect-id");
        ItemEffectGUI.openGui((Player) event.getWhoClicked(), id, ArmorType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-type")), TrimType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-trim")), EffectType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-effect")), NBTHandler.getInt(clickedItem, "armoreffect-strength"));
    }

    private static void listArmorTypes(Inventory inventory, Integer effectid, TrimType trimType, EffectType effectType, Integer strength){
        // BUG: This code doesn't work if there are more then 7 armor types
        List<ArmorType> armorTypes = Arrays.asList(ArmorType.values());
        for(int i = 0; i < armorTypes.size(); i++){
            ItemStack itemStack = new ItemStack(armorTypes.get(i).getHelmet());
            ArmorEffectsNBTDefaultData.addNBTDataToItems(itemStack, effectid, armorTypes.get(i), trimType, effectType, strength);
            inventory.setItem(i + 10, itemStack);
        }
    }
}
