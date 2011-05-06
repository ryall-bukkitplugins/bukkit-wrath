package me.ryall.wrath.system.executioners;

import me.ryall.wrath.Wrath;
import me.ryall.wrath.system.Executioner;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class Drown extends Executioner
{
    //BlockInfo blockInfo = new BlockInfo();
    Location position;
    Block waterBlock;
    
    public boolean hasPermission(Player _player)
    {
        return Wrath.get().getPermissions().hasDrownPermission(_player);
    }

    public String getMessage()
    {
        return Wrath.get().getConfig().getDrownMessage();
    }

    public void begin(Player _target)
    {
        waterBlock = getHeadBlock(_target);
        
        if (waterBlock == null)
        {
            _target.damage(_target.getHealth());
            return;
        }
        
        /*blockInfo.material = waterBlock.getType();
        blockInfo.data = waterBlock.getData();
        blockInfo.position = waterBlock.getLocation();*/
        
        position = _target.getLocation();

        waterBlock.setType(Material.WATER);
    }

    public void move(Player _target)
    {
        _target.teleport(position);
        
        /*Block headBlock = getHeadBlock(_target);
        
        if (headBlock != null)
        {
            Location prev = waterBlock.getLocation();
            Location curr = headBlock.getLocation();
        
            if (prev.getBlockX() != curr.getBlockX() || prev.getBlockY() != curr.getBlockY() || prev.getBlockZ() != curr.getBlockZ())
            {
                waterBlock.setType(blockInfo.material);
                waterBlock.setData(blockInfo.data);
                
                waterBlock = headBlock;
                waterBlock.setType(Material.WATER);
                
            }
        }*/
        
    }

    public void end(Player _target)
    {
    }

    public Block getWaterBlock()
    {
        return waterBlock;
    }
    
    private Block getHeadBlock(Player _player)
    {
        return _player.getLocation().getBlock().getFace(BlockFace.UP);
    }
}
