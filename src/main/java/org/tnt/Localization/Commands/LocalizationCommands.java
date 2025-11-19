package org.tnt.Localization.Commands;

import org.tnt.Localization.Commands.Interfaces.*;
import org.tnt.Localization.LocalizationManager;

public class LocalizationCommands extends LocalizationManager implements Language, Command, Subcommand, Option, Choice, Description {

    @Override
    public Command setLanguage(String language) {
        this.language = language;
        return this;
    }
    @Override
    public Description atCommand(String command) {
        path.clear();
        path.add(command);
        return this;
    }
    @Override
    public Description atSubcommand(String subcommand) {
        path.subList(1, path.size()).clear();
        path.add("subcommands");
        path.add(subcommand);
        return this;
    }
    @Override
    public Option atOption(String option) {
        path.add("options");
        this.key = option;
        return this;
    }
    @Override
    public Choice atChoice(String choice) {
        if (!path.contains("choices")) {
            path.add(key);
            path.add("choices");
        }

        this.key = choice;
        return this;
    }

    @Override
    public String getDescription() {
        if (!path.contains("options")) this.key = "description";
        return getData();
    }
}
