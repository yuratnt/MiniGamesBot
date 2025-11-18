package org.tnt.Events.SlashCommands;

import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.tnt.Localization.LocalizationManager;

import java.util.ArrayList;
import java.util.List;

public class GuiManager extends ListenerAdapter {

    List<CommandData> commandData = new ArrayList<>();


    public void init(GenericGuildEvent event) {
        commandData.clear();
        otherCommands();
        rpg();
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    private void otherCommands() {
        LocalizationManager localizationRu = new LocalizationManager("RU", "other");
        LocalizationManager localizationEN = new LocalizationManager("EN", "other");


        commandData.add(Commands.slash("settings", localizationEN.getDescription("settings"))
                //Локализация
                .setDescriptionLocalization(
                        DiscordLocale.RUSSIAN,
                        localizationRu.getDescription("settings")
                )

                //подкоманды
                .addSubcommands(new SubcommandData(
                        "help",
                        localizationEN.getSubcommandDescription("settings", "help")
                        )
                        .setDescriptionLocalization(
                                DiscordLocale.RUSSIAN,
                                localizationRu.getSubcommandDescription("settings", "help")
                        )
                )
                .addSubcommands(new SubcommandData(
                        "register",
                        localizationEN.getSubcommandDescription(
                                "settings",
                                "register"
                        ))
                        .setDescriptionLocalization(
                                DiscordLocale.RUSSIAN,
                                localizationRu.getSubcommandDescription(
                                        "settings",
                                        "register"
                                ))
                )
                .addSubcommands(new SubcommandData(
                        "initialization",
                        localizationEN.getSubcommandDescription(
                                "settings",
                                "initialization"
                        ))
                        .setDescriptionLocalization(
                                DiscordLocale.RUSSIAN,
                                localizationRu.getSubcommandDescription(
                                        "settings",
                                        "initialization"
                                ))
                        .addOptions(new OptionData(
                                OptionType.CHANNEL,
                                "chanel",
                                "Чат, в котором будут проводиться минигры",
                                true
                        ))
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


