package nl.slicegames.whitelistsmp.utils.armoreffects;

import nl.slicegames.whitelistsmp.WhitelistSMP;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Set;

public class ArmorEffectsCheck {
    static Plugin plugin = WhitelistSMP.getPlugin(WhitelistSMP.class);
    public static void executeCheck(Player player){
        EntityEquipment equipment = player.getEquipment();
        FileConfiguration config = plugin.getConfig();
        Set<String> armorKeys = config.getConfigurationSection("armoreffects").getKeys(false);
        if(equipment.getBoots() != null && equipment.getLeggings() != null && equipment.getChestplate() != null && equipment.getHelmet() != null) {
            ItemStack boots = equipment.getBoots();
            ItemStack leggings = equipment.getLeggings();
            ItemStack chestplate = equipment.getChestplate();
            ItemStack helmet = equipment.getHelmet();
            ArmorMeta bootsmeta = (ArmorMeta) boots.getItemMeta();
            ArmorMeta leggingsmeta = (ArmorMeta) leggings.getItemMeta();
            ArmorMeta chestplatemeta = (ArmorMeta) chestplate.getItemMeta();
            ArmorMeta helmetmeta = (ArmorMeta) helmet.getItemMeta();
            ArrayList<EffectType> unusedEffects = CheckMissingEffects.check();
            for(EffectType effectType : unusedEffects){
                if (player.getPotionEffect(effectType.getEffectType()) != null && player.getPotionEffect(effectType.getEffectType()).getDuration() > 86400) {
                    player.removePotionEffect(effectType.getEffectType());
                }
            }
            for(String armorKey : armorKeys) {
                ArmorType armorType = ArmorType.valueOf(config.getInt("armoreffects." + armorKey + ".type"));
                EffectType effectType = EffectType.valueOf(config.getInt("armoreffects." + armorKey + ".effect"));
                TrimType trimType = TrimType.valueOf(config.getInt("armoreffects." + armorKey + ".trim"));
                if (boots.getType() == armorType.getBoots() && leggings.getType() == armorType.getLeggings() && chestplate.getType() == armorType.getChestplate() && helmet.getType() == armorType.getHelmet() && (trimType == TrimType.ANY || (bootsmeta.getTrim() != null && leggingsmeta.getTrim() != null && chestplatemeta.getTrim() != null && helmetmeta.getTrim() != null && bootsmeta.getTrim().getPattern() == trimType.getTrimPattern() && leggingsmeta.getTrim().getPattern() == trimType.getTrimPattern() && chestplatemeta.getTrim().getPattern() == trimType.getTrimPattern() && helmetmeta.getTrim().getPattern() == trimType.getTrimPattern()))) {
                    int strength = config.getInt("armoreffects." + armorKey + ".strength");
                    if (player.getPotionEffect(effectType.getEffectType()) == null || player.getPotionEffect(effectType.getEffectType()).getDuration() < 86400 || player.getPotionEffect(effectType.getEffectType()).getAmplifier() < strength) {
                        player.addPotionEffect(new PotionEffect(effectType.getEffectType(), 31536000, strength - 1));
                    }
                } else {
                    if (player.getPotionEffect(effectType.getEffectType()) != null && player.getPotionEffect(effectType.getEffectType()).getDuration() > 86400) {
                        player.removePotionEffect(effectType.getEffectType());
                    }
                }
            }
        }else{
            for(EffectType effectType : EffectType.values()){
                if (player.getPotionEffect(effectType.getEffectType()) != null && player.getPotionEffect(effectType.getEffectType()).getDuration() > 86400) {
                    player.removePotionEffect(effectType.getEffectType());
                }
            }
        }
    }
}
