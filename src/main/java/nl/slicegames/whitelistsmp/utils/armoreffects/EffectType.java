package nl.slicegames.whitelistsmp.utils.armoreffects;

import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public enum EffectType {
    SPEED(0, PotionEffectType.SPEED),
    HASTE(1, PotionEffectType.FAST_DIGGING),
    STRENGTH(2, PotionEffectType.INCREASE_DAMAGE),
    JUMP_BOOST(3, PotionEffectType.JUMP),
    NAUSEA(4, PotionEffectType.CONFUSION),
    REGENERATION(5, PotionEffectType.REGENERATION),
    RESISTANCE(6, PotionEffectType.DAMAGE_RESISTANCE),
    FIRE_RESISTANCE(7, PotionEffectType.FIRE_RESISTANCE),
    WATER_BREATHING(8, PotionEffectType.WATER_BREATHING),
    INVISIBILITY(9, PotionEffectType.INVISIBILITY),
    BLINDNESS(10, PotionEffectType.BLINDNESS),
    NIGHT_VISION(11, PotionEffectType.NIGHT_VISION),
    WEAKNESS(12, PotionEffectType.WEAKNESS),
    HEALTH_BOOST(13, PotionEffectType.HEALTH_BOOST),
    ABSORPTION(14, PotionEffectType.ABSORPTION),
    SATURATION(15, PotionEffectType.SATURATION),
    GLOWING(16, PotionEffectType.GLOWING),
    LEVITATION(17, PotionEffectType.LEVITATION),
    LUCK(18, PotionEffectType.LUCK),
    SLOW_FALLING(19, PotionEffectType.SLOW_FALLING),
    CONDUIT_POWER(20, PotionEffectType.CONDUIT_POWER),
    DOLPHINS_GRACE(21, PotionEffectType.DOLPHINS_GRACE),
    HERO_OF_THE_VILLAGE(22, PotionEffectType.HERO_OF_THE_VILLAGE);

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
