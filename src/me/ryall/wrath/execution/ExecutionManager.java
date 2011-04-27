package me.ryall.wrath.execution;

import java.util.ArrayList;
import java.util.HashMap;

import me.ryall.wrath.Wrath;

import org.bukkit.entity.Player;

public class ExecutionManager
{
    private static HashMap<String, Executioner> executioners = new HashMap<String, Executioner>();
    private static HashMap<String, Sentence> sentences = new HashMap<String, Sentence>();
    
    static 
    {
        executioners.put("strike", new Strike());
    };
    
    public static Executioner getExecutioner(String _name)
    {
        _name = _name.toLowerCase();
        
        if (executioners.containsKey(_name))
            return executioners.get(_name);
        
        return null;
    }
    
    public static boolean isExecuting(Player _target)
    {
        return sentences.containsKey(_target.getName());
    }

    public static void execute(Executioner _executioner, Player _player, Player _target, ArrayList<String> _flags)
    {
        Sentence sentence = new Sentence();
        
        sentence.executioner = _executioner;
        sentence.player = _player;
        sentence.target = _target;
        sentence.flags = _flags;
        
        sentences.put(_target.getName(), sentence);
        
        // This should be in a loop, eventually, to allow damage over time.
        update();
    }
    
    public static void update()
    {
        for (String target : sentences.keySet())
        {
            Sentence sentence = sentences.get(target);
            sentence.executioner.update(sentence.target);
        }
    }
    
    public static void playerDied(Player _player)
    {
        Sentence sentence = sentences.get(_player.getName());
        
        if (sentence != null)
        {
            String message = sentence.executioner.getMessage();
            
            if (message != null && !sentence.flags.contains("-s"))
            {
                message = Wrath.get().getComms().parse(message, sentence.player, sentence.target);
                Wrath.get().getComms().broadcast(null, message);
            }
            
            sentences.remove(_player.getName());
        }
    }
}
