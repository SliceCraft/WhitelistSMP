package nl.slicegames.whitelistsmp.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import nl.slicegames.whitelistsmp.WhitelistSMP;
import nl.slicegames.whitelistsmp.utils.armoreffects.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PlayerArmorChangeListener implements Listener {
    Plugin plugin = WhitelistSMP.getPlugin(WhitelistSMP.class);

    @EventHandler
    public void onPlayerArmorChange(PlayerArmorChangeEvent event){
        Player player = event.getPlayer();
        ArmorEffectsCheck.executeCheck(player);
    }
}
