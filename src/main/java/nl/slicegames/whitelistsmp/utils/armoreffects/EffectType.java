package nl.slicegames.whitelistsmp.utils.armoreffects;

import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public enum EffectType {
    SPEED(0, PotionEffectType.SPEED),
    SLOWNESS(1, PotionEffectType.SLOW);

    private int value;
    private PotionEffectType effectType;
    private static Map map = new HashMap<>();

    EffectType(int value, PotionEffectType effectType){
        this.value = value;
        this.effectType = effectType;
    }

    static {
        for(EffectType effectType : EffectType.values()){
            map.put(effectType.value, effectType);
        }
    }

    public static EffectType valueOf(int effectType){
        return (EffectType) map.get(effectType);
    }

    public int getValue(){
        return value;
    }

    public PotionEffectType getEffectType(){
        return effectType;
    }
}
