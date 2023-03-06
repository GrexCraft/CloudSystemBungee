package net.grexcraft.cloud_bungee.jedis;

import net.grexcraft.cloud_bungee.listener.BungeeRedisPubSub;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisManager {
    public static final String CHANNEL_NAME = "cloud:bungee";

    public JedisManager() {}

    public static void start() {

        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        final JedisPool jedisPool = new JedisPool(poolConfig, System.getenv("GC_CLOUD_REDIS_HOST"), Integer.parseInt(System.getenv("GC_CLOUD_REDIS_PORT")), 0);
        final Jedis subscriberJedis = jedisPool.getResource();
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
