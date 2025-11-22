package org.tnt.Database;

import org.jetbrains.annotations.NotNull;
import org.tnt.Database.interfaces.Data;
import org.tnt.Database.interfaces.Select;
import org.tnt.Database.interfaces.Table;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.tnt.MiniGamesBot.connectionSQL;


public class Schema implements Table, Select, Data {


    private PreparedStatement statement;
    private String schema;
    private String table;
    private String column;
    private String row;
    public Schema(String schema) {
        this.schema = schema;
    }

    @Override
    public Select setTable(String table) {
        this.table = table;
        return this;
    }
    @Override
    public Table setColumns(String column) {
        this.column = column;
        return this;
    }

    @Override
    public Table setRows(String rows) {
        this.row = rows;
        return this;
    }


    @Override
    public String getString() {


        return getData("id");
    }

    @Override
    public int getInt() {
        return 0;
    }

    private String getData(@NotNull String data) {
        String sql = "SELECT * FROM " + schema + "." + table + " WHERE " + column + " ='" + row + "'";
        try {
            statement = connectionSQL.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                data = rs.getString(data);
            }
            return data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
