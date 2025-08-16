package dev.vishyloki.sqlite.ddl.column;

import dev.vishyloki.sqlite.ddl.columnDefinitions.Constraints;
import dev.vishyloki.sqlite.ddl.columnDefinitions.DataType;

import java.util.HashSet;
import java.util.Set;

/**
 * Column creation
 * @author vishal
 */
public class Column {
    private String columnName;
    private String columnDataType;
    private Set<String> constraintsSet;
    private Object defaultValue;
    private String checkExpression;
    private String collateType;

    private Column(){}

    private Column(String columnName, String columnDataType){
        this.columnName = columnName;
        this.columnDataType = columnDataType;
        this.constraintsSet = new HashSet<>();
    }

    public static Column createColumn(String columnName, String columnDataType){
        return new Column(columnName, columnDataType);
    }

    public String getColumnName(){
        return columnName;
    }

    public Column addConstraint(String constraint){
        if(Constraints.PRIMARYKEY.equals(constraint) || Constraints.NOTNULL.equals(constraint)
                || Constraints.AUTOINCREMENT.equals(constraint) || Constraints.UNIQUE.equals(constraint)){
            constraintsSet.add(constraint);
        }else if(Constraints.COLLATE.equals(constraint)){
            constraintsSet.add(constraint);
            collateType = Constraints.CollateConstants.BINARY;
        }
        return this;
    }

    public Column addConstraint(String constraint, Object constraintValue){
        if(Constraints.DEFAULT.equals(constraint)){
            constraintsSet.add(constraint);
            defaultValue = constraintValue;
        } else if(Constraints.CHECK.equals(constraint)){
            constraintsSet.add(constraint);
            checkExpression = (String) constraintValue;
        } else if(Constraints.COLLATE.equals(constraint)){
            constraintsSet.add(constraint);
            collateType = (String) constraintValue;
        }
        return this;
    }

    /**
     * Check the constraints by SQLite Rules.
     */
    private void checkConstraintsSet(){

        //If the PRIMARY KEY is present , NOT NULL should be present there
        if(constraintsSet.contains(Constraints.NOTNULL) && constraintsSet.contains(Constraints.PRIMARYKEY)){
            constraintsSet.remove(Constraints.NOTNULL);
        }

        //If PRIMARY KEY is present, UNIQUE is redundant and can be removed
        if(constraintsSet.contains(Constraints.UNIQUE) && constraintsSet.contains(Constraints.PRIMARYKEY)){
            constraintsSet.remove(Constraints.UNIQUE);
        }

        //defaultValue datatype and ColumnDataType want to be same.
        if(defaultValue != null){
            if(DataType.INTEGER.equals(columnDataType)){
                if(!(defaultValue instanceof Integer) && !(defaultValue instanceof Long)){
                    defaultValue = null;
                    constraintsSet.remove(Constraints.DEFAULT);
                }
            }else if(DataType.REAL.equals(columnDataType)){
                if(!(defaultValue instanceof Float) && !(defaultValue instanceof Double)){
                    defaultValue = null;
                    constraintsSet.remove(Constraints.DEFAULT);
                }
            }else if(DataType.TEXT.equals(columnDataType)){
                if(!(defaultValue instanceof String)){
                    defaultValue = null;
                    constraintsSet.remove(Constraints.DEFAULT);
                }
            }
        }

        //If the AUTOINCREMENT is present , then INTEGER PRIMARY KEY must be present
        if(constraintsSet.contains(Constraints.AUTOINCREMENT) && !(constraintsSet.contains(Constraints.PRIMARYKEY) && columnDataType.equals(DataType.INTEGER))){
            constraintsSet.remove(Constraints.AUTOINCREMENT);
        }

        //If the AUTOINCREMENT is present , then DEFAULT value should be assigned.
        if(constraintsSet.contains(Constraints.DEFAULT) && constraintsSet.contains(Constraints.AUTOINCREMENT)){
            constraintsSet.remove(Constraints.DEFAULT);
        }

        //If the COLLATE is present , It can be applicable for TEXT Datatype only.
        if(constraintsSet.contains(Constraints.COLLATE) && !DataType.TEXT.equals(columnDataType)){
            constraintsSet.remove(Constraints.COLLATE);
        }

        //If the COLLATE is present , it types must be of three (BINARY, NOCASE, RTRIM)
        if(constraintsSet.contains(Constraints.COLLATE)){
            if(!(Constraints.CollateConstants.BINARY.equals(collateType) ||
            Constraints.CollateConstants.NOCASE.equals(collateType) ||
            Constraints.CollateConstants.RTRIM.equals(collateType))){
                constraintsSet.remove(Constraints.COLLATE);
            }
        }
    }

    @Override
    public String toString(){
        checkConstraintsSet();

        StringBuilder stringBuilder = new StringBuilder(columnName);
        stringBuilder.append(" ").append(columnDataType);

        if(constraintsSet.contains(Constraints.PRIMARYKEY)){
            stringBuilder.append(" ").append(Constraints.PRIMARYKEY);
        }
        if(constraintsSet.contains(Constraints.AUTOINCREMENT)){
            stringBuilder.append(" ").append(Constraints.AUTOINCREMENT);
        }
        if(constraintsSet.contains(Constraints.NOTNULL)){
            stringBuilder.append(" ").append(Constraints.NOTNULL);
        }
        if(constraintsSet.contains(Constraints.UNIQUE)){
            stringBuilder.append(" ").append(Constraints.UNIQUE);
        }
        if(constraintsSet.contains(Constraints.DEFAULT)){
            stringBuilder.append(" ").append(Constraints.DEFAULT).append(" ");
            if(DataType.TEXT.equals(columnDataType)){
                stringBuilder.append("'").append(defaultValue).append("'");
            }else{
                stringBuilder.append(defaultValue);
            }
        }
        if(constraintsSet.contains(Constraints.CHECK)){
            stringBuilder.append(" ").append(Constraints.CHECK);
            stringBuilder.append(" (").append(checkExpression).append(")");
        }
        if(constraintsSet.contains(Constraints.COLLATE)){
            stringBuilder.append(" ").append(Constraints.COLLATE);
            stringBuilder.append(" ").append(collateType);
        }

        return stringBuilder.toString();
    }
}
