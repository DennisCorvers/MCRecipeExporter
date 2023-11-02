package com.denniscorvers.recipeexporter.recipes.items;

import com.google.gson.annotations.SerializedName;

public class MyItemStack implements IMyItemStack {
    /**
     * amount
     */
    @SerializedName("Amount")
    private final int m_amount;
    /**
     * name
     */
    @SerializedName("ItemID")
    private final int m_itemID;

    public MyItemStack(int amount, int itemID) {
        this.m_amount = amount;
        this.m_itemID = itemID;
    }
}
