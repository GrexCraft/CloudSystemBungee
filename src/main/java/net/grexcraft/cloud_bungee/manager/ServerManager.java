package net.grexcraft.cloud_bungee.manager;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.net.InetSocketAddress;

public class ServerManager {

    public static void register(String name, String ip, int port) {
        if (port == 0) port = 25565;
        InetSocketAddress address = new InetSocketAddress(ip, port);

        ServerInfo info = ProxyServer.getInstance().constructServerInfo(name, address, "server in cloud", false);
        ProxyServer.getInstance().getServers().put(name, info);

        System.out.println("[CloudSystem] Added server " + name + " with ip " + ip + ":" + port);
    }

    public static void remove(String name) {
        ProxyServer.getInstance().getServers().remove(name);
        System.out.println("[CloudSystem] Removed server " + name);

    }
}
