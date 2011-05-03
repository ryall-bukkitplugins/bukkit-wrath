package me.ryall.wrath.system;

import me.ryall.wrath.Wrath;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class Drown extends Executioner
{
    BlockInfo blockInfo = new BlockInfo();
    Location location;
    
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
        location = _target.getLocation();
        
        Block block = location.getBlock().getFace(BlockFace.UP);
        
        if (block != null)
        {
            blockInfo.material = block.getType();
            blockInfo.data = block.getData();
            blockInfo.position = block.getLocation();
        }
        else
            blockInfo.material = null;
        
        block.setType(Material.WATER);
    }

    public void update(Player _target)
    {
        if (!_target.getLocation().equals(location))
        {
            
        }
    }

    public void end(Player _target)
    {
    }
}
