package com.denniscorvers.recipeexporter.gui;

import net.minecraft.client.gui.GuiButton;

import java.util.ArrayList;
import java.util.List;

public class MyButton extends GuiButton {

    private final List<ButtonClickEvent> listeners = new ArrayList<>();

    public MyButton(int id, int xPos, int yPos, int width, int height, String text) {
        super(id, xPos, yPos, width, height, text);
    }

    public void AddListener(ButtonClickEvent event) {
        listeners.add(event);
    }

    public void RemoveListener(ButtonClickEvent event) {
        listeners.remove(event);
    }

    public void Click() {
        for (ButtonClickEvent event : listeners) {
            event.OnButtonClicked(this);
        }
    }

    @FunctionalInterface
    public interface ButtonClickEvent {
        void OnButtonClicked(Object sender);
    }
}
