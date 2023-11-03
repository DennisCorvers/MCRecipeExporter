package com.denniscorvers.recipeexporter.recipes.items;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Objects;

public class MyItem implements IMyItem {
    @SerializedName("Name")
    private final String m_displayName;

    @SerializedName("ModID")
    private final int m_modID;

    // Item ID and Metadata used to differentiate items besides ModID and Item Name
    private transient final int m_id;
    private transient final int m_metaData;

    public MyItem(ItemStack stack, int modID) {
        Item item = stack.getItem();

        m_displayName = stack.getDisplayName();
        m_modID = modID;

        m_id = Item.getIdFromItem(item);
        m_metaData = item.getMetadata(stack);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        MyItem myItem = (MyItem) o;
        return m_modID == myItem.m_modID
                && m_id == myItem.m_id
                && m_metaData == myItem.m_metaData
                && Objects.equals(m_displayName, myItem.m_displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_modID, m_id, m_metaData, m_displayName);
    }
}
