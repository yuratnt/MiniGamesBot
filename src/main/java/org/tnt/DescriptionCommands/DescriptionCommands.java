package org.tnt.DescriptionCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class DescriptionCommands {

    public MessageEmbed messageEmbed(String title, String description) {
        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(description)
                .build();
    }

    public MessageEmbed commandsError(String description) {
        return new EmbedBuilder()
                .setTitle("Ошибка выполнения команды")
                .setDescription(description)
                .build();
    }
}
