package me.ryall.wrath.execution;

import org.bukkit.entity.Player;

public abstract class Executioner
{
    public abstract boolean hasPermission(Player _player);
    
    public abstract String getMessage();

    public abstract void update(Player _target);
}
