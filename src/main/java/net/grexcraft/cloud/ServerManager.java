package net.grexcraft.cloud;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.net.InetSocketAddress;

public class ServerManager {

    public void register(String name, String ip, int port) {
        InetSocketAddress address = new InetSocketAddress(ip, port);

        ServerInfo info = ProxyServer.getInstance().constructServerInfo(name, address, "server in cloud", false);
        ProxyServer.getInstance().getServers().put(name, info);

        System.out.println("[CloudSystem] Added server " + name + " with ip " + ip + ":" + port);
    }

    public void remove(String name) {
        ProxyServer.getInstance().getServers().remove(name);
        System.out.println("[CloudSystem] Removed server " + name);

    }
}
