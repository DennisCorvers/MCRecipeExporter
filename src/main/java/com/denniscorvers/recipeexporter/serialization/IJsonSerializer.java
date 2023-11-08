package com.denniscorvers.recipeexporter.serialization;

import java.io.File;
import java.io.IOException;

public interface IJsonSerializer {
    String serialize(Object data);

    void serialize(Object data, File file) throws IOException;
}
