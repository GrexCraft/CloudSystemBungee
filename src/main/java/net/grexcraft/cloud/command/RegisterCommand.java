package net.grexcraft.cloud.command;

import net.grexcraft.cloud.ServerManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class RegisterCommand extends Command {

    ServerManager serverManager;

    public RegisterCommand(ServerManager serverManager) {
        super("cloudregister");
        this.serverManager = serverManager;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        System.out.println("register command");

        String name = strings[0];
        String address = strings[1];
        int port = Integer.parseInt(strings[2]);

        if ((commandSender instanceof ProxiedPlayer player)) {
            player.sendMessage(new ComponentBuilder("Adding Server: '" + name + "' at '" + address + ":" + port + "'").color(ChatColor.GREEN).create());
        }

        serverManager.register(strings[0], strings[1], Integer.parseInt(strings[2]));
    }
}
