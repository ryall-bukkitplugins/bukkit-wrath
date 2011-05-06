package me.ryall.wrath.system.executioners;

import me.ryall.wrath.Wrath;
import me.ryall.wrath.system.Executioner;

import org.bukkit.entity.Player;

public class Strike extends Executioner
{
    public boolean hasPermission(Player _player)
    {
        return Wrath.get().getPermissions().hasStrikePermission(_player);
    }

    public String getMessage()
    {
        return Wrath.get().getConfig().getStrikeMessage();
    }

    public void begin(Player _target)
    {
        _target.getWorld().strikeLightning(_target.getLocation());
        _target.damage(_target.getHealth());
    }

    public void update(Player _target)
    {
    }
    
    public void move(Player _target)
    {
    }

    public void end(Player _target)
    {
    }
}
