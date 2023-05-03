package ua.vladmoyseienko.notevault.LocalDB.Notes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SelectLocalNotes {
    private  ArrayList notes = new ArrayList();

    public SelectLocalNotes(){}

    public  ArrayList selectAllNotes(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:app.db");//get connection

            String query = "SELECT * FROM notes";//sql query
            Statement stmt = conn.createStatement();//statement
            ResultSet rs = stmt.executeQuery(query);//result

            while (rs.next()) { //while there are incomes
                int id = rs.getInt("id");
                String label = rs.getString("label");
                String path = rs.getString("path");

                HashMap<String, String> map = new HashMap<>();//"id":"1", "label":"someName", "path":"local path to file"
                map.put("id", String.valueOf(id));
                map.put("label", label);
                map.put("path", path);

                notes.add(map);//add map to array
            }
            rs.close();
            return notes;
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
        return notes;
    }
}
