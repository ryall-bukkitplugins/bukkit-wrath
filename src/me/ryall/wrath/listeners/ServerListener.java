package me.ryall.wrath.listeners;

import me.ryall.wrath.Wrath;

import org.bukkit.event.server.PluginEnableEvent;

public class ServerListener extends org.bukkit.event.server.ServerListener
{
    public void onPluginEnable(PluginEnableEvent _event) 
    {
        Wrath.get().getPermissions().load();
    }
}
