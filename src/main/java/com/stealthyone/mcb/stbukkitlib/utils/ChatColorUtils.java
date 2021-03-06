package com.stealthyone.mcb.stbukkitlib.utils;

import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for Bukkit's ChatColor class.
 */
public class ChatColorUtils {

    private ChatColorUtils() { }

    /**
     * Basically ChatColor.translateAlternateColorCodes on a string array.
     *
     * @param altColorChar Code to use.
     * @param arrayToTranslate String array to format.
     * @return Newly formatted string array.
     */
    public static String[] translateAlternateColorCodes(char altColorChar, String[] arrayToTranslate) {
        List<String> returnList = new ArrayList<>();
        for (String string : arrayToTranslate) {
            returnList.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        return returnList.toArray(new String[returnList.size()]);
    }

    /**
     * Replaces the color codes in ChatColor where found.
     *
     * @param string String to colorize.
     * @return Newly colorized string.
     */
    public static String colorizeString(String string) {
        Validate.notNull(string, "String cannot be null.");
        for (ChatColor val : ChatColor.values()) {
            switch (val) {
                case BOLD:
                case MAGIC:
                case ITALIC:
                case RESET:
                case STRIKETHROUGH:
                case UNDERLINE:
                    continue;

                default:
                    break;
            }
            string = ChatColorUtils.singleFormatString(string, val);
        }
        return string;
    }

    /**
     * Replaces the bold, italic, strikethrough, and underline effects where found.
     *
     * @param string String to format.
     * @return Newly formatted string.
     */
    public static String formatString(String string) {
        Validate.notNull(string, "String cannot be null.");
        for (ChatColor val : ChatColor.values()) {
            switch (val) {
                case BOLD:
                case MAGIC:
                case ITALIC:
                case RESET:
                case STRIKETHROUGH:
                case UNDERLINE:
                    break;

                default:
                    continue;
            }
            string = ChatColorUtils.singleFormatString(string, val);
        }
        return string;
    }

    /**
     * Replaces the 'magic' ChatColor in a string where found.
     *
     * @param string String to format.
     * @return Newly formatted string.
     */
    public static String magicfyString(String string) {
        Validate.notNull(string, "String cannot be null.");
        return ChatColorUtils.singleFormatString(string, ChatColor.MAGIC);
    }

    /**
     * Replaces the specified ChatColor in a given string.
     *
     * @param string String to format.
     * @param format ChatFormat to use.
     * @return The newly formatted string.
     */
    public static String singleFormatString(String string, ChatColor format) {
        Validate.notNull(string, "String cannot be null.");
        Validate.notNull(format, "Format cannot be null.");

        return string.replace("&" + format.getChar(), format.toString());
    }

}