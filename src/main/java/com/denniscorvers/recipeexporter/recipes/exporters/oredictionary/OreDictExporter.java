package com.denniscorvers.recipeexporter.recipes.exporters.oredictionary;

import com.denniscorvers.recipeexporter.recipes.exporters.Exporter;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;


public class OreDictExporter extends Exporter {
    public OreDictExporter(boolean isActive) {
        super(isActive);
    }

    @Override
    public boolean canProcess(IRecipe recipe) {
        return recipe instanceof ShapedOreRecipe;
    }
}
