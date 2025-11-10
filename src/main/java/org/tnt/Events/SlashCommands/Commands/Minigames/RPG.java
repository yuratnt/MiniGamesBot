package org.tnt.Events.SlashCommands.Commands.Minigames;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.tnt.DescriptionCommands.DescriptionCommands;

public class RPG {

    private final DescriptionCommands descriptionCommands = new DescriptionCommands();
    public RPG() {

    }

    private SlashCommandInteractionEvent event;
    public void usingCommands(SlashCommandInteractionEvent event) {
        this.event = event;
        if (event.getName().equals("rpg")) {
            switch (event.getSubcommandName()) {
                case "help" -> {
                    help();
                }
                case "register" -> {
                }
            }
        }
    }

    public void help() {
        String[][] field = new String[][] {
                {"Команды",""},
                {"help","Вызывает сводку о командах и правилах игры"},
                {"register","Создаёт нового персонажа. При повторном использовании создаст нового персонажа и перезапишет персонажа, сбросив тем самым прогресс"},

        };

        event.replyEmbeds(descriptionCommands.embedField("РПГ приключение",
                "",
                field
        )).queue();
    }


}
