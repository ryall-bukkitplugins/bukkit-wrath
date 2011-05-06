package me.ryall.wrath;

import java.util.ArrayList;
import java.util.logging.Logger;

import me.ryall.wrath.communication.CommunicationManager;
import me.ryall.wrath.listeners.BlockListener;
import me.ryall.wrath.listeners.EntityListener;
import me.ryall.wrath.listeners.PlayerListener;
import me.ryall.wrath.listeners.ServerListener;
import me.ryall.wrath.settings.ConfigManager;
import me.ryall.wrath.settings.PermissionManager;
import me.ryall.wrath.system.ExecutionManager;
import me.ryall.wrath.system.Executioner;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Wrath extends JavaPlugin
{
    public static String PLUGIN_NAME = "Wrath";
    public static String LOG_HEADER = "[" + PLUGIN_NAME + "] ";
    private static Wrath instance = null;

    private Logger log;
    private ServerListener serverListener;
    private EntityListener entityListener;
    private BlockListener blockListener;
    private PlayerListener playerListener;
    private ConfigManager configManager;
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

        serverListener = new ServerListener();
        entityListener = new EntityListener();
        blockListener = new BlockListener();
        playerListener = new PlayerListener();

        configManager = new ConfigManager();
        permissionManager = new PermissionManager();
        communicationManager = new CommunicationManager();

        registerEvents();

        logInfo("Started");
    }

    public void onDisable()
    {
        logInfo("Stopped");
    }

    public void registerEvents()
    {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvent(Event.Type.PLUGIN_ENABLE, serverListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Event.Priority.Highest, this);
        pm.registerEvent(Event.Type.BLOCK_FROMTO, blockListener, Event.Priority.Highest, this);
        pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Highest, this);
    }

    public boolean onCommand(CommandSender _sender, Command _command, String _label, String[] _args)
    {
        Player player = (_sender instanceof Player) ? (Player) _sender : null;

        if (_label.equals("wrath"))
        {
            // We need at least two params to successfully execute a command.
            if (_args.length >= 2)
            {
                String commandName = _args[0];
                String playerName = _args[1];
                ArrayList<String> flags = new ArrayList<String>();

                // See if we have any "special" flags.
                for (int i = 2; i < _args.length; i++)
                {
                    flags.add(_args[i]);
                }

                // Check the args against the system and execute the target if we can.
                Executioner executioner = ExecutionManager.createExecutioner(commandName);

                if (executioner != null)
                {
                    Player target = findPlayer(playerName);
                    
                    if (playerName.equals("."))
                    {
                        target = player;
                        playerName = (player != null) ? player.getName() : "Console";
                    }
                    
                    if (target != null)
                    {
                        if (executioner.hasPermission(player))
                        {
                            ExecutionManager.add(executioner, player, target, flags);

                            communicationManager.message(player, "Executed the '" + commandName + "' command on '" + playerName + "'");
                        } else
                            communicationManager.error(player, "You don't have permission to use the '" + commandName + "' command.");
                    } else
                        communicationManager.error(player, "Could not find the player '" + playerName + "'.");
                } else
                    communicationManager.error(player, "Could not execute the command '" + commandName + "'.");
            }
            // If we don't supply enough arguments, show the help.
            else
            {
                if (player != null)
                {
                    if (permissionManager.hasExplodePermission(player))
                        communicationManager.command(player, "/wrath explode <player>", "Make a player explode");
                    
                    if (permissionManager.hasStrikePermission(player))
                        communicationManager.command(player, "/wrath strike <player>", "Kill a player with lightning");
                }
            }

            return true;
        }

        return false;
    }

    public Player findPlayer(String _name)
    {
        for (World world : getServer().getWorlds())
        {
            for (Player player : world.getPlayers())
            {
                if (player.getName().equalsIgnoreCase(_name))
                    return player;
            }
        }

        return null;
    }

    public ConfigManager getConfig()
    {
        return configManager;
    }

    public PermissionManager getPermissions()
    {
        return permissionManager;
    }

    public CommunicationManager getCommunicationManager()
    {
        return communicationManager;
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