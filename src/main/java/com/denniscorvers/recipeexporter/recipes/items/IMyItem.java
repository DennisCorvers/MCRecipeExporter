package com.denniscorvers.recipeexporter.recipes.items;

public interface IMyItem {
    int getAmount();

    void setAmount(int a);

    String getName();

    void setName(String n);

    int getModID();

    void setModID(int m);
}
