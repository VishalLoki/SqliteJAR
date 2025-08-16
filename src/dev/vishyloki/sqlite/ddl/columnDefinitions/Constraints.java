package dev.vishyloki.sqlite.ddl.columnDefinitions;

/**
 * Column Level Constraints
 * @author vishal
 */
public class Constraints {

    /**
     * Prevents the column from storing NULL values
     * If you try to insert a NULL into this column, SQLite throws an error
     * Don't use in PRIMARY KEY columns.
     */
    public static String NOTNULL = "NOT NULL";

    /**
     * Sets a value to be used automatically when no value is given during an INSERT
     */
    public static String DEFAULT = "DEFAULT";

    /**
     * Defines collation order (how text comparison and sorting are done)
     * BINARY (default) → compares raw byte values (case-sensitive)
     * NOCASE → case-insensitive for ASCII letters
     * RTRIM → ignores trailing spaces.
     */
    public static String COLLATE = "COLLATE";

    /**
     * Ensures that an automatically assigned INTEGER PRIMARY KEY value is always greater than
     * any previous value in the table, even if rows are deleted
     * Can only be used with INTEGER PRIMARY KEY
     * Without AUTOINCREMENT, SQLite still auto-generates IDs, but it may reuse deleted values
     */
    public static String AUTOINCREMENT = "AUTOINCREMENT";

    /**
     * Primary key (must be unique, automatically NOT NULL)
     */
    public static String PRIMARYKEY = "PRIMARY KEY";

    /**
     * Ensures all values in this column are unique (can be NULL unless NOT NULL is also specified)
     */
    public static String UNIQUE = "UNIQUE";

    /**
     * Adds a rule that must be true for every row
     */
    public static String CHECK = "CHECK";

    public static class CollateConstants{
        //BINARY (default) → compares raw byte values (case-sensitive)
        public static String BINARY = "BINARY";

        //NOCASE → case-insensitive for ASCII letters
        public static String NOCASE = "NOCASE";

        //RTRIM → ignores trailing spaces.
        public static String RTRIM = "RTRIM";
    }

}
