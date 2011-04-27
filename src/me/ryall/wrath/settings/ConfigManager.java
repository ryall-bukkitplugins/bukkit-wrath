package me.ryall.wrath.settings;

import org.bukkit.util.config.Configuration;
import me.ryall.wrath.Wrath;

public class ConfigManager
{
	private static String DEFAULT_MESSAGE = "%player% killed %victim% (%method%)";
    private Configuration config;
    
    public void load() 
    {
        config = Wrath.get().getConfiguration();
        config.load();
    }
    
    public boolean shouldBroadcast()
    {
        return config.getBoolean("Messages.Enable", true);
    }
    
    public String getStrikeMessage()
    {
    	return config.getString("Messages.Strike", DEFAULT_MESSAGE);
    }
}
