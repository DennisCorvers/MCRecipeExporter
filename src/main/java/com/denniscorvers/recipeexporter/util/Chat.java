package com.denniscorvers.recipeexporter.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.StringTextComponent;

public class Chat {
    private static StringTextComponent getHeader() {
        StringTextComponent header = new StringTextComponent("[");
        StringTextComponent txt = new StringTextComponent("Recipe Exporter");
        txt.getStyle().setColor(Color.fromHex("#00FFFF"));
        StringTextComponent end = new StringTextComponent("] ");

        header.appendSibling(txt).appendSibling(end);
        return header;
    }

    public static void init() {

    }

    public static void addMessage(PlayerEntity player, String message, boolean includeHeader) {
        StringTextComponent stc = new StringTextComponent(message);
        if (includeHeader) {
            player.sendMessage(getHeader().appendSibling(stc), null);
        } else {
            player.sendMessage(stc, null);
        }
    }

    public static void addSystemMessage(String text) {
        addMessage(Minecraft.getInstance().player, text, true);
    }
}
