package com.denniscorvers.recipeexporter.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

import java.io.File;
import java.io.FileWriter;
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

    public static boolean tryCompress(File target, int compressionMethod, int compressionLevel) {

        String zipPath = target.getPath().replace(".json", ".zip");
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(new File(zipPath));
        } catch (ZipException e) {
            e.printStackTrace();
            return false;
        }
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionMethod(compressionMethod);
        zipParameters.setCompressionLevel(compressionLevel);

        try {
            zipFile.addFile(target, zipParameters);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean trySaveJson(File target, String json) {
        try {
            FileWriter writer = new FileWriter(target);
            writer.write(json);
            writer.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
