package org.tnt.SlashCommands;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GuiSlashCommand extends ListenerAdapter {

    public void onGuildReady(@NotNull GuildReadyEvent event) {

        List<CommandData> commandData = new ArrayList<>();

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
