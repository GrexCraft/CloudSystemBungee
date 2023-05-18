package net.grexcraft.cloud_bungee.manager;

import net.grexcraft.cloud.core.dto.PoolSlotDto;
import net.grexcraft.cloud.core.dto.ServerDto;
import net.grexcraft.cloud.core.enums.ServerState;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.net.InetSocketAddress;
import java.util.List;

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

    public static void registerAllCurrentServers(List<ServerDto> servers) {
        for (ServerDto server : servers) {
            if (server.getState().equals(ServerState.STOPPED) || server.getState().equals(ServerState.STOPPING)) {
                continue;
            }

            if (server.getState().equals(ServerState.RUNNING)) {
                register(server.getName(), server.getAddress(), 0);
            }
        }
    }

    public static void registerAllCurrentSlots(List<PoolSlotDto> slots) {
        for (PoolSlotDto slot : slots) {
            if (slot.getServer() == null) continue;
            ServerDto server = slot.getServer();
            if (server.getState().equals(ServerState.STOPPED) || server.getState().equals(ServerState.STOPPING)) {
                continue;
            }

            if (server.getState().equals(ServerState.RUNNING)) {
                register(slot.getName(), server.getAddress(), 0);
            }
        }
    }
}
