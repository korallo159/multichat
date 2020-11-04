package koral.multichat;

import com.mojang.brigadier.exceptions.CommandExceptionType;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.security.Permission;

public final class Multichat extends Plugin implements Listener {




    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerCommand(this, new chatAllCommand());
        getProxy().getPluginManager().registerCommand(this, new chatAllClear());
        getProxy().getPluginManager().registerCommand(this, new chatGlobalCommand());
        getProxy().getPluginManager().registerCommand(this, new chatFreeze());
        //
        getProxy().getPluginManager().registerListener(this, new chatFreeze());
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}

