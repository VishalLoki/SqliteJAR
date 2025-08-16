package dev.vishyloki.sqlite.ddl.columnDefinitions;

/**
 * Datatypes constants
 * @author vishal
 */
public class DataType {
    /**
     * INTEGER DATATYPE
     * Stores whole numbers
     * Can store signed integers of 1, 2, 3, 4, 6, or 8 bytes depending on value
     * Range: –9,223,372,036,854,775,808 to +9,223,372,036,854,775,807 (8-byte signed)
     */
    public static String INTEGER = "INTEGER";

    /**
     * REAL DATATYPE
     * Stores floating-point numbers (8-byte IEEE 754)
     * Good for decimals, measurements, scientific values
     */
    public static String REAL = "REAL";

    /**
     * TEXT DATATYPE
     * Stores text (string) values
     * Uses UTF-8 or UTF-16 encoding
     * No strict length limit — you can store very long strings
     */
    public static String TEXT = "TEXT";

    /**
     * BLOB DATATYPE
     * Stores Binary Large Objects (raw binary data)
     * SQLite stores it exactly as given (no encoding or transformation)
     * Used for files, images, serialized data, etc
     */
    public static String BLOB = "BLOB";
}
