package ua.vladmoyseienko.notevault.LocalDB.Notes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class InsertLocalNotes {

    public static boolean insertNote(ArrayList notes){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:app.db");//get connection

            for (int i = 0; i < notes.size(); i++){
                HashMap<String,String> currentNote = (HashMap) notes.get(i);
                Object label = currentNote.get("label");
                Object path = currentNote.get("path");
                String query = "INSERT INTO notes (label, path), VALUES("+label+", " + path + ");";//sql query
                Statement stmt = conn.createStatement();//statement
                int rows  = stmt.executeUpdate(query);
                System.out.println("Rows updated: " + rows);
                if (rows>0){
                    return true;
                }else {
                    return false;
                }
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            try{
                if(conn != null){
                    conn.close();

                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
}

