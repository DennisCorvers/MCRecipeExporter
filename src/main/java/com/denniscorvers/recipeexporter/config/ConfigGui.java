package com.denniscorvers.recipeexporter.config;

import com.denniscorvers.recipeexporter.ModRecipeExporter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

import java.util.Set;

public class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, Config.getConfigElements(), ModRecipeExporter.MODID,
                false, false, ModRecipeExporter.MODNAME);
    }

    public static class Factory implements IModGuiFactory {

        @Override
        public void initialize(Minecraft minecraft) {
        }

        @Override
        public boolean hasConfigGui() {
            return true;
        }

        @Override
        public GuiScreen createConfigGui(GuiScreen parentScreen) {
            return new ConfigGui(parentScreen);
        }

        @Override
        public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
            return null;
        }
    }
}
