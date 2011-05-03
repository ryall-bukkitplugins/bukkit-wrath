package me.ryall.wrath.listeners;

import me.ryall.wrath.system.ExecutionManager;
import me.ryall.wrath.system.Sentence;
import me.ryall.wrath.system.executioners.Drown;

import org.bukkit.event.block.BlockFromToEvent;

public class BlockListener extends org.bukkit.event.block.BlockListener
{
    public void onBlockFromTo(BlockFromToEvent _event)
    {
        for (String playerName : ExecutionManager.getSentences().keySet())
        {
            Sentence sentence = ExecutionManager.getSentences().get(playerName);
            
            if (sentence.executioner instanceof Drown)
            {
                Drown drown = (Drown)sentence.executioner;
                
                if (_event.getBlock() == drown.getWaterBlock())
                    _event.setCancelled(true);
            }
        }
    }
}
