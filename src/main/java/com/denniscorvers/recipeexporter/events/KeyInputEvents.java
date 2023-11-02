package com.denniscorvers.recipeexporter.events;

import com.denniscorvers.recipeexporter.ModRecipeExporter;
import com.denniscorvers.recipeexporter.gui.GuiMain;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModRecipeExporter.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyInputEvents {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world == null)
            return;

        onKeyPressed(mc, event);
    }

    private static void onKeyPressed(Minecraft mc, InputEvent.KeyInputEvent event) {
        if (mc.currentScreen == null && Keybindings.ExportKey.isPressed()) {
            mc.displayGuiScreen(GuiMain.getInstance());
        }
    }
}
