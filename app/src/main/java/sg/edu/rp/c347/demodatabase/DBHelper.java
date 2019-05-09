package sg.edu.rp.c347.demodatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

//sqlite super class
public class DBHelper extends SQLiteOpenHelper {
    // Start version with 1
    // increment by 1 whenever db schema changes.
    private static final int DATABASE_VER = 1;
    //FILE NAME OF database
    private static final String DATABASE_NAME = "tasks.db";

    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";


    public DBHelper(Context context) {
        //constructor of super class
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableSql = "CREATE TABLE " + TABLE_TASK +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_DATE + " TEXT)";

        db.execSQL(createTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        // Create table(s) again
        onCreate(db);
    }

    public void insertTask(String description, String date) {

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();

        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        //store column name as key and description as value
        values.put(COLUMN_DESCRIPTION, description);
        //store column name as key and date as value
        values.put(COLUMN_DATE, date);
        //insert row into table_task
        db.insert(TABLE_TASK, null, values);
        //close connection
        db.close();
    }

    public ArrayList<String> getTaskContent() {
        ArrayList<String> tasks = new ArrayList<String>();
        String selectQuery = "SELECT " + COLUMN_DESCRIPTION + " FROM "
                + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //boolean if first row is true
        if (cursor.moveToFirst()) {
            //do at least once
            do {
                tasks.add(cursor.getString(0));

                //            cursor to 2nd row
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }


    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String selectQuery = "Select " + COLUMN_ID + ", "
                + COLUMN_DESCRIPTION + ", "
                + COLUMN_DATE
                + " from " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                //object creation from database
                int id = cursor.getInt(0);
                String description = cursor.getString(1);
                String date = cursor.getString(2);
                Task obj = new Task(id, description, date);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;

    }
}
