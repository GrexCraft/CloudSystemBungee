package net.grexcraft.cloud_bungee.command;

import com.google.common.collect.ImmutableSet;
import net.grexcraft.cloud_bungee.client.CloudWebClient;
import net.grexcraft.cloud_bungee.dto.ImageDto;
import net.grexcraft.cloud_bungee.helper.MessageHelper;
import net.grexcraft.cloud_bungee.model.CreateServerRequest;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StartCommand extends Command implements TabExecutor {

    public StartCommand() {
        super("cloudstart", "grexcraft.cloud.start");
        CloudWebClient.fetchImages();
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

    @Override
    public Iterable<String> onTabComplete(CommandSender commandSender, String[] args) {
        if ( args.length > 2 || args.length == 0 )
        {
            return ImmutableSet.of();
        }

        List<ImageDto> images = CloudWebClient.getImages();

        Set<String> matches = new HashSet<>();
        if ( args.length == 1 )
        {
            String search = args[0].toLowerCase();
            for ( ImageDto image : images )
            {
                if ( image.getName().toLowerCase().startsWith( search ) )
                {
                    matches.add( image.getName() );
                }
            }
        } else
        {
            String search = args[1].toLowerCase();
            for ( ImageDto image : images )
            {
                if ( image.getName().equalsIgnoreCase(args[0]) && image.getTag().toLowerCase().startsWith( search ) )
                {
                    matches.add( image.getTag() );
                }
            }
        }
        return matches;
    }
}
