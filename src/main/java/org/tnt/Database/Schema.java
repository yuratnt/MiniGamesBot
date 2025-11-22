package org.tnt.Database;

import org.tnt.Database.interfaces.Data;
import org.tnt.Database.interfaces.Select;

import java.sql.PreparedStatement;


public class Schema implements Select, Data {


    private PreparedStatement statement;
    private String schema;
    String column;
    String row;
    public Schema(String schema) {
        this.schema = schema;
    }

    @Override
    public Data setColumns(String column) {
        this.column = column;
        return this;
    }

    @Override
    public Data setRows(String rows) {
        this.row = rows;
        return this;
    }


    @Override
    public String getString() {


        return "";
    }

    @Override
    public int getInt() {
        return 0;
    }

    private Object getData() {


        return null;
    }
}
