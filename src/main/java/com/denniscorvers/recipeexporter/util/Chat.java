package com.denniscorvers.recipeexporter.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class Chat {
    private static TextComponentString getHeader() {
        TextComponentString header = new TextComponentString("[");
        TextComponentString txt = new TextComponentString("Recipe Exporter");
        txt.getStyle().setColor(TextFormatting.AQUA);
        header.appendSibling(txt);
        header.appendSibling(new TextComponentString("] "));

        return header;
    }

    public static void addMessage(EntityPlayer player, String message, boolean includeHeader) {
        TextComponentString cmp = new TextComponentString(message);
        if (includeHeader) {
            player.sendMessage(getHeader().appendSibling(cmp));
        } else {
            player.sendMessage(cmp);
        }
    }

    public static void addSystemMessage(String text) {
        addMessage(Minecraft.getMinecraft().player, text, true);
    }
}
