package me.ryall.wrath.settings;

import org.bukkit.util.config.Configuration;
import me.ryall.wrath.Wrath;

public class ConfigManager
{
    private Configuration config;
    
    public ConfigManager() 
    {
        config = Wrath.get().getConfiguration();
        config.load();
    }
    
    public String getStrikeMessage()
    {
        String message = config.getString("Messages.Strike", null);
    	return message == null || message.isEmpty() ? null : message;
    }
}
