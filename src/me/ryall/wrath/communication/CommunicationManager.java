package me.ryall.wrath.communication;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.ryall.wrath.Wrath;

public class CommunicationManager
{
    public static String MESSAGE_HEADER = ChatColor.WHITE + "[" + ChatColor.GOLD + Wrath.PLUGIN_NAME + ChatColor.WHITE + "] " + ChatColor.WHITE;
    
    public void message(Player _player, String _message)
    {
        _player.sendMessage(MESSAGE_HEADER + _message);
    }
    
    public void error(Player _player, String _message)
    {
        _player.sendMessage(MESSAGE_HEADER + ChatColor.RED + "Error: " + _message);
    }

    public void command(Player _player, String _command, String _description)
    {
        _player.sendMessage(MESSAGE_HEADER + ChatColor.GOLD + _command + ChatColor.WHITE + ": " + _description);
    }
    
    public void broadcast(Player _except, String _message)
    {
    	for (World world : Wrath.get().getServer().getWorlds())
    	{
    		for (Player player : world.getPlayers())
    		{
    			if (_except == null || !_except.getName().equals(player.getName()))
    				message(player, _message);
    		}
    	}
    }
}
