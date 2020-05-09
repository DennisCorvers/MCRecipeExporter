package com.denniscorvers.recipeexporter.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class Chat {

    private static final TextComponentString m_header = new TextComponentString("");

    private static void setupChatHeader() {
        m_header.appendSibling(new TextComponentString("["));
        TextComponentString txt = new TextComponentString("Recipe Exporter");
        txt.getStyle().setColor(TextFormatting.AQUA);
        m_header.appendSibling(txt);
        m_header.appendSibling(new TextComponentString("] "));
    }

    public static void init() {
        setupChatHeader();
    }

    public static void addMessage(EntityPlayer player, String message, boolean includeHeader) {
        TextComponentString cmp = new TextComponentString(message);
        if (includeHeader) {
            TextComponentString cpy = m_header.createCopy();
            cpy.appendSibling(cmp);
            cmp = cpy;
        }
        player.sendMessage(cmp);
    }

    public static void addChatMessage(EntityPlayer player, String message) {
        addMessage(player, message, false);
    }

    public static void addChatMessage(String text) {
        addMessage(Minecraft.getMinecraft().player, text, false);
    }

    public static void addSystemMessage(EntityPlayer player, String message) {
        addMessage(player, message, true);
    }

    public static void addSystemMessage(String text) {
        addMessage(Minecraft.getMinecraft().player, text, true);
    }
}
