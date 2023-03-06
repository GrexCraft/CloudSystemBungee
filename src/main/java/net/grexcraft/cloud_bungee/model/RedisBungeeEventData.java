package net.grexcraft.cloud_bungee.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;

import java.io.Serializable;

@Data
public class RedisBungeeEventData implements Serializable {

    public enum BungeeEventType {
        REGISTER, REMOVE
    }

    private String name;
    private String hostname;
    private int port;
    private BungeeEventType eventType;

    public static String toJson(RedisBungeeEventData data) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static RedisBungeeEventData fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, RedisBungeeEventData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
