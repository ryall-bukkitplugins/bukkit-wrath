package me.ryall.wrath.system;

import java.util.ArrayList;
import java.util.HashMap;
import me.ryall.wrath.Wrath;
import me.ryall.wrath.system.executioners.Explode;
import me.ryall.wrath.system.executioners.Strike;

import org.bukkit.entity.Player;

public class ExecutionManager
{
    private static HashMap<String, Sentence> sentences = new HashMap<String, Sentence>();

    public static HashMap<String, Sentence> getSentences()
    {
        return sentences;
    }
    
    public static Executioner createExecutioner(String _name)
    {
        if (_name.equalsIgnoreCase("explode"))
            return new Explode();
        
        //if (_name.equalsIgnoreCase("drown"))
        //    return new Drown();
        
        if (_name.equalsIgnoreCase("strike"))
            return new Strike();

        return null;
    }

    public static void add(Executioner _executioner, Player _player, Player _target, ArrayList<String> _flags)
    {
        // Only allow one instance.
        if (sentences.containsKey(_target.getName()))
            remove(_target);

        Sentence sentence = new Sentence();

        sentence.executioner = _executioner;
        sentence.player = _player;
        sentence.target = _target;
        sentence.flags = _flags;

        sentences.put(_target.getName(), sentence);

        _executioner.begin(_target);
    }
    
    public static void remove(Player _target)
    {
        Sentence sentence = sentences.get(_target.getName());

        if (sentence != null)
        {
            sentence.executioner.end(_target);

            sentences.remove(_target.getName());
        }
    }
    
    public static void kill(Player _target)
    {
        // Entity death is not called when using setHealth(0) (this causes a client crash).
        //executeDeathEvent(_target);
        
        // TODO: Interface with scavenger directly until Bukkit is more reliable with its events.
        
        // Finally kill the player, without damaging their armour.
        _target.damage(_target.getHealth());
    }

    public static void onUpdate()
    {
        for (String target : sentences.keySet())
        {
            Sentence sentence = sentences.get(target);
            sentence.executioner.update(sentence.target);
        }
    }
    
    public static void onMove(Player _player)
    {
        Sentence sentence = sentences.get(_player.getName());

        if (sentence != null)
        {
            sentence.executioner.move(_player);
        }
    }

    public static void onDeath(Player _player)
    {
        Sentence sentence = sentences.get(_player.getName());

        if (sentence != null)
        {
            String message = sentence.executioner.getMessage();

            if (message != null && !sentence.flags.contains("-s"))
            {
                message = Wrath.get().getCommunicationManager().parse(message, sentence.player, sentence.target);
                Wrath.get().getCommunicationManager().broadcast(null, message);
            }

            remove(_player);
        }
    }
    
    /*private static void executeDeathEvent(Player _target)
    {
        List<ItemStack> drops = new ArrayList<ItemStack>();
        
        drops.addAll(Arrays.asList(_target.getInventory().getContents()));
        drops.addAll(Arrays.asList(_target.getInventory().getArmorContents()));
        
        EntityDeathEvent deathEvent = new EntityDeathEvent(_target, drops);
        Wrath.get().getServer().getPluginManager().callEvent(deathEvent);
        
        for (ItemStack drop : deathEvent.getDrops())
        {
            _target.getWorld().dropItemNaturally(_target.getLocation(), drop);
        }
    }*/
}
