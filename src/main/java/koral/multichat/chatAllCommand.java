package koral.multichat;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

public class chatAllCommand extends Command {

public chatAllClear chatAllClear = new chatAllClear();

    public chatAllCommand() {
        super("chatall", "Multichat.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(sender instanceof ProxiedPlayer && sender.hasPermission("Multichat.admin"))
        {
            String message = "";
            for (int i = 0; i < args.length; i++) {
                message = message + args[i] + " ";
            }
            message = message.trim();
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7[&cGlobal&7] " + message)));
        }

    }
        else
            chatAllClear.noPerms(sender);
}
    }