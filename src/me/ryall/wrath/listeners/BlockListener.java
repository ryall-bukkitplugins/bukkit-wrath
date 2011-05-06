package me.ryall.wrath.listeners;

import org.bukkit.event.block.BlockFromToEvent;

public class BlockListener extends org.bukkit.event.block.BlockListener
{
    public void onBlockFromTo(BlockFromToEvent _event)
    {
        /*for (String playerName : ExecutionManager.getSentences().keySet())
        {
            Sentence sentence = ExecutionManager.getSentences().get(playerName);
            
            if (sentence.executioner instanceof Drown)
            {
                Drown drown = (Drown)sentence.executioner;
                
                if (_event.getBlock() == drown.getWaterBlock())
                    _event.setCancelled(true);
            }
        }*/
    }
}
