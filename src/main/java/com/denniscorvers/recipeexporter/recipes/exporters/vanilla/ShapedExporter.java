package com.denniscorvers.recipeexporter.recipes.exporters.vanilla;

import com.denniscorvers.recipeexporter.recipes.exporters.Exporter;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.common.crafting.IShapedRecipe;

public class ShapedExporter extends Exporter {
    public ShapedExporter(boolean isActive) {
        super(isActive);
    }

    @Override
    public boolean canProcess(IRecipe recipe) {
        return recipe instanceof IShapedRecipe;
    }
}
