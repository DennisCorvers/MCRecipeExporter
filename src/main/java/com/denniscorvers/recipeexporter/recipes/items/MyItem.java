package com.denniscorvers.recipeexporter.recipes.items;

import com.google.gson.annotations.SerializedName;

public class MyItem implements IMyItem {
    /**
     * amount
     */
    @SerializedName("Amount")
    private int m_amount;
    /**
     * name
     */
    @SerializedName("Item")
    private int m_itemID;
    /**
     * Mod ID
     */
    @SerializedName("Mod")
    private int m_modID;

    public MyItem() {

    }

    @Override
    public void setAmount(int amount) {
        this.m_amount = amount;
    }

    @Override
    public void setItemID(int itemID) {
        this.m_itemID = itemID;
    }

    @Override
    public void setModID(int modID) {
        this.m_modID = modID;
    }
}
