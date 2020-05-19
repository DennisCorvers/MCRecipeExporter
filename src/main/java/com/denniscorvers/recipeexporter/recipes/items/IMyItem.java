package com.denniscorvers.recipeexporter.recipes.items;

public interface IMyItem {
    int getAmount();

    void setAmount(int amount);

    String getName();

    void setName(String name);

    int getModID();

    void setModID(int modID);
}
