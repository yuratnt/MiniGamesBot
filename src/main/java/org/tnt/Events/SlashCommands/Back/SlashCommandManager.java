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

public class SlashCommandManager extends ListenerAdapter {
    private final SQLManager sqlManager = new SQLManager();
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "help" -> {
                help(event);
            }
            case "register" -> {
                register(event);
            }

        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
    }

    private void help(SlashCommandInteractionEvent event) {
        event.reply("Slash Commands Help").queue();
    }

    private void register(SlashCommandInteractionEvent event) {
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
