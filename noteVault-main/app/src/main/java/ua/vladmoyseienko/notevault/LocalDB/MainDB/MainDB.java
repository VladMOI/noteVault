package ua.vladmoyseienko.notevault.LocalDB.MainDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDB{
    private static final String DB_NAME = "app.db";
    private static final String TABLE_NOTES_NAME = "notes";
    private static final String TABLE_USER_NAME = "users";

    private static final String NOTES_ID = "id";
    private static final String NOTES_PATH = "path";
    private static final String NOTES_LABEL = "label";

    private static final String USER_ID = "id";
    private static final String USER_DEVICE_SERIAL = "serial";

    public static void createDb(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:"+DB_NAME);//create connection
            String queryNotes = "CREATE TABLE IF NOT EXISTS " + TABLE_NOTES_NAME + " ("//sql query notes
                    + NOTES_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    + NOTES_LABEL + "TEXT,"
                    + NOTES_PATH + "TEXT, "
                    + ");";

            String queryUser = "CREATE TABLE IF NOT EXISTS " + TABLE_USER_NAME + " ("//sql query users
                    + USER_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    + USER_DEVICE_SERIAL + "TEXT,"
                    + ");";

            Statement stmt = conn.createStatement();//get statement
            stmt.execute(queryNotes);//execute query notes
            stmt.execute(queryUser);//execute query users

            System.out.println("MainDB: database " + DB_NAME + " created with tables: " + TABLE_NOTES_NAME + ", " + TABLE_USER_NAME);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        } finally {
            try {
                if (conn != null){
                    conn.close();
                }
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
