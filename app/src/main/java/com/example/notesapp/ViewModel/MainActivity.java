package com.example.notesapp.ViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.notesapp.DatabaseClient;
import com.example.notesapp.Models.Note;
import com.example.notesapp.NoteAdapter;
import com.example.notesapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.fab)
     FloatingActionButton floatingActionButton;
    @BindView(R.id.notesList)
    RecyclerView recyclerView;
    Note note;
    //NoteDatabase noteDatabase = NoteDatabase.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton.setOnClickListener(this);
        getNotes();
    }

    private void getNotes() {
        class GetNotes extends AsyncTask<Void, Void, List<Note>>{

            @Override
            protected List<Note> doInBackground(Void... voids) {

                List<Note> noteList = DatabaseClient.getInstance(getApplicationContext())
                        .getNoteDatabase()
                        .noteDao()
                        .getAll();
                return noteList;
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
                NoteAdapter noteAdapter = new NoteAdapter(MainActivity.this, notes);
                recyclerView.setAdapter(noteAdapter);
            }
        }
        GetNotes getNotes = new GetNotes();
        getNotes.execute();
    }


    @Override
    public void onClick(View v) {
        if(v == floatingActionButton){
            startActivity(new Intent(MainActivity.this, AddNote.class));
        }

    }


}