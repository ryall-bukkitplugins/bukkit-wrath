package me.ryall.wrath.system.executioners;

import org.bukkit.entity.Player;

import me.ryall.wrath.Wrath;
import me.ryall.wrath.system.Executioner;

public class Explode extends Executioner
{
    int stepsRemaining;
    
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
        stepsRemaining = Wrath.get().getConfig().getExplodeSteps();
        
        if (stepsRemaining > 0)
        {
            Wrath.get().getCommunicationManager().message(_target, "You have been set to explode after " + 
                    stepsRemaining + " " + Wrath.get().getCommunicationManager().pluralise(stepsRemaining, "step") + "!");
        }
    }

    public void move(Player _target)
    {
        if (stepsRemaining > 0)
        {
            Wrath.get().getCommunicationManager().message(_target, "Careful! You'll explode after " + 
                    stepsRemaining + " " + Wrath.get().getCommunicationManager().pluralise(stepsRemaining, "step") + "!");
        }
    }

    public void end(Player _target)
    {
    }
}
