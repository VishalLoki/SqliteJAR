package dev.vishyloki.sqlite.database;

import dev.vishyloki.sqlite.ddl.table.Table;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A single common DataBase
 * Maintains the details of Tables and related info.
 * @author vishal
 */
public class DataBase {
    //TableList
    private static final Map<String,Table> tableMap = new LinkedHashMap<>();

    private DataBase(){}

    public static void addTableToMap(Table table){
        tableMap.put(table.getTableName(),table);
    }

    public static Map<String,Table> getTablesFromMap(){
        return tableMap;
    }

    public static Table getTableFromMap(String tableName){
        return tableMap.get(tableName);
    }
}
