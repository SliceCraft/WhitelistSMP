package nl.slicegames.whitelistsmp.utils.armoreffects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

public class TypeToItemStack {
    public static ItemStack convertTrim(TrimType trimType){
        switch (trimType){
            case ANY -> {
                ItemStack itemStack = new ItemStack(Material.BARRIER);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(ChatColor.RED + "Any trim / No Trim");
                itemStack.setItemMeta(itemMeta);
                return itemStack;
            }
            case SENTRY -> {
                return new ItemStack(Material.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
        }
        return new ItemStack(Material.BEDROCK);
    }

    public static ItemStack convertEffectToPotion(EffectType effectType, int strength){
        ItemStack itemStack = new ItemStack(Material.POTION);
        PotionMeta itemMeta = (PotionMeta) itemStack.getItemMeta();
        itemMeta.addCustomEffect(new PotionEffect(effectType.getEffectType(), 1, strength - 1), true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
