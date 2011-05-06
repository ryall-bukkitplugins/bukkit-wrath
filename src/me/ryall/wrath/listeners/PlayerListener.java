package me.ryall.wrath.listeners;

import me.ryall.wrath.system.ExecutionManager;

import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener extends org.bukkit.event.player.PlayerListener
{
    public void onPlayerMove(PlayerMoveEvent _event)
    {
        ExecutionManager.onMove(_event.getPlayer());
    }
    
    public void onPlayerQuit(PlayerQuitEvent _event)
    {
        //ExecutionManager.onQuit(_event.getPlayer());
    }
}
