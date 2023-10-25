package com.denniscorvers.recipeexporter.recipes.crafting;

import com.denniscorvers.recipeexporter.recipes.items.IMyItemStack;

import java.util.ArrayList;

public interface IMyRecipe {
    ArrayList<IMyItemStack> getInput();

    IMyItemStack getOutput();

    void setOutput(IMyItemStack item);

    void addInput(IMyItemStack item);
}

