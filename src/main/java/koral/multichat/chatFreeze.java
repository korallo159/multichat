package koral.multichat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.w3c.dom.Text;

public class chatFreeze extends Command implements Listener {

    public chatFreeze() {
        super("chatallfreeze", "Multichat.admin");
    }
          public static boolean freeze = false;
    @Override
    public void execute(CommandSender sender, String[] strings) {
           ProxiedPlayer commanduser = (ProxiedPlayer) sender;
        if(sender instanceof ProxiedPlayer && sender.hasPermission("Multichat.admin")){
            if(!freeze) {
            freeze = true;
            for(ProxiedPlayer player: ProxyServer.getInstance().getPlayers()){
                player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7[&cGlobal&7] " + "&7Czat został globalnie zfreezowany przez" + "&4&l " + commanduser.getDisplayName())));
            }
            }
            else {
             freeze = false;
                for(ProxiedPlayer player: ProxyServer.getInstance().getPlayers()){
                    player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7[&cGlobal&7] " + "&7Czat został globalnie odfreezowany przez" + "&4&l " + commanduser.getDisplayName())));
                }
            }
        }

    }

    @EventHandler
    public void chatfreeze(ChatEvent e){
        ProxiedPlayer p = (ProxiedPlayer) e.getSender();
        if(freeze && !p.hasPermission("Multichat.admin")){
            p.sendMessage(new TextComponent(ChatColor.RED + "Czat został zfreezowany, nie możesz pisać"));
          e.setCancelled(true);
        }
    }
}
