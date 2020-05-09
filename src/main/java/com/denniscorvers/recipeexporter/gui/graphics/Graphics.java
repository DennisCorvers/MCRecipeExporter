package com.denniscorvers.recipeexporter.gui.graphics;


import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class Graphics {

    public static void drawRect(int x1, int y1, int x2, int y2, Colour colour) {
        //TODO: Draw background
    }

    public static void drawCenterText(FontRenderer renderer, String text, int xPos, int yPos, Colour colour) {
        drawText(renderer, text, xPos - renderer.getStringWidth(text) / 2, yPos, colour);
    }

    public static void drawText(FontRenderer renderer, String text, int xPos, int yPos, Colour colour) {
        renderer.drawString(text, xPos, yPos, colour.toARGB());
    }
}
