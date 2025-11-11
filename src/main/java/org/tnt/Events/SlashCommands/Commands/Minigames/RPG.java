package org.tnt.Events.SlashCommands.Commands.Minigames;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.tnt.Database.DatabaseManager;
import org.tnt.DescriptionCommands.DescriptionCommands;

public class RPG {

    private final DescriptionCommands descriptionCommands = new DescriptionCommands();
    private final DatabaseManager sqlManager = new DatabaseManager();
    public RPG() {

    }

    private SlashCommandInteractionEvent event;
    public void usingCommands(SlashCommandInteractionEvent event) {
        this.event = event;
        if (event.getName().equals("rpg")) {
            switch (event.getSubcommandName()) {
                case "help" -> help();
                case "register" -> register();
                case "play" -> play();
            }
        }
    }

    private void help() {
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

    private void register() {
        String[][] data = new String[][] {
                {"Id", event.getMember().getId().toString()},
                {"Class", event.getOption("class").getAsString()},
                {"Level", "1"},
        };

        if (sqlManager.insertData(event.getGuild().getId(), "rpg", data)) {
            event.replyEmbeds(descriptionCommands.messageEmbed("Персонаж зарегистрирован", "Ваш персонаж создан. Для сброса вашего прогресса используйте заново эту команду")).queue();
        } else {
            event.replyEmbeds(descriptionCommands.commandsError("Ошибка создания персонажа")).queue();
        }

    }

    private void play() {

    }
}
