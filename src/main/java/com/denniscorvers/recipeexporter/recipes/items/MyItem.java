package com.denniscorvers.recipeexporter.recipes.items;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class MyItem implements IMyItem {
    @SerializedName("Name")
    private String m_displayName;

    @SerializedName("ModID")
    private int m_modID;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        MyItem myItem = (MyItem) o;

        return m_modID == myItem.m_modID &&
                Objects.equals(m_displayName, myItem.m_displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_displayName, m_modID);
    }

    public MyItem(String displayName, int modID) {
        this.m_displayName = displayName;
        this.m_modID = modID;
    }

    public String getDisplayName() {
        return m_displayName;
    }

    public int getModID() {
        return m_modID;
    }


}
