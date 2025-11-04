package org.tnt.Events;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import org.tnt.Events.SlashCommands.Back.SlashCommandManager;
import org.tnt.Events.SlashCommands.Front.SlashCommandGui;
import org.tnt.SQLManager.SQLManager;

import java.util.ArrayList;
import java.util.List;

public class EventManager extends ListenerAdapter {

    private final SQLManager sqlManager = new SQLManager();
    private final SlashCommandGui slashCommandGui = new SlashCommandGui();
    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        slashCommandGui.init(event);
        sqlManager.createSchema(event.getGuild().getId());
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        SlashCommandManager slashCommandManager = new SlashCommandManager(event);
        slashCommandManager.usingCommands(event.getName());
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        slashCommandGui.init(event);
    }

}

