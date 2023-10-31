package com.denniscorvers.recipeexporter.recipes.exporters.vanilla;

import com.denniscorvers.recipeexporter.recipes.ItemStackCache;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import com.denniscorvers.recipeexporter.recipes.exporters.Exporter;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.SingleItemRecipe;

public class SingleItemExporter extends Exporter {
    public SingleItemExporter(boolean isActive) {
        super(isActive);
    }

    @Override
    public boolean canProcess(IRecipe<?> recipe) {
        return recipe instanceof SingleItemRecipe || recipe instanceof AbstractCookingRecipe;
    }
}
