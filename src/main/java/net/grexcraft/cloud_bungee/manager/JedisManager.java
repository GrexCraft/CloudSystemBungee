package net.grexcraft.cloud_bungee.manager;

import net.grexcraft.cloud_bungee.listener.BungeeRedisPubSub;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisManager {
    public static final String CHANNEL_NAME = "cloud:bungee";

    private static final JedisManager instance = new JedisManager();

    private static final String hostname = "redis_redis";
    private static final int port = 6379;

    private JedisManager() {}


    public static JedisManager getInstance(){
        return instance;
    }
    public void start() {
        System.out.println("connecting to " + hostname + ":" + port);
        System.out.println("Starting Redis Pool Config Creation");
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        System.out.println(poolConfig.getJmxNamePrefix());
        System.out.println("Starting Redis Pool Creation");

        try (JedisPool jedisPool = new JedisPool(poolConfig, hostname, port, 0);) {
            System.out.println("Fetching resources");
            final Jedis subscriberJedis = jedisPool.getResource();
            System.out.println("Init pubsub");
            final BungeeRedisPubSub subscriber = new BungeeRedisPubSub();

            System.out.println("Creating Jedis Thread");

            new Thread(() -> {
                try {
                    System.out.println("Subscribing to \"commonChannel\". This thread will be blocked.");
                    subscriberJedis.subscribe(subscriber, CHANNEL_NAME);
                    System.out.println("Subscription ended.");
                } catch (Exception e) {
                    System.out.println("Subscribing failed.");
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
