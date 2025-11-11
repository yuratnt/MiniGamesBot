package org.tnt.Database;

import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.tnt.MiniGamesBot.connectionSQL;

public class DatabaseManager {

    private final Connection con = connectionSQL;
    private final StringBuilder sqlData;

    public DatabaseManager() {
        sqlData = new StringBuilder();
    }

    public void init(GenericGuildEvent event) {
        String guildId = event.getGuild().getId();
        String[] data = {
                "Id VARCHAR(45) PRIMARY KEY",
                "Class VARCHAR(45)",
                "Level INTEGER DEFAULT 0",
                "Enemy VARCHAR(45)",
                "ChanelID VARCHAR(45)",
                "Thread VARCHAR(45)"
        };

        createSchema(guildId);
        createTable(guildId,"rpg", data);
    }

    public void createSchema(String name) {
        try {
            Statement createTable = con.createStatement();
            createTable.executeUpdate(
                    "CREATE SCHEMA `" + name + "`;");

            System.out.println("Schema " + name + " created");
        } catch (Exception e) {
            System.out.println("Error creating schema: " + e.getMessage());
        }
    }

    public void dropSchema(String name) {
        try {
            Statement createTable = con.createStatement();
            createTable.executeUpdate(
                    "DROP SCHEMA `" + name + "`;");

            System.out.println("Schema " + name + " вdropped");
        } catch (Exception e) {
            System.out.println("Error dropping schema: " + e.getMessage());
        }
    }
    public void createTable(String schemaId, String name, String[] dataType) {
        try {
            for (String s : dataType) sqlData.append(s).append(",\n");
            sqlData.deleteCharAt(sqlData.length() - 2);

            Statement createTable = con.createStatement();
            createTable.executeUpdate(
                    "CREATE TABLE `" + schemaId +"`.`" + name + "` (" +
                            sqlData + ")"
            );
            System.out.println("Created table " + name);
        } catch (Exception e) {
            System.out.println("Error creating table " + name);
            e.printStackTrace();
        }
    }

    public String getString(String schemaId, String name, String dataType) {
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT " + dataType +" FROM " + schemaId + "." + name
            );

            while (resultSet.next()) {
                return resultSet.getString(dataType);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

