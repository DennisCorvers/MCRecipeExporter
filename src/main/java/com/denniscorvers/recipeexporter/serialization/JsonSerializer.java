package com.denniscorvers.recipeexporter.serialization;

import com.denniscorvers.recipeexporter.recipes.items.IMyItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonSerializer implements IJsonSerializer {
    public JsonSerializer() {

    }

    private static Gson createSerializer() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IMyItem.class, new ItemSerializer());
        builder.disableHtmlEscaping();

        return builder.create();
    }

    public String serialize(Object data) {
        Gson gson = createSerializer();

        return gson.toJson(data);
    }

    public void serialize(Object data, File file) throws IOException {
        Gson gson = createSerializer();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

        writer.write(gson.toJson(data));
        writer.close();
    }
}
