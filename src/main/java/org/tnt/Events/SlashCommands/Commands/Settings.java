package org.tnt.Events.SlashCommands.Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.tnt.Database.DatabaseManager;
import org.tnt.Database.Schema;
import org.tnt.DescriptionCommands.DescriptionCommands;
import org.tnt.Localization.LocalizationManager;
import org.tnt.Localization.Message.LocalizationMessage;

public class Settings {
    private final DatabaseManager sqlManager = new DatabaseManager();

    private final DescriptionCommands description = new DescriptionCommands();

    private SlashCommandInteractionEvent event;

    private LocalizationMessage localization = new LocalizationMessage();

    public void usingCommands(SlashCommandInteractionEvent event) {
        this.event = event;
        if (event.getSubcommandName() == null)
            event.replyEmbeds(description.commandsError("No subcommand found")).queue();

        switch (event.getSubcommandName()) {
            case "help" -> help();
            case "language" -> language();
            case "register" -> register();
        }
    }

    private void language() {

    }

    //Основные команды
    private void help(){
        /*localization.setLanguage("RU").atMessage("settings help");
        String[][] fields = new String[][]{
                {localization.atField("help").getTitle(), localization.atField("help").getDescription()},
                {localization.atField("initialization").getTitle(), localization.atField("initialization").getDescription()},
                {localization.atField("register").getTitle(), localization.atField("register").getDescription()}
        };
        localization.atMessage("settings help");
        event.replyEmbeds(description.embedField(localization.getTitle(), localization.getDescription(), fields)).queue();*/

        Schema schema = new Schema(event.getGuild().getId());

    }

    private void register() {
        String[] data = {
                "Id VARCHAR(45) PRIMARY KEY",
                "Name VARCHAR(100)",
                "Balance VARCHAR(100) DEFAULT 0",
                "Energy VARCHAR(100) DEFAULT 100",
                "Time DATETIME"
        };

        sqlManager.createTable(event.getGuild().getId(), event.getMember().getId() , data);
        event.replyEmbeds(description.messageEmbed("Пользователь зарегистрирован", "Пользователь " + event.getMember() + " зарегистрирован и может приступать к играм")).queue();
    }
}
