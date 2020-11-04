package koral.multichat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class chatGlobalCommand extends Command implements TabExecutor {
    HashMap<ProxiedPlayer, Long> cooldown = new HashMap<>();



    public chatGlobalCommand()
    {
        super("globalmsg", "multichat.globalmsg");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
         ProxiedPlayer player = (ProxiedPlayer) sender;
        if(args.length <= 0) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Poprawne użycie: /globalmsg GRACZ <TRESC WIADOMOSCI>"));
          return;
        }
        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
         if(target == null)
             sender.sendMessage(new TextComponent(ChatColor.RED + "Taki gracz nie jest aktualnie online w sieci!"));
          else if(target == sender)
             sender.sendMessage(new TextComponent(ChatColor.RED + "Nie możesz pisać sam ze sobą!"));
        else if(sender instanceof ProxiedPlayer && cooldown.get(player) == null)   //jesli gracz na starcie nie ma cooldownu
         {
             sendMessage(sender, args);
         }
       else if(sender instanceof ProxiedPlayer && cooldown.get(player) + 15 <= (System.currentTimeMillis() / 1000))
        {
            sendMessage(sender, args);
        }
        else
            sender.sendMessage(new TextComponent(ChatColor.RED + "Nie możesz jeszcze wyslać prywatnej wiadomości globalnej przez " + ( cooldown.get(player) + 15 - System.currentTimeMillis() / 1000 )));
    }

    public void sendMessage(CommandSender sender, String[] args)
    {
        ProxiedPlayer player = (ProxiedPlayer) sender;
        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                String message = "";
                for (int i = 1; i < args.length; i++) {
                    message = message + args[i] + " ";
                }
                message = message.trim();
                target.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7[&cGlobalMSG&7] " + player.getDisplayName() + "&4---> &7Ty" + ": &7"  + message)));
                sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7[&cGlobalMSG&7] " +"&7Ty &4---> &7"+ target.getDisplayName() + ": &7"  + message)));
                cooldown.put(player, (System.currentTimeMillis() / 1000));


    }
    public Iterable<String> onTabComplete(final CommandSender sender, final String[] args) {
        final Set<String> match = new HashSet<>();
        if (args.length == 1) {
            final String search = args[0].toLowerCase();
            for (final ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                if (player.getName().toLowerCase().startsWith(search)) {
                    match.add(player.getName());
                }
            }
        }
        return match;
    }

}
