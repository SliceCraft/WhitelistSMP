package nl.slicegames.whitelistsmp.utils.armoreffects;

import org.bukkit.Material;
import org.bukkit.inventory.meta.trim.TrimPattern;

import java.util.HashMap;
import java.util.Map;

public enum TrimType {
    ANY(0, null),
    SENTRY(1, TrimPattern.SENTRY);

    private int value;
    private TrimPattern trimPattern;

    private static Map map = new HashMap<>();

    TrimType(int value, TrimPattern trimPattern){
        this.value = value;
        this.trimPattern = trimPattern;
    }

    static {
        for(TrimType trimType : TrimType.values()){
            map.put(trimType.value, trimType);
        }
    }

    public static TrimType valueOf(int trimType){
        return (TrimType) map.get(trimType);
    }

    public int getValue(){
        return value;
    }

    public TrimPattern getTrimPattern(){
        return trimPattern;
    }
}
