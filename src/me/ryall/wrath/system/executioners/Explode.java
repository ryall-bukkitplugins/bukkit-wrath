package me.ryall.wrath.system.executioners;

import org.bukkit.entity.Player;

import me.ryall.wrath.Wrath;
import me.ryall.wrath.system.Executioner;

public class Explode extends Executioner
{
    int secondsRemaining;
    
    public boolean hasPermission(Player _player)
    {
        return Wrath.get().getPermissions().hasExplodePermission(_player);
    }

    public String getMessage()
    {
        return Wrath.get().getConfig().getExplodeMessage();
    }

    public void begin(Player _target)
    {
        secondsRemaining = 5;//Wrath.get().getConfig().getExplodeSteps();
        
        if (secondsRemaining > 0)
        {
            Wrath.get().getCommunicationManager().message(_target, "You have been set to explode after " + 
                    secondsRemaining + " " + Wrath.get().getCommunicationManager().pluralise(secondsRemaining, "second") + "!");
        }
    }
    
    public void update(Player _target)
    {
        if (--secondsRemaining > 0)
        {
            Wrath.get().getCommunicationManager().message(_target, "Exploding in " + 
                    secondsRemaining + " " + Wrath.get().getCommunicationManager().pluralise(secondsRemaining, "second") + "!");
        }
        else
        {
            Wrath.get().getCommunicationManager().message(_target, "You have exploded!");
            
            //Location location = _target.getLocation();
            
            //CraftWorld cw = (CraftWorld)_target.getWorld();
            //CraftPlayer cp = (CraftPlayer)_target;
            
            //Explosion explosion = new Explosion(cw.getHandle(), cp.getHandle(), location.getX(), location.getY(), location.getZ(), 3);
            //explosion.a();
            
            //cw.getHandle().a("explode", location.getX(), location.getY(), location.getZ(), 3, 3, 3);
            
            //Block block = _target.getWorld().getBlockAt(_target.getLocation());
            //block.setType(Material.TNT);
        
            _target.damage(_target.getHealth());
        }
    }

    public void move(Player _target)
    {
    }

    public void end(Player _target)
    {
    }
}
