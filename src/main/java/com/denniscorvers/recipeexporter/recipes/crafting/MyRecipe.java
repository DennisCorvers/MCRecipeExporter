package com.denniscorvers.recipeexporter.recipes.crafting;

import com.denniscorvers.recipeexporter.recipes.items.IMyItem;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public abstract class MyRecipe implements IMyRecipe {
    /**
     * Collection of input items
     */
    @SerializedName("Input")
    private final ArrayList<IMyItem> i;
    /**
     * Output item
     */
    @SerializedName("Result")
    private IMyItem o;

    public MyRecipe() {
        i = new ArrayList<>();
    }

    @Override
    public ArrayList<IMyItem> getInput() {
        return i;
    }

    @Override
    public IMyItem getOutput() {
        return o;
    }

    @Override
    public void setOutput(IMyItem item) {
        o = item;
    }

    @Override
    public void addInput(IMyItem item) {
        i.add(item);
    }

    @Override
    public String toString() {
        return "Recipe for " + o.getName();
    }
}
