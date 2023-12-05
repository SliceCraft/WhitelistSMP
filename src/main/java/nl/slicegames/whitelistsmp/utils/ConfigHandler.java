package nl.slicegames.whitelistsmp.utils;

import nl.slicegames.whitelistsmp.utils.armoreffects.ArmorType;
import nl.slicegames.whitelistsmp.utils.armoreffects.EffectType;
import nl.slicegames.whitelistsmp.utils.armoreffects.TrimType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import java.io.File;
import java.io.IOException;

public class ConfigHandler {
    public static void updateExistingEffect(Plugin plugin, int id, ArmorType armorType, TrimType trimType, EffectType effectType, int strength){
        File existingConfigFile = new File(plugin.getDataFolder() ,"config.yml");
        FileConfiguration existingFileConfiguration = YamlConfiguration.loadConfiguration(existingConfigFile);

        existingFileConfiguration.set("armoreffects." + id + ".type", armorType.getValue());
        existingFileConfiguration.set("armoreffects." + id + ".trim", trimType.getValue());
        existingFileConfiguration.set("armoreffects." + id + ".effect", effectType.getValue());
        existingFileConfiguration.set("armoreffects." + id + ".strength", strength);

        try {
            existingFileConfiguration.save(existingConfigFile);
            plugin.reloadConfig();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
