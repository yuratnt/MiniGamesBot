package org.tnt.Localization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LocalizationManager {


    private final String language;
    private final String type;

    public LocalizationManager(String language, String type) {
        this.language = language;
        this.type = type;
    }
    public String getTitle(String command) {
        return getData(command, "title");
    }
    public String getDescription(String command) {
        return getData(command, "description");

    }

    public String getOption(String command, String option) {
        return getData(command, "option", option);
    }

    private String getData(String command,String key) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode config = mapper.readTree(new File("Localization/RU/Command.json"));
            return config.path(type).path(command).get(key).asText();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    private String getData(String command, String path, String key) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode config = mapper.readTree(new File("Localization/RU/Command.json"));
            return config.path(type).path(path).path(command).get(key).asText();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
