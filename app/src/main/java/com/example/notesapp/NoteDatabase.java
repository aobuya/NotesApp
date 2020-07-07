package com.example.notesapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.notesapp.Models.Note;

@Database(entities = {Note.class}, exportSchema = false,version = 1)
public abstract class NoteDatabase extends RoomDatabase {
 /*private  static final String DB_NAME = "note_db";
 private static NoteDatabase instance;

 public static synchronized NoteDatabase getInstance(Context context){
     if(instance == null){
         instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, DB_NAME)
                 .fallbackToDestructiveMigration()
                 .allowMainThreadQueries()
                 .build();
     }
     return instance;
 }*/
public abstract NoteDao noteDao();



}
