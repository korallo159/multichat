package koral.multichat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class chatAllClear extends Command {

    public void noPerms(CommandSender sender)
    {
        sender.sendMessage(new TextComponent(ChatColor.RED + "Nie masz uprawnien aby to wykonac."));
    }



    public chatAllClear() {
        super("chatallclear", "Multichat.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(sender instanceof ProxiedPlayer && sender.hasPermission("Multichat.admin"))
        {
            ProxiedPlayer commanduser = (ProxiedPlayer) sender;
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                for( int i = 0; i<200; i++)
                {

                    player.sendMessage(new TextComponent(" "));
                }
                player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7[&cGlobal&7] " + "&7Czat został globalnie wyczyszczony przez" + "&4&l " + commanduser.getDisplayName())));
            }


        }
        else
            noPerms(sender);
    }
}