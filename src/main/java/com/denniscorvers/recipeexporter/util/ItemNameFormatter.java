package com.denniscorvers.recipeexporter.util;

public class ItemNameFormatter {
    private static final char ColourCodeCharacter = (char) 167;

    public static String FormatName(String name) {
        int index = IndexOf(name, ColourCodeCharacter);
        if (index < 0)
            return name;

        // At least one occurrence of a colour code. StringBuilder length therefor
        // is at least 2 shorter than the input string.
        StringBuilder sb = new StringBuilder(name.length() - 2);
        while (index < name.length()) {
            char current = name.charAt(index);
            // If colour code character is found, skip two characters in the input.
            if (current == ColourCodeCharacter) {
                index += 2;
                continue;
            }

            sb.append(current);
            index++;
        }

        return sb.toString();
    }

    private static int IndexOf(String str, char c) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c)
                return i;
        }

        return -1;
    }
}
