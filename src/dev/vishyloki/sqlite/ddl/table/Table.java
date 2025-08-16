package dev.vishyloki.sqlite.ddl.table;

import dev.vishyloki.sqlite.database.DataBase;
import dev.vishyloki.sqlite.ddl.column.Column;
import dev.vishyloki.sqlite.logging.SQLLogger;
import dev.vishyloki.sqlite.commonConstants.CommonConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Table creation
 * @author vishal
 */
public class Table {
    //Column List
    private final Map<String,Column> columnMap = new LinkedHashMap<>();

    private String tableName;
    private SQLLogger logger;

    private Table(){}

    private Table(String tableName){
        this.tableName = tableName;
    }

    public String getTableName(){
        return tableName;
    }

    public static Table createTable(String tableName){
        Table table = new Table(tableName);
        DataBase.addTableToMap(table);
        return table;
    }

    private void addColumnToMap(Column column){
        columnMap.put(column.getColumnName(),column);
    }

    public Map<String,Column> getColumnsFromMap(){
        return columnMap;
    }

    public Table addColumn(Column column){
        addColumnToMap(column);
        return this;
    }

    @Override
    public String toString(){
        if(tableName == null || columnMap.isEmpty()){
            logger.warn(TableConstants.className, TableConstants.toString, "Table Name (OR) Column List is Empty or not updated.");
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder(CommonConstants.CREATE_TABLE);
        stringBuilder.append(tableName);

        List<Column> columnList = new ArrayList<>(columnMap.values());
        int columnListLength = columnList.size();
        for(int i = 0; i < columnListLength; i++){
            if(i == 0) stringBuilder.append(" (");
            stringBuilder.append(columnList.get(i).toString());
            if(i == columnListLength - 1){
                stringBuilder.append(");");
            }else{
                stringBuilder.append(",");
            }
        }

        return stringBuilder.toString();
    }

    private static class TableConstants{
        //Class Name Constant
        public static String className = "Table";

        //Method Name constants
        public static String toString = "toString";
    }
}
