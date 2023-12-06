package nl.slicegames.whitelistsmp.utils.armoreffects;

import org.bukkit.Material;
import org.bukkit.inventory.meta.trim.TrimPattern;

import java.util.HashMap;
import java.util.Map;

public enum TrimType {
    ANY(0, null),
    SENTRY(1, TrimPattern.SENTRY),
    VEX(2, TrimPattern.VEX),
    WILD(3, TrimPattern.WILD),
    COAST(4, TrimPattern.COAST),
    DUNE(5, TrimPattern.DUNE),
    WAYFINDER(6, TrimPattern.WAYFINDER),
    RAISER(7, TrimPattern.RAISER),
    SHAPER(8, TrimPattern.SHAPER),
    HOST(9, TrimPattern.HOST),
    WARD(10, TrimPattern.WARD),
    SILENCE(11, TrimPattern.SILENCE),
    TIDE(12, TrimPattern.TIDE),
    SNOUT(13, TrimPattern.SNOUT),
    RIB(14, TrimPattern.RIB),
    EYE(15, TrimPattern.EYE),
    SPIRE(16, TrimPattern.SPIRE);

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
