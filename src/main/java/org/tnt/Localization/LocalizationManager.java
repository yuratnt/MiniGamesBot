package org.tnt.Localization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LocalizationManager {


    private String language = "EN";
    private String command = "test";


    public LocalizationManager setLanguage(String language) {
        this.language = language;
        return this;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public String getDescriptionCommand() {
        String[] path = new String[] {
                command
        };
        return getData(path);

    }
    public String getSubcommandDescription(String subcommand) {
        String[] path = new String[] {
                command,
                "subcommands",
                subcommand
        };
        return getData(path);
    }

    public String getSubcommandOption(String subcommand, String option) {
        String[] path = new String[] {
                command,
                "subcommands",
                subcommand,
                "options"
        };
        return getData(path, option);
    }

    public String getCommandOption(String option) {
        String[] path = new String[] {
                command,
                "options"
        };
        return getData(path, option);
    }
    public String getCommandOptionChoice(String option, String choice) {
        String[] path = new String[] {
                command,
                "options",
                option,
                "choices"
        };
        return getData(path, choice);
    }

    public String getSubCommandOptionChoice(String subcommand, String option, String choice) {
        String[] path = new String[] {
                command,
                "subcommands",
                subcommand,
                "options",
                option,
                "choices"
        };
        return getData(path, choice);
    }

    private String getData(String[] path) {
        return dataManager(path, "description");
    }
    private String getData(String[]  path, String key) {
        return dataManager(path, key);
    }

    private String dataManager(String[] path, String key) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode config = mapper.readTree(new File("src/main/resources/Localization/" + language + "/Command.json"));
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
