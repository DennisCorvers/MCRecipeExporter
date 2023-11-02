package com.denniscorvers.recipeexporter.recipes.exporters.oredictionary;

import com.denniscorvers.recipeexporter.recipes.exporters.Exporter;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ShapelessOreDictExporter extends Exporter {
    public ShapelessOreDictExporter(boolean isActive) {
        super(isActive);
    }

    @Override
    public boolean canProcess(IRecipe recipe) {
        return recipe instanceof ShapelessOreRecipe;
    }
}
