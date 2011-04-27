package me.ryall.wrath;

import java.util.logging.Logger;

import me.ryall.wrath.communication.CommunicationManager;
import me.ryall.wrath.settings.PermissionManager;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

// TODO:
// Add a list of "doomed" players when wrath is called and ensure that they die.
// If a message, "%player% is already dying a slow and painful death."

public class Wrath extends JavaPlugin
{
    public static String PLUGIN_NAME = "Wrath";
    public static String LOG_HEADER = "[" + PLUGIN_NAME + "] ";
    private static Wrath instance = null;
    
    private Logger log;
    private PermissionManager permissionManager;
    private CommunicationManager communicationManager;
    
    public static Wrath get()
    {
        return instance;
    }
    
    public void onEnable()
    {
        instance = this;
        log = Logger.getLogger("Minecraft");
        permissionManager = new PermissionManager();
        communicationManager = new CommunicationManager();
        
        logInfo("Started");
    }

    public void onDisable()
    {
        logInfo("Stopped");
    }
    
    public boolean onCommand(CommandSender _sender, Command _command, String _label, String[] _args)
    {
        if (_label.equals("wrath"))
        {
            if (_args.length == 2)
            {
                if (_args[0].equals("strike"))
                {
                    for (World world : getServer().getWorlds()) 
                    {
                        for (Player player : world.getPlayers())
                        {
                            if (player.getName().equalsIgnoreCase(_args[1]))
                            {
                                world.strikeLightning(player.getLocation());
                                player.damage(player.getHealth() - 1);
                                player.damage(1);
                                
                                return true;
                            }
                        }
                    }
                }
            } 
            else
            {
                if (_sender instanceof Player)
                {
                    Player player = (Player)_sender;
                    
                    if (permissionManager.hasStrikePermission(player))
                        communicationManager.command(player, "/wrath strike <player>", "Strike a player down with lightning");
                }
            }
        
            return true;
        }
        
        return false;
    }
    
    public void logInfo(String _message)
    {
        log.info(LOG_HEADER + _message);
    }
    
    public void logError(String _message)
    {
        log.severe(LOG_HEADER + _message);
    }
}