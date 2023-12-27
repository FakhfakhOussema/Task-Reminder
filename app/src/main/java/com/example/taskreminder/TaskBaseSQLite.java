package com.example.taskreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TaskBaseSQLite extends SQLiteOpenHelper
{
    public static final String TABLE_NAME = "task";
    public static final String NOM_BDD = "mydatabase";
    public static final String COL_ID = "ID";
    public static final String COL_TITLE = "TITLE";
    public static final String COL_DESC = "DESCRIPTION";
    public static final String COL_DATE = "DATE";

    public TaskBaseSQLite(Context context)
    {
        super (context, NOM_BDD, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TAB);
    }
    public static final String CREATE_TAB = "CREATE TABLE " + TABLE_NAME +
            " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TITLE + " TEXT NOT NULL, "
            + COL_DESC + " TEXT NOT NULL, "
            + COL_DATE + " TEXT NOT NULL);";
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public  Boolean insertTask(Task task)
    {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues content = new ContentValues();
        content.put (COL_TITLE, task.getTitle ());
        content.put (COL_DESC, task.getDescription());
        content.put(COL_DATE,task.getDate());

        long result = db.insert (TABLE_NAME, null, content);

        if (result == -1)
            return false;
        else
            return true;
    }

    public  Boolean updateTask(int id,Task task)
    {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues content = new ContentValues();
        content.put (COL_TITLE, task.getTitle ());
        content.put (COL_DESC, task.getDescription());
        content.put(COL_DATE,task.getDate());
        int result = db.update (TABLE_NAME, content, COL_ID + " = " + id, null);

        if (result <= 0)
            return false;
        else
            return true;
    }

    public long getTasksCount()
    {
        SQLiteDatabase db = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }


    public ArrayList<Task> getAllTasks()
    {
        ArrayList<Task> tasks = new ArrayList<Task>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if(cursor.moveToFirst())
        {
            do
            {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String date = cursor.getString(3);

                Task task = new Task(id,title,description,date);
                tasks.add(task);
            }
            while(cursor.moveToNext());
            cursor.close();
        }
        return tasks;
    }

    public  Boolean deleteTask(String x)
    {
        SQLiteDatabase db = getWritableDatabase();
        String args[] ={"%"+x+"%"};
        int result = db.delete(TABLE_NAME,"TITLE LIKE ?",args);
        if (result <= 0)
            return false;
        else
            return true;
    }


}
