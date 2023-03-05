package net.grexcraft.cloud_bungee.command;

import net.grexcraft.cloud_bungee.ServerManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class RemoveCommand extends Command {

    ServerManager serverManager;

    public RemoveCommand(ServerManager serverManager) {
        super("cloudremove");
        this.serverManager = serverManager;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        System.out.println("remove command");

        String name = strings[0];

        if ((commandSender instanceof ProxiedPlayer player)) {
            player.sendMessage(new ComponentBuilder("Removing Server: '" + name + "'").color(ChatColor.GOLD).create());
        }

        serverManager.remove(name);
    }
}
