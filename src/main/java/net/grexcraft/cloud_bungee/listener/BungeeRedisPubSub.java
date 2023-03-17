package net.grexcraft.cloud_bungee.listener;

import net.grexcraft.cloud_bungee.helper.MessageHelper;
import net.grexcraft.cloud_bungee.manager.ServerManager;
import net.grexcraft.cloud_bungee.model.RedisBungeeEventData;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import redis.clients.jedis.JedisPubSub;

import java.util.Objects;

public class BungeeRedisPubSub extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("Channel " + channel + " has sent a message : " + message );

        if (Objects.equals(channel, "cloud:bungee")) {
            RedisBungeeEventData data = RedisBungeeEventData.fromJson(message);

            switch (Objects.requireNonNull(data).getEventType()) {
                case REGISTER -> {
                    ServerManager.register(
                            data.getName(),
                            data.getHostname(),
                            data.getPort()
                    );
                    for (ProxiedPlayer proxy : ProxyServer.getInstance().getPlayers()) {
                        if (proxy.hasPermission("grexcraft.cloud.messages")) {
                            proxy.sendMessage(
                                    MessageHelper.getPrefix()
                                            .append("Server ").color(ChatColor.GRAY)
                                            .append(data.getName()).color(ChatColor.YELLOW)
                                            .event(new HoverEvent(
                                                    HoverEvent.Action.SHOW_TEXT,
                                                    new ComponentBuilder("Join Server!").create()
                                            ))
                                            .event(new ClickEvent(
                                                    ClickEvent.Action.RUN_COMMAND,
                                                    "server " + data.getName()
                                            ))
                                            .append(" has started!").color(ChatColor.GRAY)
                                            .create()
                            );
                        }
                    }
                }
                case REMOVE -> {
                    ServerManager.remove(data.getName());
                    for (ProxiedPlayer proxy : ProxyServer.getInstance().getPlayers()) {
                        if (proxy.hasPermission("grexcraft.cloud.messages")) {
                            proxy.sendMessage(
                                    MessageHelper.getPrefix()
                                            .append("Server ").color(ChatColor.GRAY)
                                            .append(data.getName()).color(ChatColor.YELLOW)
                                            .append(" has stopped!").color(ChatColor.GRAY)
                                            .create()
                            );
                        }
                    }
                }
                default -> System.out.println("received unknown RedisBungeeEventData");
            }
        }
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("Client is Subscribed to channel : "+ channel);
        System.out.println("Client is Subscribed to "+ subscribedChannels + " no. of channels");
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("Client is Unsubscribed from channel : "+ channel);
        System.out.println("Client is Subscribed to "+ subscribedChannels + " no. of channels");
    }
}
