package com.denniscorvers.recipeexporter.recipes.crafting;

import com.denniscorvers.recipeexporter.recipes.items.IMyItemStack;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyRecipe implements IMyRecipe {
    /**
     * Collection of input items
     */
    @SerializedName("In")
    private final ArrayList<IMyItemStack> m_input;
    /**
     * Output item
     */
    @SerializedName("Out")
    private IMyItemStack m_output;

    public MyRecipe() {
        m_input = new ArrayList<>();
    }

    @Override
    public ArrayList<IMyItemStack> getInput() {
        return m_input;
    }

    @Override
    public IMyItemStack getOutput() {
        return m_output;
    }

    @Override
    public void setOutput(IMyItemStack item) {
        m_output = item;
    }

    @Override
    public void addInput(IMyItemStack item) {
        m_input.add(item);
    }
}
