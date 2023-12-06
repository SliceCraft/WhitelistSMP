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
            case VEX -> {
                return new ItemStack(Material.VEX_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case WILD -> {
                return new ItemStack(Material.WILD_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case COAST -> {
                return new ItemStack(Material.COAST_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case DUNE -> {
                return new ItemStack(Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case WAYFINDER -> {
                return new ItemStack(Material.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case RAISER -> {
                return new ItemStack(Material.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case SHAPER -> {
                return new ItemStack(Material.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case HOST -> {
                return new ItemStack(Material.HOST_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case WARD -> {
                return new ItemStack(Material.WARD_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case SILENCE -> {
                return new ItemStack(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case TIDE -> {
                return new ItemStack(Material.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case SNOUT -> {
                return new ItemStack(Material.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case RIB -> {
                return new ItemStack(Material.RIB_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case EYE -> {
                return new ItemStack(Material.EYE_ARMOR_TRIM_SMITHING_TEMPLATE);
            }
            case SPIRE -> {
                return new ItemStack(Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE);
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
