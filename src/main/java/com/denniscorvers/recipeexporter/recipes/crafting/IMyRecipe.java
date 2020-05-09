package com.denniscorvers.recipeexporter.recipes.crafting;

import com.denniscorvers.recipeexporter.recipes.items.IMyItem;

import java.util.ArrayList;

public interface IMyRecipe {
    ArrayList<IMyItem> getInput();

    IMyItem getOutput();

    void setOutput(IMyItem item);

    void addInput(IMyItem item);
}

