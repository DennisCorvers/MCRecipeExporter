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
    private String m_name;
    /**
     * Mod ID
     */
    @SerializedName("Mod")
    private int m_modID;

    public MyItem() {

    }

    public MyItem(int amount, String displayName, int modID) {
        this.m_amount = amount;
        this.m_name = displayName;
        this.m_modID = modID;
    }

    @Override
    public int getAmount() {
        return m_amount;
    }

    @Override
    public void setAmount(int amount) {
        this.m_amount = amount;
    }

    @Override
    public String getName() {
        return m_name;
    }

    @Override
    public void setName(String name) {
        this.m_name = name;
    }

    @Override
    public int getModID() {
        return m_modID;
    }

    @Override
    public void setModID(int modID) {
        this.m_modID = modID;
    }

    @Override
    public String toString() {
        return m_name;
    }
}
