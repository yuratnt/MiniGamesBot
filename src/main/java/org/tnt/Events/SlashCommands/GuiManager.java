package org.tnt.Events.SlashCommands;

import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
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

        commandData.add(Commands.slash("bot", "Содержит различные вспомогательные команды бота")
                .addSubcommands(new SubcommandData("help", "Информация о боте и его основных командах"))
                .addSubcommands(new SubcommandData("initialization", "Обязательная команды для работы бота. Устанавливает чат, в котором будут проводиться минигры")
                        .addOptions(new OptionData(OptionType.CHANNEL, "chanel", "Чат, в котором будут проводиться минигры", true))
                )
        );
    }

    private void rpg() {
        commandData.add(Commands.slash("rpg", "Команды для управления rog миниигры")
                .addSubcommands(new SubcommandData("help", "Информация о миниигре"))
                .addSubcommands(new SubcommandData("register", "Создание персонажа для начала игры")
                        .addOptions(new OptionData(OptionType.STRING, "class", "Выберете класс персонажа", true)
                                .addChoice("Воин", "Warrior")
                                .addChoice("Волшебник", "Mage")
                                .addChoice("Лучник", "Archer")
                                .addChoice("Разбойник", "Rogue")
                        )
                )
                .addSubcommands(new SubcommandData("play", "Запускает миниигру РПГ приключение"))

        );
    }

}


