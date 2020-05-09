package com.denniscorvers.recipeexporter.recipes.items;

import net.minecraft.item.ItemStack;

public class MyItem implements IMyItem {
    /**
     * amount
     */
    private int a;
    /**
     * name
     */
    private String n;
    /**
     * Mod ID
     */
    private int m;

    public MyItem() {

    }

    public MyItem(int amount, String displayName, int modID) {
        this.a = amount;
        this.n = displayName;
        this.m = modID;
    }

    @Override
    public int getAmount() {
        return a;
    }

    @Override
    public void setAmount(int a) {
        this.a = a;
    }

    @Override
    public String getName() {
        return n;
    }

    @Override
    public void setName(String n) {
        this.n = n;
    }

    @Override
    public int getModID() {
        return m;
    }

    @Override
    public void setModID(int m) {
        this.m = m;
    }

    @Override
    public String toString() {
        return n;
    }
}
