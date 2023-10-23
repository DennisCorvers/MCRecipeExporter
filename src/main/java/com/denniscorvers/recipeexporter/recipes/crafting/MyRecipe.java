package com.denniscorvers.recipeexporter.recipes.crafting;

import com.denniscorvers.recipeexporter.recipes.items.IMyItem;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyRecipe implements IMyRecipe {
    /**
     * Collection of input items
     */
    @SerializedName("In")
    private final ArrayList<IMyItem> m_input;
    /**
     * Output item
     */
    @SerializedName("Out")
    private IMyItem m_output;

    public MyRecipe() {
        m_input = new ArrayList<>();
    }

    @Override
    public ArrayList<IMyItem> getInput() {
        return m_input;
    }

    @Override
    public IMyItem getOutput() {
        return m_output;
    }

    @Override
    public void setOutput(IMyItem item) {
        m_output = item;
    }

    @Override
    public void addInput(IMyItem item) {
        m_input.add(item);
    }
}
