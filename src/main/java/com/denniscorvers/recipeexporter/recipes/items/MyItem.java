package com.denniscorvers.recipeexporter.recipes.items;

import com.denniscorvers.recipeexporter.serialization.JsonGetter;
import com.denniscorvers.recipeexporter.util.ItemNameFormatter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Objects;

public class MyItem implements IMyItem {
    private final String m_displayName;
    private final int m_modID;

    // Item ID and Metadata used to differentiate items besides ModID and Item Name
    private final int m_id;
    private final int m_metaData;

    public MyItem(ItemStack stack, int modID) {
        Item item = stack.getItem();

        m_displayName = stack.getDisplayName();
        m_modID = modID;

        m_id = Item.getIdFromItem(item);
        m_metaData = item.getMetadata(stack);
    }

    @JsonGetter("Name")
    public String getFormattedName() {
        return ItemNameFormatter.FormatName(m_displayName);
    }

    @JsonGetter("ModID")
    public int getModID() {
        return m_modID;
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
