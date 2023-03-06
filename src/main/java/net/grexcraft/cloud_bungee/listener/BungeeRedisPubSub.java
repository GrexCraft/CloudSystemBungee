package net.grexcraft.cloud_bungee.listener;

import net.grexcraft.cloud_bungee.ServerManager;
import net.grexcraft.cloud_bungee.model.RedisBungeeEventData;
import redis.clients.jedis.JedisPubSub;

import java.util.Objects;

public class BungeeRedisPubSub extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("Channel " + channel + " has sent a message : " + message );

        if (Objects.equals(channel, "cloud:bungee")) {
            RedisBungeeEventData data = RedisBungeeEventData.fromJson(message);

            switch (Objects.requireNonNull(data).getEventType()) {
                case REGISTER -> ServerManager.register(
                            data.getName(),
                            data.getHostname(),
                            data.getPort()
                    );
                case REMOVE -> ServerManager.remove(data.getName());
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
