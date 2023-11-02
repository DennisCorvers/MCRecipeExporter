package com.denniscorvers.recipeexporter.recipes.items;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class MyIngredient {
    private final Ingredient m_ingredient;

    public MyIngredient(Ingredient ingredient) {
        if (ingredient.getMatchingStacks().length == 0)
            throw new IllegalArgumentException("Ingredient doesn't contain any item stacks.");

        m_ingredient = ingredient;
    }

    public Ingredient getIngredient() {
        return m_ingredient;
    }

    public ItemStack getFirstStack() {
        return m_ingredient.getMatchingStacks()[0];
    }

    // Equality comparer used by HashMap to check if input items can be added together.
    // The HashCode and Equals override determine if an input ingredient for a recipe is equal
    // and thus can be added together.
    //
    // First match on display name, then compare the actual item stack.
    // TODO: Include alternative items for this ingredient?
    @Override
    public int hashCode() {
        return getFirstStack().getDisplayName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyIngredient)) {
            return false;
        }

        MyIngredient myIngredient = (MyIngredient) obj;
        return myIngredient.getFirstStack().isItemEqualIgnoreDurability(this.getFirstStack());
    }
}
