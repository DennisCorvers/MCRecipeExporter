package com.denniscorvers.recipeexporter.events;

import com.denniscorvers.recipeexporter.ModRecipeExporter;
import com.denniscorvers.recipeexporter.gui.GuiMain;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = ModRecipeExporter.MODID, value = Side.CLIENT)
public class EventKeyInput {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
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
