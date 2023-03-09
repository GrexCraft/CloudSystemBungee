package net.grexcraft.cloud_bungee.helper;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;

public abstract class MessageHelper {

    public static ComponentBuilder getPrefix() {
        return new ComponentBuilder("[").color(ChatColor.GRAY)
                .append("Cloud").color(ChatColor.DARK_AQUA)
                .append("] ").color(ChatColor.GRAY);
    }
}
