package org.tnt.Events.SlashCommands;

import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandGui extends ListenerAdapter {

    List<CommandData> commandData = new ArrayList<>();

    public void init(GenericGuildEvent event) {
        commandData.clear();
        otherCommands();
        rpg();
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    private void otherCommands() {
        commandData.add(Commands.slash("help", "Информация о командах бота"));
        commandData.add(Commands.slash("register", "Регистрация для использования бота"));
    }


    private void rpg() {
        commandData.add(Commands.slash("rpg", "Команды для управления rog миниигры")
                .addSubcommands(new SubcommandData("help", "Информация о миниигре"))
        );
    }

}


