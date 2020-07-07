package com.example.notesapp;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private Context context;
    private static DatabaseClient instance;
    private NoteDatabase noteDatabase;

    public DatabaseClient(Context context) {
        this.context = context;
        noteDatabase = Room.databaseBuilder(context, NoteDatabase.class, "Note_db")
                .build();
    }
    public static synchronized DatabaseClient getInstance(Context context){
        if(instance == null){
            instance = new DatabaseClient(context);
        }
        return instance;
    }
    public NoteDatabase getNoteDatabase(){
        return  noteDatabase;
    }


}
