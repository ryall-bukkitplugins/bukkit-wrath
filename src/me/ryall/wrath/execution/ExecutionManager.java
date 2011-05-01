package me.ryall.wrath.execution;

import java.util.ArrayList;
import java.util.HashMap;

import me.ryall.wrath.Wrath;

import org.bukkit.entity.Player;

public class ExecutionManager
{
    private static HashMap<String, Sentence> sentences = new HashMap<String, Sentence>();
    
	public static Executioner createExecutioner(String _name)
	{
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
        
        // This should be in a loop, eventually, to allow damage over time.
        _executioner.start(_target);
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
    
    public static void killed(Player _player)
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
            
            remove(_player);
        }
    }
    
	private static void remove(Player _target) 
	{
		Sentence sentence = sentences.get(_target.getName());
		
		if (sentence != null)
        {
			sentence.executioner.stop(_target);
			
			sentences.remove(_target.getName());
        }
	}
}
