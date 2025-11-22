package org.tnt.Database;

import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

import static org.tnt.MiniGamesBot.connectionSQL;

public class DatabaseManager {

    private final Connection con = connectionSQL;
    private final StringBuilder sqlData;

    public DatabaseManager() {
        sqlData = new StringBuilder();
    }

    public void init(GenericGuildEvent event) {
        String guildId = event.getGuild().getId();
        String[] rpgData = {
                "Id VARCHAR(45) PRIMARY KEY",
                "Class VARCHAR(45)",
                "Level INTEGER DEFAULT 0",
                "Enemy VARCHAR(45)",
                "ChanelID VARCHAR(45)",
                "Thread VARCHAR(45)"
        };

        String[] settingsData = {
                "Id VARCHAR(45) PRIMARY KEY",
                "Localization VARCHAR(2) DEFAULT 'EN'",
                "ChanelID VARCHAR(45)"
        };

        createSchema(guildId);
        createTable(guildId,"rpg", rpgData);
        createTable(guildId,"settings", settingsData);
    }

    public boolean createSchema(@NotNull String name) {
        try {
            Statement createTable = con.createStatement();

            createTable.executeUpdate(
                    "CREATE SCHEMA `" + name + "`;");

            System.out.println("Schema " + name + " created");
            return true;
        } catch (Exception e) {
            System.out.println("Error creating schema: " + e.getMessage());
            return false;
        }
    }

    public boolean dropSchema(@NotNull String name) {
        try {
            Statement createTable = con.createStatement();

            createTable.executeUpdate(
                    "DROP SCHEMA `" + name + "`;");

            System.out.println("Schema " + name + " вdropped");
            return true;
        } catch (Exception e) {
            System.out.println("Error dropping schema: " + e.getMessage());
            return false;
        }
    }
    public boolean createTable(@NotNull String schema, @NotNull String name, @NotNull String[] dataType) {
        try {
            for (String s : dataType) sqlData.append(s).append(",\n");
            sqlData.deleteCharAt(sqlData.length() - 2);

            Statement createTable = con.createStatement();
            createTable.executeUpdate(
                    "CREATE TABLE `" + schema + "`.`" + name + "` (" +
                            sqlData + ")"
            );
            sqlData.delete(0, sqlData.length() - 1);
            System.out.println("Created table " + name);
            return true;
        } catch (Exception e) {
            System.out.println("Error creating table " + name);
            return false;
        }
    }
}

