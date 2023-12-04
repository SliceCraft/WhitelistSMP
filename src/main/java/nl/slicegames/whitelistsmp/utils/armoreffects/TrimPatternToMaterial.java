package nl.slicegames.whitelistsmp.utils.armoreffects;

import org.bukkit.Material;

public class TrimPatternToMaterial {
    public static Material convert(TrimType trimType){
        switch (trimType){
            case ANY -> {
                return Material.BARRIER;
            }
            case SENTRY -> {
                return Material.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE;
            }
        }
        return Material.BEDROCK;
    }
}
