package org.tnt.Events.SlashCommands;

import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.tnt.Localization.Commands.LocalizationCommands;

import java.util.ArrayList;
import java.util.List;

public class GuiManager extends ListenerAdapter {

    List<CommandData> commandData = new ArrayList<>();

    private final LocalizationCommands localization = new LocalizationCommands();

    public void init(GenericGuildEvent event) {
        commandData.clear();
        otherCommands();
        rpg();
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    private void otherCommands() {
        commandData.add(Commands.slash("settings", localization.setLanguage("EN").atCommand("settings").getDescription())
                .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getDescription()
                )

                //подкоманды
                .addSubcommands(new SubcommandData(
                        "help",
                        localization.setLanguage("EN").atSubcommand("help").getDescription())
                        .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getDescription())
                )
                .addSubcommands(new SubcommandData(
                        "register",
                        localization.setLanguage("EN").atSubcommand("register").getDescription())
                        .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").atSubcommand("register").getDescription())
                )
                .addSubcommands(new SubcommandData(
                        "initialization",
                        localization.setLanguage("EN").atCommand("settings").atSubcommand("initialization").getDescription())
                        .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getDescription())
                        .addOptions(new OptionData(
                                OptionType.CHANNEL,
                                "chanel",
                                localization.setLanguage("EN").atOption("chanel").getDescription(),
                                true)
                                .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getDescription())
                        )
                )
        );
    }

    private void rpg() {
        commandData.add(Commands.slash("rpg", localization.setLanguage("EN").atCommand("rpg").getDescription())
                .addSubcommands(new SubcommandData("help", localization.setLanguage("EN").atSubcommand("help").getDescription())
                        .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getDescription())
                )
                .addSubcommands(new SubcommandData("register", localization.setLanguage("EN").atSubcommand("register").getDescription())
                        .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getDescription())
                        .addOptions(new OptionData(
                                OptionType.STRING,
                                "class",
                                localization.setLanguage("EN").getDescription(),
                                true
                                )
                                .addChoice(localization.setLanguage("EN").atOption("class").atChoice("warrior").getDescription(), "warrior")
                                .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getDescription())
                                .addChoice(localization.setLanguage("EN").atChoice("mage").getDescription(), "mage")
                                .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getDescription())
                                .addChoice(localization.setLanguage("EN").atChoice("archer").getDescription(), "archer")
                                .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getDescription())
                                .addChoice(localization.setLanguage("EN").atChoice("rogue").getDescription(), "rogue")
                                .setDescriptionLocalization(DiscordLocale.RUSSIAN, localization.setLanguage("RU").getDescription())
                        )
                )
                .addSubcommands(new SubcommandData("play", "Запускает миниигру РПГ приключение"))
        );
    }

}


