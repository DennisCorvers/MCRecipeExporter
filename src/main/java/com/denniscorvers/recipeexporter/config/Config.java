package com.denniscorvers.recipeexporter.config;

import net.minecraftforge.common.config.Configuration;

public class Config {

    private static Configuration config;

    public static Configuration getConfig() {
        return config;
    }

    public static void init(Configuration c) {
        config = c;
        config.load();
    }

    public static void syncAllConfig() {
        config.save();
    }
}
