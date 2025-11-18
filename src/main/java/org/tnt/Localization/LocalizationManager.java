package org.tnt.Localization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.intellij.lang.annotations.Language;

import java.io.File;
import java.io.IOException;

public class LocalizationManager {


    private final String language;
    private final String type;

    public LocalizationManager(String language, String type) {
        this.language = language;
        this.type = type;
    }

    public String getDescription(String command) {
        return getData(command, null ,"description");

    }
    public String getSubcommandDescription(String command, String subcommand) {
        String[] subcommandPath = new String[] {
                "subcommands",
                "description"
        };
        return getData(command, subcommandPath, subcommand);
    }

    public String getCommandOption(String command, String option) {
        String[] commandPath = new String[] {
                "options"
        };
        return getData(command, commandPath, option);
    }

    private String getData(String command, String[] path, String key) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode config = mapper.readTree(new File("src/main/resources/Localization/" + language + "/Command.json"));
            config = config.path(type).path(command);
            if (path != null) {
                for (String s : path) {
                    if (s != null && !s.isEmpty()) {
                        config = config.path(s);
                    }
                }
            }
            return config.get(key).asText();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
