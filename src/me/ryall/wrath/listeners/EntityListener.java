package me.ryall.wrath.listeners;

import me.ryall.wrath.system.ExecutionManager;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityListener extends org.bukkit.event.entity.EntityListener
{
    public void onEntityDeath(EntityDeathEvent event)
    {
        if (event.getEntity() instanceof Player)
        {
            ExecutionManager.onDeath((Player) event.getEntity());
        }
    }
}
