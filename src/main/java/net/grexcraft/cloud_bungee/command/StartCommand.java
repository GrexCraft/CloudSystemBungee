package net.grexcraft.cloud_bungee.command;

import net.grexcraft.cloud_bungee.client.CloudWebClient;
import net.grexcraft.cloud_bungee.helper.MessageHelper;
import net.grexcraft.cloud_bungee.model.CreateServerRequest;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StartCommand extends Command {
    public StartCommand() {
        super("cloudstart", "grexcraft.cloud.start");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if (strings.length < 2) {
            throw new IllegalArgumentException("missing arguments");
        }
        String image = strings[0];
        String tag = strings[1];

        ProxiedPlayer player = null;
        if ((commandSender instanceof ProxiedPlayer p)) {
            player = p;
        }


        if (player != null) {
            player.sendMessage(
                    MessageHelper.getPrefix()
                            .append("Starting server with image: ").color(ChatColor.GRAY)
                            .append(image + ":" + tag).color(ChatColor.YELLOW).create()
            );
        }


        CreateServerRequest request = new CreateServerRequest(image, tag);
        String serverName = CloudWebClient.createServer(request);

        if (player != null) {
            player.sendMessage(
                    MessageHelper.getPrefix()
                            .append("Server ").color(ChatColor.GRAY)
                            .append(serverName).color(ChatColor.YELLOW)
                            .append(" is starting").color(ChatColor.GRAY).create()
            );
        }
    }
}
