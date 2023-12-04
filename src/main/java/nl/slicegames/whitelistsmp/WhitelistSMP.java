package nl.slicegames.whitelistsmp;

import nl.slicegames.whitelistsmp.commands.ArmorEffectsCommand;
import nl.slicegames.whitelistsmp.listeners.PlayerArmorChangeListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WhitelistSMP extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("armoreffects").setExecutor(new ArmorEffectsCommand());
        getServer().getPluginManager().registerEvents(new PlayerArmorChangeListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
