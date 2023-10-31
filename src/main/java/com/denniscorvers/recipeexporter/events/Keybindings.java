package com.denniscorvers.recipeexporter.events;

import com.denniscorvers.recipeexporter.ModRecipeExporter;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.event.KeyEvent;

@OnlyIn(Dist.CLIENT)
public class Keybindings {
    public static KeyBinding ExportKey;

    public static void register(final FMLClientSetupEvent e) {
        ExportKey = create("export_recipes_key", KeyEvent.VK_NUMPAD5);
        ClientRegistry.registerKeyBinding(ExportKey);
    }

    private static KeyBinding create(String name, int key) {
        return new KeyBinding("key." + ModRecipeExporter.MODID + "." + name, key, "key.category." + ModRecipeExporter.MODID);
    }
}
