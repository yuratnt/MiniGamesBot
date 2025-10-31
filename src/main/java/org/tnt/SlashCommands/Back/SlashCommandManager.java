package org.tnt.SlashCommands.Back;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import static org.tnt.MiniGamesBot.connectionSQL;

public class SlashCommandManager extends ListenerAdapter {
    private Statement statement;
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
        String userid = Objects.requireNonNull(event.getMember()).getUser().getId();
        try {
            statement = connectionSQL.createStatement();
            statement.executeUpdate("INSERT minigamesbot.users(ID) VALUES ('" + userid + "')");
            MessageEmbed eb = new EmbedBuilder()
                    .setTitle("Slash Commands Registered")
                    .setDescription("Slash Commands Registered")
                    .build();

            event.replyEmbeds(eb).queue();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
