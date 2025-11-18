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

    private final LocalizationManager localization = new LocalizationManager();

    public void init(GenericGuildEvent event) {
        commandData.clear();
        otherCommands();
        rpg();
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    private void otherCommands() {
        localization.setCommand("settings");
        localization.getDescriptionCommand();

        commandData.add(Commands.slash("settings", localization.setLanguage("EN").getDescriptionCommand())
                .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getDescriptionCommand()
                )

                //подкоманды
                .addSubcommands(new SubcommandData(
                        "help",
                        localization.setLanguage("EN").getSubcommandDescription("help"))
                        .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getSubcommandDescription("help"))
                )
                .addSubcommands(new SubcommandData(
                        "register",
                        localization.setLanguage("EN").getSubcommandDescription("register"))
                        .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getSubcommandDescription("register"))
                )
                .addSubcommands(new SubcommandData(
                        "initialization",
                        localization.setLanguage("EN").getSubcommandDescription("initialization"))
                        .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getSubcommandDescription("initialization"))
                        .addOptions(new OptionData(
                                OptionType.CHANNEL,
                                "chanel",
                                localization.setLanguage("EN").getSubcommandOption("initialization", "chanel"
                                ),
                                true)
                                .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getSubcommandOption("initialization", "chanel"))
                        )
                )
        );
    }

    private void rpg() {
        localization.setCommand("rpg");

        commandData.add(Commands.slash("rpg", "Команды для управления rog миниигры")
                .addSubcommands(new SubcommandData("help", "Информация о миниигре"))
                .addSubcommands(new SubcommandData("register", "Создание персонажа для начала игры")
                        .addOptions(new OptionData(
                                OptionType.STRING,
                                "class",
                                "Выберете класс персонажа",
                                true
                                )
                                .addChoice("Воин", "Warrior").setDescriptionLocalization(
                                        DiscordLocale.RUSSIAN,
                                        localization.setLanguage("RU").getSubCommandOptionChoice(
                                                "register",
                                                "class",
                                                "warrior")
                                )
                                .addChoice("Волшебник", "Mage")
                                .addChoice("Лучник", "Archer")
                                .addChoice("Разбойник", "Rogue")
                        )
                )
                .addSubcommands(new SubcommandData("play", "Запускает миниигру РПГ приключение"))

        );
    }

}


