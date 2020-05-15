package com.denniscorvers.recipeexporter.recipes.exporters;

import com.denniscorvers.recipeexporter.recipes.items.MyItemStack;

import java.util.HashMap;

public abstract class ShapeExporter implements IRecipeExporter {

    protected final HashMap<MyItemStack, Integer> m_recipeCache;

    public ShapeExporter() {
        m_recipeCache = new HashMap<>(9);
    }
}
