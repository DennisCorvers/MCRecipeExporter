package com.denniscorvers.recipeexporter.recipes.items;

import com.google.gson.annotations.SerializedName;

public class MatchingItemStack extends MyItemStack {

    @SerializedName("AlternativeInputs")
    private final Integer[] m_alternativeItemIDs;

    public MatchingItemStack(int amount, int itemID, Integer[] alternativeItemIds) {
        super(amount, itemID);
        m_alternativeItemIDs = alternativeItemIds;
    }
}
