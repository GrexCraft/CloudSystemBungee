package net.grexcraft.cloud_bungee;

import net.grexcraft.cloud_bungee.command.RegisterCommand;
import net.grexcraft.cloud_bungee.command.RemoveCommand;
import net.grexcraft.cloud_bungee.jedis.JedisManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class CloudSystemPlugin extends Plugin {

    @Override
    public void onEnable() {
        super.onEnable();

        initCommands();

        System.out.println("GrexCraft CloudSystemPlugin started!");

        JedisManager.start();
        System.out.println("after starting jedis");

    }

    private void initCommands() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new RegisterCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new RemoveCommand());
    }




}