package org.tnt.Events.SlashCommands.Front;

import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandGui extends ListenerAdapter {

    private final List<CommandData> commandData = new ArrayList<>();
    public void init(GenericGuildEvent event) {
        mainCommands();
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    private void mainCommands() {
        commandData.add(Commands.slash("help", "Информация о командах бота"));
        commandData.add(Commands.slash("register", "Регистрация для использования бота"));
    }

}


