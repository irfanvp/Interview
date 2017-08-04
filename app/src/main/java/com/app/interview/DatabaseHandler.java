package com.app.interview;

/**
 * Created by IRFAN on 8/4/2017.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "StudentsManager";

    // Students table name
    private static final String TABLE_STUDENTS = "Students";

    // Students Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "score";
    private static final String KEY_SlEVEL = "level";
    private static final String KEY_IMAGE = "image";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SCORE + " TEXT," + KEY_SlEVEL + " TEXT,"
                + KEY_IMAGE + " TEXT"
                + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);

        // Create tables again
        onCreate(db);
    }


    // Adding new Student
    void addStudent(Student Student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, Student.getName()); // Student Name
        values.put(KEY_SCORE, Student.getScore()); // Student Phone
        values.put(KEY_SlEVEL, Student.getLevel());
        values.put(KEY_IMAGE, Student.getImageurl());
        // Inserting Row
        db.insert(TABLE_STUDENTS, null, values);
        db.close(); // Closing database connection
    }



    // Getting All Students
    public List<Student> getAllStudents() {
        List<Student> StudentList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student Student = new Student();

                Student.setName(cursor.getString(1));
                Student.setScore(cursor.getString(2));
                Student.setLevel(cursor.getString(3));
                // /Adding Student to list
                StudentList.add(Student);
            } while (cursor.moveToNext());
        }

        // return Student list
        return StudentList;
    }

public void deletetable(){
    SQLiteDatabase db = this.getWritableDatabase();
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_STUDENTS);
}

}
