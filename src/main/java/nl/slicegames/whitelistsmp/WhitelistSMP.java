package nl.slicegames.whitelistsmp;

import nl.slicegames.whitelistsmp.commands.ArmorEffectsCommand;
import nl.slicegames.whitelistsmp.listeners.InventoryClickListener;
import nl.slicegames.whitelistsmp.listeners.PlayerArmorChangeListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WhitelistSMP extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("armoreffects").setExecutor(new ArmorEffectsCommand());
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerArmorChangeListener(), this);
        // TODO: Add code for a big wither event
        // TODO: Add the timber enchant
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
