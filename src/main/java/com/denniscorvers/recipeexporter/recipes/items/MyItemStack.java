package com.denniscorvers.recipeexporter.recipes.items;

import net.minecraft.item.ItemStack;

public final class MyItemStack {
    private final ItemStack m_stack;

    public MyItemStack(ItemStack stack) {
        m_stack = stack;
    }

    public ItemStack getStack() {
        return m_stack;
    }

    @Override
    public int hashCode() {
        return m_stack.getDisplayName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyItemStack)) {
            return false;
        }

        return m_stack.isItemEqualIgnoreDurability(((MyItemStack) obj).getStack());
    }
}
