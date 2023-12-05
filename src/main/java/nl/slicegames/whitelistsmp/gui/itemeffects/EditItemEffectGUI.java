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

import java.util.Arrays;
import java.util.List;

public class EditItemEffectGUI {
    public static void openGui(Player player, Integer effectid, ArmorType armorType, TrimType trimType, EffectType effectType, Integer strength, Integer edit){
        Inventory inventory = GUIHandler.createDefaultInventory(54, ChatColor.RED + "ArmorEffect");

        ItemStack firstitem = inventory.getItem(0);
        NBTHandler.setInt(firstitem, "armoreffect-edit", edit);
        inventory.setItem(0, firstitem);

        if(edit == 0){
            listArmorTypes(inventory, effectid, trimType, effectType, strength);
        }else if(edit == 1){
            listTrimTypes(inventory, effectid, armorType, effectType, strength);
        }else if(edit == 2){
            listEffectTypes(inventory, effectid, armorType, trimType);
        }else if(edit == 3){
            displayLevelChooser(inventory, effectid, armorType, trimType, effectType);
        }

        ItemStack item1 = new ItemStack(Material.BARRIER);
        ItemMeta item1Meta = item1.getItemMeta();
        item1Meta.setDisplayName(ChatColor.RED + "Back");
        item1.setItemMeta(item1Meta);
        ArmorEffectsNBTDefaultData.addNBTDataToItems(item1, effectid, armorType, trimType, effectType, strength);
        inventory.setItem(53, item1);

        GUIHandler.openGui(player, inventory, 2);
    }

    public static void handleClick(InventoryClickEvent event){
        ItemStack clickedItem = event.getCurrentItem();

        Integer edit = NBTHandler.getInt(event.getClickedInventory().getItem(0), "armoreffect-edit");
        if(event.getSlot() != 53 && edit != null && edit == 2){
            EditItemEffectGUI.openGui((Player) event.getWhoClicked(), NBTHandler.getInt(clickedItem, "armoreffect-id"), ArmorType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-type")), TrimType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-trim")), EffectType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-effect")), NBTHandler.getInt(clickedItem, "armoreffect-strength"), 3);
            return;
        }
        if(edit != null && edit == 3 && (event.getSlot() == 37 || event.getSlot() == 43)){
            Integer id = NBTHandler.getInt(clickedItem, "armoreffect-id");
            ArmorType armorType = ArmorType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-type"));
            TrimType trimType = TrimType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-trim"));
            EffectType effectType = EffectType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-effect"));
            int strength = NBTHandler.getInt(clickedItem, "armoreffect-strength");
            if(event.getSlot() == 37 && strength - 1 >= 1){
                ItemStack newPotion = TypeToItemStack.convertEffectToPotion(EffectType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-effect")), strength - 1);
                ItemStack lower = event.getClickedInventory().getItem(37);
                ItemStack itemBack = event.getClickedInventory().getItem(40);
                ItemStack higher = event.getClickedInventory().getItem(43);
                ArmorEffectsNBTDefaultData.addNBTDataToItems(lower, id, armorType, trimType, effectType, strength - 1);
                ArmorEffectsNBTDefaultData.addNBTDataToItems(itemBack, id, armorType, trimType, effectType, strength - 1);
                ArmorEffectsNBTDefaultData.addNBTDataToItems(higher, id, armorType, trimType, effectType, strength - 1);
                event.getClickedInventory().setItem(13, newPotion);
                event.getClickedInventory().setItem(37, lower);
                event.getClickedInventory().setItem(40, itemBack);
                event.getClickedInventory().setItem(43, higher);
            }else if(event.getSlot() == 43){
                ItemStack newPotion = TypeToItemStack.convertEffectToPotion(EffectType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-effect")), strength + 1);
                ItemStack lower = event.getClickedInventory().getItem(37);
                ItemStack itemBack = event.getClickedInventory().getItem(40);
                ItemStack higher = event.getClickedInventory().getItem(43);
                ArmorEffectsNBTDefaultData.addNBTDataToItems(lower, id, armorType, trimType, effectType, strength + 1);
                ArmorEffectsNBTDefaultData.addNBTDataToItems(itemBack, id, armorType, trimType, effectType, strength + 1);
                ArmorEffectsNBTDefaultData.addNBTDataToItems(higher, id, armorType, trimType, effectType, strength + 1);
                event.getClickedInventory().setItem(13, newPotion);
                event.getClickedInventory().setItem(37, lower);
                event.getClickedInventory().setItem(40, itemBack);
                event.getClickedInventory().setItem(43, higher);
            }
            return;
        }
        if(NBTHandler.getInt(clickedItem, "armoreffect-type") != null){
            ItemEffectGUI.openGui((Player) event.getWhoClicked(), NBTHandler.getInt(clickedItem, "armoreffect-id"), ArmorType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-type")), TrimType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-trim")), EffectType.valueOf(NBTHandler.getInt(clickedItem, "armoreffect-effect")), NBTHandler.getInt(clickedItem, "armoreffect-strength"));
        }
    }

    private static void listArmorTypes(Inventory inventory, Integer effectid, TrimType trimType, EffectType effectType, Integer strength){
        List<ArmorType> armorTypes = Arrays.asList(ArmorType.values());
        for(int i = 0; i < 28; i++){
            if(i < armorTypes.size()){
                ItemStack itemStack = new ItemStack(armorTypes.get(i).getHelmet());
                ArmorEffectsNBTDefaultData.addNBTDataToItems(itemStack, effectid, armorTypes.get(i), trimType, effectType, strength);
                if(i < 7){
                    inventory.setItem(i + 10, itemStack);
                }else if(i < 14){
                    inventory.setItem(i + 12, itemStack);
                }else if(i < 21){
                    inventory.setItem(i + 14, itemStack);
                }else {
                    inventory.setItem(i + 16, itemStack);
                }
                continue;
            }
            if(i < 7){
                inventory.setItem(i + 10, new ItemStack(Material.AIR));
            }else if(i < 14){
                inventory.setItem(i + 12, new ItemStack(Material.AIR));
            }else if(i < 21){
                inventory.setItem(i + 14, new ItemStack(Material.AIR));
            }else {
                inventory.setItem(i + 16, new ItemStack(Material.AIR));
            }
        }
    }

    private static void listTrimTypes(Inventory inventory, Integer effectid, ArmorType armorType, EffectType effectType, Integer strength){
        List<TrimType> trimTypes = Arrays.asList(TrimType.values());
        for(int i = 0; i < 28; i++){
            if(i < trimTypes.size()){
                ItemStack itemStack = TypeToItemStack.convertTrim(trimTypes.get(i));
                ArmorEffectsNBTDefaultData.addNBTDataToItems(itemStack, effectid, armorType, trimTypes.get(i), effectType, strength);
                if(i < 7){
                    inventory.setItem(i + 10, itemStack);
                }else if(i < 14){
                    inventory.setItem(i + 12, itemStack);
                }else if(i < 21){
                    inventory.setItem(i + 14, itemStack);
                }else {
                    inventory.setItem(i + 16, itemStack);
                }
                continue;
            }
            if(i < 7){
                inventory.setItem(i + 10, new ItemStack(Material.AIR));
            }else if(i < 14){
                inventory.setItem(i + 12, new ItemStack(Material.AIR));
            }else if(i < 21){
                inventory.setItem(i + 14, new ItemStack(Material.AIR));
            }else {
                inventory.setItem(i + 16, new ItemStack(Material.AIR));
            }
        }
    }

    private static void listEffectTypes(Inventory inventory, Integer effectid, ArmorType armorType, TrimType trimType){
        List<EffectType> effectTypes = Arrays.asList(EffectType.values());
        for(int i = 0; i < 28; i++){
            if(i < effectTypes.size()){
                ItemStack itemStack = TypeToItemStack.convertEffectToPotion(effectTypes.get(i), 1);
                ArmorEffectsNBTDefaultData.addNBTDataToItems(itemStack, effectid, armorType, trimType, effectTypes.get(i), 1);
                if(i < 7){
                    inventory.setItem(i + 10, itemStack);
                }else if(i < 14){
                    inventory.setItem(i + 12, itemStack);
                }else if(i < 21){
                    inventory.setItem(i + 14, itemStack);
                }else {
                    inventory.setItem(i + 16, itemStack);
                }
                continue;
            }
            if(i < 7){
                inventory.setItem(i + 10, new ItemStack(Material.AIR));
            }else if(i < 14){
                inventory.setItem(i + 12, new ItemStack(Material.AIR));
            }else if(i < 21){
                inventory.setItem(i + 14, new ItemStack(Material.AIR));
            }else {
                inventory.setItem(i + 16, new ItemStack(Material.AIR));
            }
        }
    }

    private static void displayLevelChooser(Inventory inventory, Integer effectid, ArmorType armorType, TrimType trimType, EffectType effectType){
        ItemStack potion = TypeToItemStack.convertEffectToPotion(effectType, 1);
        ItemStack lower = new ItemStack(Material.RED_CONCRETE);
        ItemMeta lowerMeta = lower.getItemMeta();
        lowerMeta.setDisplayName(ChatColor.RED + "Lower by one level");
        lower.setItemMeta(lowerMeta);
        ItemStack itemBack = new ItemStack(Material.BARRIER);
        ItemMeta itemBackMeta = itemBack.getItemMeta();
        itemBackMeta.setDisplayName(ChatColor.RED + "Save");
        itemBack.setItemMeta(itemBackMeta);
        ItemStack higher = new ItemStack(Material.GREEN_CONCRETE);
        ItemMeta higherMeta = higher.getItemMeta();
        higherMeta.setDisplayName(ChatColor.GREEN + "Increase by one level");
        higher.setItemMeta(higherMeta);
        ArmorEffectsNBTDefaultData.addNBTDataToItems(lower, effectid, armorType, trimType, effectType, 1);
        ArmorEffectsNBTDefaultData.addNBTDataToItems(itemBack, effectid, armorType, trimType, effectType, 1);
        ArmorEffectsNBTDefaultData.addNBTDataToItems(higher, effectid, armorType, trimType, effectType, 1);
        inventory.setItem(13, potion);
        inventory.setItem(37, lower);
        inventory.setItem(40, itemBack);
        inventory.setItem(43, higher);
    }
}
