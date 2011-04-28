package me.ryall.wrath.execution;

import org.bukkit.entity.Player;

public abstract class Executioner
{
    public abstract boolean hasPermission(Player _player);
    
    public abstract String getMessage();

    public abstract void start(Player _target);
    
    public abstract void update(Player _target);
    
    public abstract void stop(Player _target);
}
