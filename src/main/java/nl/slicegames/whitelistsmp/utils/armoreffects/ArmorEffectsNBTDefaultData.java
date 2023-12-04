package nl.slicegames.whitelistsmp.utils.armoreffects;

import nl.slicegames.whitelistsmp.utils.NBTHandler;
import org.bukkit.inventory.ItemStack;

public class ArmorEffectsNBTDefaultData {
    public static void addNBTDataToItems(ItemStack itemStack, Integer id, ArmorType armorType, TrimType trimType, EffectType effectType, Integer strength){
        if(id != null) NBTHandler.setInt(itemStack, "armoreffect-id", id);
        if(armorType != null) NBTHandler.setInt(itemStack, "armoreffect-type", armorType.getValue());
        if(trimType != null) NBTHandler.setInt(itemStack, "armoreffect-trim", trimType.getValue());
        if(effectType != null) NBTHandler.setInt(itemStack, "armoreffect-effect", effectType.getValue());
        if(strength != null) NBTHandler.setInt(itemStack, "armoreffect-strength", strength);
    }
}
