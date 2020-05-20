package com.denniscorvers.recipeexporter.recipes.exporters.oredictionary;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ShapelessOreDictExporter extends OreDictExporter {
    @Override
    public boolean canProcess(IRecipe recipe) {
        return recipe instanceof ShapelessOreRecipe;
    }
}
