package net.grexcraft.cloud;

import net.grexcraft.cloud.command.RegisterCommand;
import net.grexcraft.cloud.command.RemoveCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class CloudSystemPlugin extends Plugin {

    private ServerManager serverManager;

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @Override
    public void onEnable() {
        super.onEnable();

        this.serverManager = new ServerManager();

        initCommands();

        System.out.println("GrexCraft CloudSystemPlugin started!");
    }

    private void initCommands() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new RegisterCommand(serverManager));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new RemoveCommand(serverManager));
    }
}