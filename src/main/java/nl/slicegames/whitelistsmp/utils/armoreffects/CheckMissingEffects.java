package nl.slicegames.whitelistsmp.utils.armoreffects;

import nl.slicegames.whitelistsmp.WhitelistSMP;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CheckMissingEffects {
    static Plugin plugin = WhitelistSMP.getPlugin(WhitelistSMP.class);

    public static ArrayList<EffectType> check(){
        ArrayList<EffectType> effectTypes = new ArrayList<>(Arrays.asList(EffectType.values()));
        Set<String> armorKeys = plugin.getConfig().getConfigurationSection("armoreffects").getKeys(false);
        for(String armorKey : armorKeys){
            effectTypes.remove(EffectType.valueOf(plugin.getConfig().getInt("armoreffects." + armorKey + ".effect")));
        }
        return effectTypes;
    }
}
