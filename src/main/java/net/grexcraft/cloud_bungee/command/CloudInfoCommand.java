package net.grexcraft.cloud_bungee.command;

import net.grexcraft.cloud.core.dto.ServerDto;
import net.grexcraft.cloud_bungee.client.CloudWebClient;
import net.grexcraft.cloud_bungee.helper.MessageHelper;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;

public class CloudInfoCommand extends Command {
    public CloudInfoCommand() {
        super("cloudinfo", "grexcraft.cloud.info");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer player) {
            if (strings.length != 1) {
                die("wrong syntax", player);
                return;
            }

            String serverName = strings[0];

            List<ServerDto> servers = CloudWebClient.getServers();
            ServerDto srv = null;
            for (ServerDto server: servers) {
                if (server.getName().equals(serverName)) {
                    srv = server;
                }
            }

            if (srv == null) {
                die("server not found", player);
                return;
            }

            String playerCount = "-";
            try {
                playerCount = "" + ProxyServer.getInstance().getServers().get(srv.getName()).getPlayers().size();
            } catch (Exception e) {
                e.printStackTrace();
            }

            player.sendMessage(
                    MessageHelper.getPrefix()
                            .append(" --==[ ").color(ChatColor.GRAY)
                            .append(srv.getName()).color(ChatColor.YELLOW)
                            .append(" ]==--\n").color(ChatColor.GRAY)
                            .append("  Image:   ").color(ChatColor.GRAY)
                            .append(srv.getImage().getName() + "\n").color(ChatColor.YELLOW)
                            .append("  Version: ").color(ChatColor.GRAY)
                            .append(srv.getImage().getTag() + "\n").color(ChatColor.YELLOW)
                            .append("  State:   ").color(ChatColor.GRAY)
                            .append(srv.getState().toString() + "\n").color(ChatColor.YELLOW)
                            .append("  Players: ").color(ChatColor.GRAY)
                            .append(playerCount + "\n").color(ChatColor.YELLOW)
                            .create()
            );
        }
    }

    private void die(String reason, ProxiedPlayer player) {
        player.sendMessage(
                MessageHelper.getPrefix().append(reason).color(ChatColor.RED).create()
        );
    }
}
