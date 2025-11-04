package org.tnt.Events.SlashCommands.Back;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.tnt.SQLManager.SQLManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

import static org.tnt.MiniGamesBot.connectionSQL;

public class SlashCommandManager {
    private final SQLManager sqlManager = new SQLManager();

    private final SlashCommandInteractionEvent event;

    public SlashCommandManager(SlashCommandInteractionEvent event) {
        this.event = event;
    }

    public void usingCommands(String command) {
        switch (command) {
            case "help" -> {
                help();
            }
            case "register" -> {
                register();
            }
        }
    }

    //Основные команды
    private void help() {
        event.reply("Slash Commands Help").queue();
    }

    protected void register() {
        MessageEmbed eb = new EmbedBuilder()
                .setTitle("Slash Commands Registered")
                .setDescription("Slash Commands Registered")
                .build();

        String[] data = {
                "Id INT PRIMARY KEY",
                "Name VARCHAR(100)",
                "Balance VARCHAR(100) DEFAULT 0",
                "Energy VARCHAR(100) DEFAULT 100",
                "Time DATETIME"
        };

        sqlManager.createTable(event.getGuild().getId(), event.getMember().getId() , data);
        event.replyEmbeds(eb).queue();
    }


}
