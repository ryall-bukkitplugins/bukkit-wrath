package me.ryall.wrath.settings;

import org.bukkit.util.config.Configuration;
import me.ryall.wrath.Wrath;

public class ConfigManager
{
    private Configuration config;
    
    public void load() 
    {
        config = Wrath.get().getConfiguration();
        config.load();
    }
}
