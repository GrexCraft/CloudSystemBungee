package net.grexcraft.cloud_bungee;

import net.grexcraft.cloud.core.dto.ServerDto;
import net.grexcraft.cloud_bungee.client.CloudWebClient;
import net.grexcraft.cloud_bungee.command.RegisterCommand;
import net.grexcraft.cloud_bungee.command.RemoveCommand;
import net.grexcraft.cloud_bungee.command.StartCommand;
import net.grexcraft.cloud_bungee.command.UpdateImagesCommand;
import net.grexcraft.cloud_bungee.manager.JedisManager;
import net.grexcraft.cloud_bungee.manager.ServerManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.List;

public class CloudSystemPlugin extends Plugin {

    @Override
    public void onEnable() {
        super.onEnable();

        initCommands();

        System.out.println("GrexCraft CloudSystemBungee starting...");

        JedisManager.getInstance().start();
        //JedisManager.getInstance().subscribe();
        System.out.println("after starting jedis");

        List<ServerDto> servers = CloudWebClient.getServers();
        ServerManager.registerAllCurrentServers(servers);

    }

    private void initCommands() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new RegisterCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new RemoveCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new StartCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new UpdateImagesCommand());
    }
}