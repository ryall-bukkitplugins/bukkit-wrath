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

    public String getDrownMessage()
    {
        String message = config.getString("Drown.Message", null);
        return message == null || message.isEmpty() ? null : message;
    }
    
    public String getExplodeMessage()
    {
        String message = config.getString("Explode.Message", null);
        return message == null || message.isEmpty() ? null : message;
    }
    
    public int getExplodeSeconds()
    {
        return config.getInt("Explode.Seconds", 0);
    }
    
    public String getStrikeMessage()
    {
        String message = config.getString("Strike.Message", null);
        return message == null || message.isEmpty() ? null : message;
    }
}
