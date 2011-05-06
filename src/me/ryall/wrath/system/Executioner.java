package me.ryall.wrath.system;

import org.bukkit.entity.Player;

public abstract class Executioner
{
    public abstract boolean hasPermission(Player _player);

    public abstract String getMessage();

    public abstract void begin(Player _target);

    public abstract void move(Player _target);

    public abstract void end(Player _target);
}
