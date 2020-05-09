package com.denniscorvers.recipeexporter.recipes;

import com.denniscorvers.recipeexporter.recipes.exporters.ShapedExporter;
import com.denniscorvers.recipeexporter.util.Chat;

public class RecipeExporter {

    public static void Export() {
        ModResolver resolver = new ModResolver();

        Chat.addSystemMessage("Starting Export...");

        ShapedExporter sEx = new ShapedExporter();
        sEx.Export(resolver);
    }
}
