package nl.slicegames.whitelistsmp.utils;

import nl.slicegames.whitelistsmp.WhitelistSMP;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class NBTHandler {
    public static void setInt(ItemStack itemStack, String key, int value){
        ItemMeta itemMeta = itemStack.getItemMeta();
        NamespacedKey NSkey = new NamespacedKey(WhitelistSMP.getPlugin(WhitelistSMP.class), key);
        itemMeta.getPersistentDataContainer().set(NSkey, PersistentDataType.INTEGER, value);
        itemStack.setItemMeta(itemMeta);
    }

    public static Integer getInt(ItemStack itemStack, String key){
        NamespacedKey NSkey = new NamespacedKey(WhitelistSMP.getPlugin(WhitelistSMP.class), key);
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        if(container.has(NSkey, PersistentDataType.INTEGER)) {
            return container.get(NSkey, PersistentDataType.INTEGER);
        }
        return null;
    }
}
