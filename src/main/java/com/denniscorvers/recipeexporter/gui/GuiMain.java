package com.denniscorvers.recipeexporter.gui;


import com.denniscorvers.recipeexporter.gui.graphics.Point;
import com.denniscorvers.recipeexporter.recipes.RecipeExporter;
import com.google.common.base.Preconditions;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class GuiMain extends Screen {

    private static GuiMain m_instance;

    public GuiMain() {
        super(new StringTextComponent("Recipe Exporter"));
    }

    public static Screen getInstance() {
        if (m_instance == null) {
            m_instance = new GuiMain();
        }

        return m_instance;
    }

    @Override
    protected void init() {
        super.init();

        Point loc = getMidPoint(-100, -10);
        Button exportButton = new Button(
                loc.x, loc.y, 200, 20,
                new StringTextComponent("Export Recipes"), x-> RecipeExporter.export());

        super.addButton(exportButton);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return super.mouseReleased(mouseX, mouseY, button);
    }

    private Point getMidPoint(int xOffset, int yOffset) {
        return new Point(super.width / 2 + xOffset, super.height / 2 + yOffset);
    }
}
