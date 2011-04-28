package me.ryall.wrath.listeners;

import me.ryall.wrath.execution.ExecutionManager;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityListener extends org.bukkit.event.entity.EntityListener
{
    public void onEntityDeath(EntityDeathEvent event) 
    {
        if (event.getEntity() instanceof Player)
        {
            ExecutionManager.killed((Player)event.getEntity());
        }
    }
}
