package hu.minehun.bungeecore.commands;

import hu.minehun.bungeecore.BungeeCore;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

public class LobbyCommand extends Command {
    private static final String permission = BungeeCore.getInstance().getConfiguration().getString("permission");

    public LobbyCommand() {
        super("lobby", permission,"hub");
        ProxyServer.getInstance().getPluginManager().registerCommand((Plugin) BungeeCore.getInstance(), this);
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BungeeCore.getInstance().getConfiguration().getString("onlyplayers")));
            return;
        }

        if (args.length == 0);
            ProxiedPlayer player = (ProxiedPlayer)sender;
            ServerInfo server = ProxyServer.getInstance().getServerInfo(ChatColor.translateAlternateColorCodes('&', BungeeCore.getInstance().getConfiguration().getString("server")));

            if (server.getName().equalsIgnoreCase(player.getServer().getInfo().getName())) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', BungeeCore.getInstance().getConfiguration().getString("joinedalready")));
                return;
            }
            player.connect(server);
        }
    }