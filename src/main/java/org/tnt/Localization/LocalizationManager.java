package org.tnt.Localization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tnt.Localization.Interfaces.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LocalizationManager implements LanguageLocalization, CommandLocalization, SubcommandLocalization, OptionLocalization, ChoiceLocalization, DescriptionLocalization {


    private String language;
    private ArrayList<String> path = new ArrayList<>();
    private String key;


    @Override
    public CommandLocalization setLanguage(String language) {
        path.clear();
        this.key = "description";
        this.language = language;
        return this;
    }
    @Override
    public DescriptionLocalization atCommand(String command) {
        path.add(command);
        return this;
    }
    @Override
    public SubcommandLocalization atSubcommand(String subcommand) {
        path.add("subcommands");
        path.add(subcommand);
        return this;
    }
    @Override
    public OptionLocalization atOption(String option) {
        path.add("options");
        key = option;
        return this;
    }
    @Override
    public ChoiceLocalization atChoice(String choice) {
        key = choice;
        return this;
    }

    @Override
    public String getDescription() {
        return getData();
    }
    private String getData() {
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
