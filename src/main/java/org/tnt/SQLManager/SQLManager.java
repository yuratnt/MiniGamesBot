package org.tnt.SQLManager;

import java.sql.Connection;
import java.sql.Statement;

import static org.tnt.MiniGamesBot.connectionSQL;

public class SQLManager {

    private final Connection con = connectionSQL;
    private final StringBuilder sqlData;

    public SQLManager() {
        sqlData = new StringBuilder();
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
                    "CREATE TABLE `" +schemaId +"`.`" + name + "` (" +
                            sqlData + ")"
            );
            System.out.println("Created table " + name);
        } catch (Exception e) {
            System.out.println("Error creating table " + name);
            e.printStackTrace();
        }
    }


}

