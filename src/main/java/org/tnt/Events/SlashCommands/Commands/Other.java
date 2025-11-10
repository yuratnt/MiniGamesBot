package org.tnt.Events.SlashCommands.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.tnt.Database.DatabaseManager;

public class Other {
    private final DatabaseManager sqlManager = new DatabaseManager();

    private SlashCommandInteractionEvent event;

    public void usingCommands(SlashCommandInteractionEvent event) {
        this.event = event;
        switch (event.getName()) {
            case "help" -> {
                help();
            }
        }
    }

    //Основные команды
    private void help(){
        sqlManager.getString(event.getGuild().getId(), event.getMember().getId(), "Id");
        event.reply("Slash Commands Help").queue();
    }

    protected void register() {
        MessageEmbed eb = new EmbedBuilder()
                .setTitle("Slash Commands Registered")
                .setDescription("Slash Commands Registered")
                .build();

        String[] data = {
                "Id VARCHAR(45) PRIMARY KEY",
                "Name VARCHAR(100)",
                "Balance VARCHAR(100) DEFAULT 0",
                "Energy VARCHAR(100) DEFAULT 100",
                "Time DATETIME"
        };

        sqlManager.createTable(event.getGuild().getId(), event.getMember().getId() , data);
        event.replyEmbeds(eb).queue();
    }
}
