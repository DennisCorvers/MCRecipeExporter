package com.denniscorvers.recipeexporter.recipes.items;

import com.google.gson.annotations.SerializedName;

public class MyOreDictItemStack extends MyItemStack {

    @SerializedName("OreDictNames")
    private String[] m_oreDictionaryNames;

    public MyOreDictItemStack(int amount, int itemID, String[] oreDictionaryNames) {
        super(amount, itemID);
        this.m_oreDictionaryNames = oreDictionaryNames;
    }
}
