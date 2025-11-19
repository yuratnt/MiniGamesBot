package org.tnt.Localization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LocalizationManager {

    public String filename;
    public String language;
    public ArrayList<String> path = new ArrayList<>();
    public String key;

    public String getData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode config = mapper.readTree(new File("src/main/resources/Localization/" + language + "/"+ filename + ".json"));
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
