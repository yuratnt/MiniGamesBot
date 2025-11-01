package org.tnt.ServerJoin;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.tnt.SQLManager.SQLManager;

import java.util.ArrayList;
import java.util.List;

public class JoinServerManager extends ListenerAdapter {

    private final SQLManager sqlManager = new SQLManager();
    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        System.out.println("Joined Server: " + event.getGuild().getName());
        sqlManager.createDatabase(event.getGuild().getId());
    }
}
