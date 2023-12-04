package nl.slicegames.whitelistsmp.utils.armoreffects;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public enum ArmorType {
    LEATHER(0, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS),
    CHAINMAIL(1, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS),
    IRON(2, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS),
    GOLD(3, Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS),
    DIAMOND(4, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS),
    NETHERITE(5, Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS);

    private int value;
    private Material helmet;
    private Material chestplate;
    private Material leggings;
    private Material boots;

    private static Map map = new HashMap<>();

    ArmorType(int value, Material helmet, Material chestplate, Material leggings, Material boots){
        this.value = value;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    static {
        for(ArmorType armorType : ArmorType.values()){
            map.put(armorType.value, armorType);
        }
    }

    public static ArmorType valueOf(int armorType){
        return (ArmorType) map.get(armorType);
    }

    public int getValue(){
        return value;
    }

    public Material getHelmet(){
        return helmet;
    }

    public Material getChestplate(){
        return chestplate;
    }

    public Material getLeggings(){
        return leggings;
    }

    public Material getBoots(){
        return boots;
    }
}
