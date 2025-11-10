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

    public MessageEmbed embedField(String title, String description, String[][] fields) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        builder.setDescription(description);

        for (String[] field : fields) {
            MessageEmbed.Field embed = new MessageEmbed.Field(field[0], field[1], false);
            builder.addField(embed);
        }

        return builder.build();
    }
}
