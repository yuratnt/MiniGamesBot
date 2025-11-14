package org.tnt.Events.SlashCommands.Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.tnt.Database.DatabaseManager;
import org.tnt.DescriptionCommands.DescriptionCommands;

public class Settings {
    private final DatabaseManager sqlManager = new DatabaseManager();

    private final DescriptionCommands descriptionCommands = new DescriptionCommands();

    private SlashCommandInteractionEvent event;

    public void usingCommands(SlashCommandInteractionEvent event) {
        this.event = event;
        if (event.getSubcommandName() == null)
            event.replyEmbeds(descriptionCommands.commandsError("No subcommand found")).queue();

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
        sqlManager.getData(event.getGuild().getId(), event.getMember().getId(), "Id");
        event.reply("Slash Commands Help").queue();
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
        event.replyEmbeds(descriptionCommands.messageEmbed("Пользователь зарегистрирован", "Пользователь " + event.getMember() + " зарегистрирован и может приступать к играм")).queue();
    }
}
