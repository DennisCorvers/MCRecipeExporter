package com.denniscorvers.recipeexporter.gui;


import com.denniscorvers.recipeexporter.gui.graphics.Colour;
import com.denniscorvers.recipeexporter.gui.graphics.Graphics;
import com.denniscorvers.recipeexporter.gui.graphics.MyPoint;
import com.denniscorvers.recipeexporter.recipes.RecipeExporter;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;


@SideOnly(Side.CLIENT)
public class GuiMain extends GuiScreen {

    private static GuiMain m_instance;
    private MyButton buttonExport;

    private GuiMain() {
    }

    public static GuiScreen getInstance() {
        if (m_instance == null) {
            m_instance = new GuiMain();
        }

        return m_instance;
    }

    @Override
    public void initGui() {
        super.initGui();

        MyPoint loc = getMidPoint(-100, -10);

        buttonExport = new MyButton(100, loc.x, loc.y, 200, 20, "Export Recipes");
        buttonExport.AddListener(this::ButtonExportClick);
        addButton(buttonExport);
    }

    @Override
    public void drawScreen(int mx, int my, float partTicks) {
        drawDefaultBackground();

        Graphics.drawCenterText(fontRenderer,
                "Recipe Exporter",
                width / 2,
                30,
                Colour.WHITE);

        super.drawScreen(mx, my, partTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        if (button.id == buttonExport.id) {
            buttonExport.Click();
        }
    }

    private MyPoint getMidPoint() {
        return getMidPoint(0, 0);
    }

    private MyPoint getMidPoint(int xOffset, int yOffset) {
        return new MyPoint(width / 2 + xOffset, height / 2 + yOffset);
    }

    private void ButtonExportClick(Object sender) {
        RecipeExporter.Export();
    }
}
