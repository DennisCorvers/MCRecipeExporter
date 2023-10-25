package com.denniscorvers.recipeexporter.recipes;

import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import com.denniscorvers.recipeexporter.recipes.items.IMyItem;
import com.denniscorvers.recipeexporter.recipes.items.MyItem;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class OutputData {
    private transient final String RecipeCount;

    @SerializedName("Recipes")
    private final List<IMyRecipe> Recipes;

    @SerializedName("Mods")
    private final Map<Integer, String> ModList;

    @SerializedName("Items")
    private final Map<Integer, IMyItem> ItemList;

    public OutputData(List<IMyRecipe> recipes, Map<Integer, String> modList, Map<Integer, IMyItem> itemList) {
        Recipes = recipes;
        ModList = modList;
        ItemList = itemList;

        RecipeCount = getCount(Recipes);
    }

    public String getRecipeCount() {
        return RecipeCount;
    }

    private <T> String getCount(Collection<T> collection) {
        return String.format("%,d", collection.size());
    }
}
