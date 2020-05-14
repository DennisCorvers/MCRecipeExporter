package com.denniscorvers.recipeexporter.util;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MyFile {
    public static String formatExportPath(String root) {
        String dateTime = ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("uuuu-MM-dd--HH-mm"));
        return root + "\\" + dateTime + ".json";
    }

    public static File getSaveFile(String path) {
        File file = new File(path);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return file;
    }
}
