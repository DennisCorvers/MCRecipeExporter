package com.denniscorvers.recipeexporter.proxy;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

    public static KeyBinding keyStartExport;

    @Override
    public void init() {
        registerKeys();

        super.init();
    }

    private void registerKeys() {
        keyStartExport = new KeyBinding("RecipeExporter UI", Keyboard.KEY_X, "Recipe Exporter");
        ClientRegistry.registerKeyBinding(ClientProxy.keyStartExport);
    }
}
