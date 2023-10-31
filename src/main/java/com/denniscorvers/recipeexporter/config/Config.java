package com.denniscorvers.recipeexporter.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static final Vars VARS = new Vars();
    public static final ForgeConfigSpec CLIENT_CONFIG = CLIENT_BUILDER.build();

    public static void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CLIENT_CONFIG);
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    public static final class Vars {
        public final ForgeConfigSpec.ConfigValue<String> exportName;
        public final ForgeConfigSpec.ConfigValue<String> exportPath;

        public final ForgeConfigSpec.BooleanValue includeShaped;
        public final ForgeConfigSpec.BooleanValue includeShapeless;
        public final ForgeConfigSpec.BooleanValue includeSingleItem;
        public final ForgeConfigSpec.BooleanValue includeSmithing;
        public final ForgeConfigSpec.BooleanValue includeMiscItems;

        private Vars() {
            CLIENT_BUILDER.push("Recipe Exporter");
            CLIENT_BUILDER.comment("File settings:");
            exportName = CLIENT_BUILDER
                    .comment("Export file name. Leave empty for datetime stamped file name.")
                    .define("File name", "");

            exportPath = CLIENT_BUILDER
                    .comment("Default export directory. Leave empty to output in current directory.")
                    .define("Export directory", "");

            CLIENT_BUILDER.comment("Exporter settings:");
            includeShaped = CLIENT_BUILDER
                    .comment("True to export all shaped recipes.")
                    .define("includeShaped", true);

            includeShapeless = CLIENT_BUILDER
                    .comment("True to export all shapeless recipes.")
                    .define("includeShapeless", true);

            includeSingleItem = CLIENT_BUILDER
                    .comment("True to export all machine related recipes (like the stonecutter).")
                    .define("includeSingleItem", true);

            includeSmithing = CLIENT_BUILDER
                    .comment("True to export anvil/smithing related recipes.")
                    .define("includeSmithing", false);

            includeMiscItems = CLIENT_BUILDER
                    .comment("True to export all recipes not covered by previous options.")
                    .define("includeMiscItems", true);

            CLIENT_BUILDER.pop();
        }
    }
}
