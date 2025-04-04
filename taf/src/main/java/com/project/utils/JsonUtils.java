package com.project.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> readJson(String filePath) throws Exception {
        return objectMapper.readValue(new File(filePath), new TypeReference<Map<String, Object>>() {});
    }

    public static void writeJson(String filePath, Map<String, Object> data) throws Exception {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);
    }
}
