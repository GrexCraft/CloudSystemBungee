package net.grexcraft.cloud_bungee.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListCommand extends Command {

    public ListCommand() {
        super("list", "grexcraft.list");
    }

    @Override
    public void execute(CommandSender sender, String[] strings) {

        for (ServerInfo server : ProxyServer.getInstance().getServers().values()) {
            if (!server.canAccess(sender)) {
                continue;
            }

            Collection<ProxiedPlayer> serverPlayers = server.getPlayers();
            if (serverPlayers.isEmpty()) {
                continue;
            }

            List<String> players = new ArrayList<>();
            for (ProxiedPlayer player : serverPlayers) {
                players.add(player.getDisplayName());
            }
            Collections.sort(players, String.CASE_INSENSITIVE_ORDER);

            sender.sendMessage( ProxyServer.getInstance().getTranslation( "command_list", server.getName(), players.size(), String.join( ChatColor.RESET + ", ", players ) ) );
        }
        sender.sendMessage( ProxyServer.getInstance().getTranslation( "total_players", ProxyServer.getInstance().getOnlineCount() ) );
    }
}
