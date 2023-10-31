package com.denniscorvers.recipeexporter.util;

import com.denniscorvers.recipeexporter.ModRecipeExporter;
import com.denniscorvers.recipeexporter.recipes.OutputData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class MyFile {
    private final File m_jsonFile;
    private final OutputData m_data;

    private MyFile(String fileName, String filePath, OutputData data) {
        fileName = isEmptyString(fileName) ? getFileName() : fileName;
        filePath = isEmptyString(filePath) ? getFilePath() : filePath;

        m_jsonFile = getSaveFile(filePath + "\\" + fileName);
        m_data = data;
    }

    public static MyFile CreateFile(String fileName, String filePath, OutputData data) {
        MyFile mf = new MyFile(fileName, filePath, data);

        return mf.m_jsonFile == null ? null : mf;
    }

    private static <C> boolean trySaveJson(File file, C data) {
        Gson gson = (new GsonBuilder()).serializeNulls().create();
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(gson.toJson(data));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            ModRecipeExporter.LOGGER.error(e.getMessage());
            return false;
        }

        return true;
    }

    private static boolean tryCompress(File file) {
        int compressionMethod = Zip4jConstants.COMP_DEFLATE;
        int compressionLevel = Zip4jConstants.DEFLATE_LEVEL_FASTEST;

        if (file == null)
            return false;

        String zipPath = file.getPath().replace(".json", ".zip");
        ZipFile zipFile;
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
            zipFile.addFile(file, zipParameters);
        } catch (Exception e) {
            e.printStackTrace();
            ModRecipeExporter.LOGGER.error(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean tryPersist() {
        return trySaveJson(m_jsonFile, m_data)
                && tryCompress(m_jsonFile);
    }

    public String getOutputPath() {
        return m_jsonFile.getAbsolutePath();
    }

    private File getSaveFile(String path) {
        File file = new File(path);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                ModRecipeExporter.LOGGER.error(e.getMessage());
                return null;
            }
        }

        return file;
    }

    private String getFilePath() {
        return new File("").getAbsolutePath() + "\\Recipe Exports";
    }

    private String getFileName() {
        String dateTime = ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("uuuu-MM-dd--HH-mm"));
        return dateTime + ".json";
    }

    private boolean isEmptyString(String string) {
        return string == null || string.isEmpty();
    }
}
