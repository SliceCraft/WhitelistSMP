package nl.slicegames.whitelistsmp.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import nl.slicegames.whitelistsmp.WhitelistSMP;
import nl.slicegames.whitelistsmp.utils.armoreffects.ArmorType;
import nl.slicegames.whitelistsmp.utils.armoreffects.EffectType;
import nl.slicegames.whitelistsmp.utils.armoreffects.TrimType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import java.util.Objects;
import java.util.Set;

public class PlayerArmorChangeListener implements Listener {
    Plugin plugin = WhitelistSMP.getPlugin(WhitelistSMP.class);

    @EventHandler
    public void onPlayerArmorChange(PlayerArmorChangeEvent event){
        Player player = event.getPlayer();
        EntityEquipment equipment = player.getEquipment();
        FileConfiguration config = plugin.getConfig();
        Set<String> armorKeys = config.getConfigurationSection("armoreffects").getKeys(false);
        for(String armorKey : armorKeys){
            ArmorType armorType = ArmorType.valueOf(config.getInt("armoreffects." + armorKey + ".type"));
            EffectType effectType = EffectType.valueOf(config.getInt("armoreffects." + armorKey + ".effect"));
            TrimType trimType = TrimType.valueOf(config.getInt("armoreffects." + armorKey + ".trim"));
            if(equipment.getBoots() != null && equipment.getLeggings() != null && equipment.getChestplate() != null && equipment.getHelmet() != null){
                ItemStack boots = equipment.getBoots();
                ItemStack leggings = equipment.getLeggings();
                ItemStack chestplate = equipment.getChestplate();
                ItemStack helmet = equipment.getHelmet();
                ArmorMeta bootsmeta = (ArmorMeta) boots.getItemMeta();
                ArmorMeta leggingsmeta = (ArmorMeta) leggings.getItemMeta();
                ArmorMeta chestplatemeta = (ArmorMeta) chestplate.getItemMeta();
                ArmorMeta helmetmeta = (ArmorMeta) helmet.getItemMeta();
                if(boots.getType() == armorType.getBoots() && leggings.getType() == armorType.getLeggings() && chestplate.getType() == armorType.getChestplate() && helmet.getType() == armorType.getHelmet() && bootsmeta.getTrim() != null && leggingsmeta.getTrim() != null && chestplatemeta.getTrim() != null && helmetmeta.getTrim() != null && bootsmeta.getTrim().getPattern() == trimType.getTrimPattern() && leggingsmeta.getTrim().getPattern() == trimType.getTrimPattern() && chestplatemeta.getTrim().getPattern() == trimType.getTrimPattern() && helmetmeta.getTrim().getPattern() == trimType.getTrimPattern()){
                    int strength = config.getInt("armoreffects." + armorKey + ".strength");
                    if(player.getPotionEffect(effectType.getEffectType()) == null || player.getPotionEffect(effectType.getEffectType()).getDuration() < 86400 || player.getPotionEffect(effectType.getEffectType()).getAmplifier() < strength){
                        player.addPotionEffect(new PotionEffect(effectType.getEffectType(), 31536000, strength - 1));
                    }
                }else{
                    if(player.getPotionEffect(effectType.getEffectType()) != null && player.getPotionEffect(effectType.getEffectType()).getDuration() > 86400){
                        player.removePotionEffect(effectType.getEffectType());
                    }
                }
            }else{
                if(player.getPotionEffect(effectType.getEffectType()) != null && player.getPotionEffect(effectType.getEffectType()).getDuration() > 86400){
                    player.removePotionEffect(effectType.getEffectType());
                }
            }
        }
    }
}
