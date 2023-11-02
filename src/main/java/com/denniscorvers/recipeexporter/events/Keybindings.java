package com.denniscorvers.recipeexporter.events;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class Keybindings {
    public static KeyBinding ExportKey;

    public static void register() {
        ExportKey = new KeyBinding("RecipeExporter UI", Keyboard.KEY_X, "Recipe Exporter");
        ClientRegistry.registerKeyBinding(ExportKey);
    }
}
