package net.grexcraft.cloud_bungee.listener;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class ProxyPingListener implements Listener {
    private final String motd = "          &7----[&6&lGrex&c&lCraft &81.8-1.19&7&7]----&r\\n          &7TTT, Skyblock, KitPvP and more...";
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPing(ProxyPingEvent e){
        ServerPing serverPing = e.getResponse();
        String message = replaceToMinecraftStringPlaceholders(motd);
        serverPing.setDescriptionComponent(new TextComponent(message));
        e.setResponse(serverPing);
    }

    private String replaceToMinecraftStringPlaceholders(String motd) {
        return motd
                .replace("&", "§")
                .replace("\\n", "\n");
    }

}
