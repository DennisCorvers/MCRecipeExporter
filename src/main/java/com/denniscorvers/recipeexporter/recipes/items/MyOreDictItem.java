package com.denniscorvers.recipeexporter.recipes.items;

import com.google.gson.annotations.SerializedName;

public class MyOreDictItem extends MyItem {

    @SerializedName("OreDictNames")
    private String[] m_oreDictionaryNames;

    public String[] getOreDictionaryNames() {
        return m_oreDictionaryNames;
    }

    public void setOreDictionaryNames(String[] m_oreDictionaryNames) {
        this.m_oreDictionaryNames = m_oreDictionaryNames;
    }


}
