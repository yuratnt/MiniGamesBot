package org.tnt.Events;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.tnt.Events.SlashCommands.Commands.Minigames.RPG;
import org.tnt.Events.SlashCommands.Commands.Settings;
import org.tnt.Events.SlashCommands.GuiManager;
import org.tnt.Database.DatabaseManager;

public class EventManager extends ListenerAdapter {

    private final DatabaseManager sqlManager = new DatabaseManager();
    private final GuiManager slashCommandGui = new GuiManager();

    private final Settings otherCommands = new Settings();
    private final RPG rpg = new RPG();
    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        sqlManager.init(event);
        slashCommandGui.init(event);
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {
        sqlManager.dropSchema(event.getGuild().getId());
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        slashCommandGui.init(event);
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        otherCommands.usingCommands(event);
        rpg.usingCommands(event);
    }

}

