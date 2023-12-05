package nl.slicegames.whitelistsmp.utils;

import nl.slicegames.whitelistsmp.WhitelistSMP;
import nl.slicegames.whitelistsmp.utils.armoreffects.ArmorType;
import nl.slicegames.whitelistsmp.utils.armoreffects.EffectType;
import nl.slicegames.whitelistsmp.utils.armoreffects.TrimType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class ConfigHandler {
    static Plugin plugin = WhitelistSMP.getPlugin(WhitelistSMP.class);

    public static void updateEffect(Integer id, ArmorType armorType, TrimType trimType, EffectType effectType, int strength){
        File existingConfigFile = new File(plugin.getDataFolder() ,"config.yml");
        FileConfiguration existingFileConfiguration = YamlConfiguration.loadConfiguration(existingConfigFile);

        if(id == null) id = getNewId();
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

    private static Integer getNewId(){
        Set<String> armorKeys = plugin.getConfig().getConfigurationSection("armoreffects").getKeys(false);
        if(armorKeys.size() == 0) return 0;
        return Integer.parseInt(Collections.max(armorKeys)) + 1;
    }
}
