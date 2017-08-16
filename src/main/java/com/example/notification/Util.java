package com.example.notification;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Util {
    public static String readResource(String resourceFile) {
        try (InputStreamReader reader = new InputStreamReader(Plugin.class.getResourceAsStream(resourceFile), Charsets.UTF_8)) {
            return CharStreams.toString(reader);
        } catch (IOException e) {
            throw new RuntimeException("Could not find resource " + resourceFile, e);
        }
    }

    public static Map<String, Object> buildField(String displayName, String defaultValue, String displayOrder, boolean required, boolean secure) {
        Map<String, Object> field = new HashMap<>();
        field.put("display-name", displayName);
        field.put("display-value", defaultValue);
        field.put("display-order", displayOrder);
        field.put("required", required);
        field.put("secure", secure);
        return field;
    }
}
