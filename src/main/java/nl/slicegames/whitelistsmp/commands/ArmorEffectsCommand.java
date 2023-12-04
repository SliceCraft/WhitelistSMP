package nl.slicegames.whitelistsmp.commands;

import nl.slicegames.whitelistsmp.gui.itemeffects.ItemEffectsGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArmorEffectsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]){
        if(sender instanceof Player){
            ItemEffectsGUI.openGui((Player) sender);
        }else{
            sender.sendMessage("Only a player can use this command");
        }
        return true;
    }
}
