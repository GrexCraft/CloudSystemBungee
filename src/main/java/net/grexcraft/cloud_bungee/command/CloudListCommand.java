package net.grexcraft.cloud_bungee.command;

import net.grexcraft.cloud.core.dto.ServerDto;
import net.grexcraft.cloud.core.enums.ServerState;
import net.grexcraft.cloud_bungee.client.CloudWebClient;
import net.grexcraft.cloud_bungee.helper.MessageHelper;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;

public class CloudListCommand extends Command {

    public CloudListCommand() {
        super("cloudlist", "grexcraft.cloud.list");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer player) {
            List<ServerDto> servers = CloudWebClient.getServers();

            ComponentBuilder builder = MessageHelper.getPrefix().append("Servers running:\n").color(ChatColor.DARK_AQUA);

            for (ServerDto server :
                    servers) {
                if (server.getState().equals(ServerState.STOPPED)) continue;
                ChatColor color = ChatColor.GRAY;
                switch (server.getState()) {
                    case RUNNING -> color = ChatColor.GREEN;
                    case STOPPING -> color = ChatColor.RED;
                    case STARTING -> color = ChatColor.YELLOW;
                }
                builder = builder
                        .append(" ⬤ ").color(color)
                        .append(server.getName() + "\n")
                        .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/cloudinfo " + server.getName()))
                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder().append("show details for ").color(ChatColor.GRAY).append(server.getName()).color(ChatColor.YELLOW).create()))
                        .color(ChatColor.GRAY);
            }

            player.sendMessage(
                    builder.create()
            );
        }
    }
}
