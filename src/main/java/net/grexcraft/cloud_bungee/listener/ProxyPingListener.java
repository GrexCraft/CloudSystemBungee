package net.grexcraft.cloud_bungee.listener;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPingListener implements Listener {
    @EventHandler
    public void onPing(ProxyPingEvent e){
        ServerPing serverPing = e.getResponse();
        serverPing.setDescription("          &7----[&6&lGrex&c&lCraft &81.8-1.19&7&7]----&r\\n          &7TTT, Skyblock, KitPvP and more...");
        e.setResponse(serverPing);
    }
}
