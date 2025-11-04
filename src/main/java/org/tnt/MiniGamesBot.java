package org.tnt;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.tnt.Events.EventManager;
import org.tnt.Events.SlashCommands.Front.SlashCommandGui;
import org.tnt.Events.SlashCommands.Back.SlashCommandManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MiniGamesBot {

    protected Dotenv dotenv;
    private final ShardManager shardManager;

    public static Connection connectionSQL;

    public MiniGamesBot() {
        dotenv = Dotenv.load();
        String token = dotenv.get("TOKEN");

        try {
            connectionSQL = DriverManager.getConnection(dotenv.get("URL"), dotenv.get("USER"), dotenv.get("PASSWORD"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        shardManager = builder.build();

        shardManager.addEventListener(new EventManager());
    }


    public Dotenv getConfig() {
        return dotenv;
    }


    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] arg) {
        MiniGamesBot bot = new MiniGamesBot();

        System.out.println("Бот работает");
    }

}
