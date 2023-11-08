package com.denniscorvers.recipeexporter.serialization;

import com.denniscorvers.recipeexporter.recipes.items.IMyItem;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ItemSerializer implements JsonSerializer<IMyItem> {

    @Override
    public JsonElement serialize(IMyItem src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonItem = new JsonObject();

        for (JsonGettersFactory.JsonGetterTargets target : JsonGettersFactory.getTargets(src)) {
            Object result = target.getJsonPropertyValue(src);
            jsonItem.addProperty(target.getJsonPropertyName(), result.toString());
        }

        return jsonItem;
    }
}
