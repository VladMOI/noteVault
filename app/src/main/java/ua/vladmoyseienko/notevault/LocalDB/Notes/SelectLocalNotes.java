package ua.vladmoyseienko.notevault.LocalDB.Notes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public  ArrayList selectAllNotes(Context context){
        LocalNotesDbHelper dbHelper = new LocalNotesDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notes",  null);
        ArrayList notes = new ArrayList();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String label = cursor.getString(cursor.getColumnIndex("label"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            HashMap<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(id));
            map.put("label", label);
            map.put("content", content);
            notes.add(map);
        }
        return notes;
    }
}
