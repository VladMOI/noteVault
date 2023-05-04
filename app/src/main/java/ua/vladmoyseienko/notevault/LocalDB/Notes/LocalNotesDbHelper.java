package ua.vladmoyseienko.notevault.LocalDB.Notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalNotesDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NOTES = "notes";
    private static final String NOTES_COLUMN_ID = "id";
    private static final String NOTES_COLUMN_LABEL = "label";
    private static final String NOTES_COLUMN_CONTENT = "content";

    private static final String SQL_CREATE_NOTES =
            "CREATE TABLE " + TABLE_NOTES + "(" +
                    NOTES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOTES_COLUMN_LABEL + " TEXT, " +
                    NOTES_COLUMN_CONTENT + " TEXT" +
                    ")";

    public LocalNotesDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(sqLiteDatabase);
    }
}
