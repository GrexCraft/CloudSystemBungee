package net.grexcraft.cloud_bungee;

import net.grexcraft.cloud_bungee.command.RegisterCommand;
import net.grexcraft.cloud_bungee.command.RemoveCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class CloudSystemPlugin extends Plugin {

    private ServerManager serverManager;

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