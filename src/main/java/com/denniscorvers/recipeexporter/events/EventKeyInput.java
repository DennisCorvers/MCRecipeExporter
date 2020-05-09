package com.denniscorvers.recipeexporter.events;

import com.denniscorvers.recipeexporter.gui.GuiMain;
import com.denniscorvers.recipeexporter.proxy.ClientProxy;
import com.denniscorvers.recipeexporter.util.Chat;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class EventKeyInput {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(InputEvent.KeyInputEvent event) {

        if (ClientProxy.keyStartExport != null && ClientProxy.keyStartExport.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(GuiMain.getInstance());
        }
    }
}
