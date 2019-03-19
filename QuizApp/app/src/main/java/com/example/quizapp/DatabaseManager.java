package com.example.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseManager extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "quiz_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_QUESTIONS = "Questions";
    private static final String TABLE_CHOICES = "choices";
    private static final String ID = "id";
    private static final String QUESTION = "question";
    private static final String CHOICE = "name";
    private static final String CORRECT_ANSWER = "isCorrect";
    private static final String Q_ID = "Qid";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        // Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_QUESTIONS);
        db.execSQL("drop table if exists " + TABLE_CHOICES);
        // Re-create tables
        onCreate(db);
    }

    public void onCreate(SQLiteDatabase db) {
        // build sql create statement
        String sqlQuestionsCreate = "create table " + TABLE_QUESTIONS + "( " + ID;
        sqlQuestionsCreate += " integer primary key autoincrement, " + QUESTION;
        sqlQuestionsCreate += " text )";
        db.execSQL(sqlQuestionsCreate);
        String sqlChoicesCreate = "create table " + TABLE_CHOICES + "( " + ID;
        sqlChoicesCreate += " integer primary key autoincrement, " + CHOICE;
        sqlChoicesCreate += " text, " + CORRECT_ANSWER;
        sqlChoicesCreate += " boolean, " + Q_ID + " int )";
        db.execSQL(sqlChoicesCreate);
    }


    public long insertQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newQuestion = new ContentValues();
        newQuestion.put(QUESTION, question.getQuestion());
        long qId = db.insert(TABLE_QUESTIONS, null, newQuestion);
        db.close();
        return qId;

    }

    public void insertChoice(Choice choice) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlChoiceInsert = "insert into " + TABLE_CHOICES;
        sqlChoiceInsert += " values( null, '" + choice.getChoice();
        sqlChoiceInsert += "', '" + choice.getIsCorrect();
        sqlChoiceInsert += "', '" + choice.getQId() + "' )";
        db.execSQL(sqlChoiceInsert);


    }

    public ArrayList<Question> selectAll() {
        String sqlQuery = "select * from " + TABLE_QUESTIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Question> candies = new ArrayList<Question>();
        while (cursor.moveToNext()) {
            Question currentQuestion
                    = new Question(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1));
            candies.add(currentQuestion);
        }
        db.close();
        return candies;
    }

    public ArrayList<Choice> selectAllChoice() {
        String sqlQuery = "select * from " + TABLE_CHOICES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Choice> choices = new ArrayList<Choice>();
        while (cursor.moveToNext()) {
            Choice currentQuestion
                    = new Choice(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Boolean.parseBoolean(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3)));

            choices.add(currentQuestion);
        }
        db.close();
        return choices;
    }

    public ArrayList<Choice> selectAllChoiceById(int qId) {
        String sqlQuery = "select * from " + TABLE_CHOICES + " WHERE Qid=" + qId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Choice> choices = new ArrayList<Choice>();
        while (cursor.moveToNext()) {
            Choice currentQuestion
                    = new Choice(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Boolean.parseBoolean(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3)));

            choices.add(currentQuestion);
        }
        db.close();
        return choices;
    }


    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //delete from questions Table
        String sqlDeleteQuestion = "delete from " + TABLE_QUESTIONS;
        sqlDeleteQuestion += " where " + ID + " = " + id;
        db.execSQL(sqlDeleteQuestion);
        // delete the choices
        String sqlDeleteChoice = "delete from " + TABLE_CHOICES;
        sqlDeleteChoice += " where " + Q_ID + " = " + id;
        db.execSQL(sqlDeleteChoice);
        db.close();
    }
}
