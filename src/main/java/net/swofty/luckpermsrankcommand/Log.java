package net.swofty.luckpermsrankcommand;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

public class Log
{
    public static void logger(final String... messages) {
        for (final String message : messages) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    public static void error(final String... messages) {
        for (final String message : messages) {
            logger("&4&lERROR: &4" + message);
        }
    }

    public static void info(final String... messages) {
        for (final String message : messages) {
            logger("&6&lINFO: &6" + message);
        }
    }

    public static void success(final String... messages) {
        for (final String message : messages) {
            logger("&a&lSUCCESS: &a" + message);
        }
    }

    public static void warn(final String... messages) {
        for (final String message : messages) {
            logger("&c&lWARNING: &c" + message);
        }
    }
}