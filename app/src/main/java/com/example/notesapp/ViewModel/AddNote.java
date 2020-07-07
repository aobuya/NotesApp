package com.example.notesapp.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.DatabaseClient;
import com.example.notesapp.Models.Note;
import com.example.notesapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNote extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.edit_title)
    EditText title_edit;
    @BindView(R.id.edit_desc)
    EditText desc_edit;
    @BindView(R.id.save)
    Button save_btn;

    //Note note;
    //NoteDatabase noteDatabase = NoteDatabase.getInstance(AddNote.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);

        save_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == save_btn ){
            saveNote();

        }
    }

    private void saveNote() {
        final String title  = title_edit.getText().toString();
        final String description = desc_edit.getText().toString();

        class SaveNote extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                Note note = new Note();
                note.setTitle(title);
                note.setDesc(description);

                DatabaseClient.getInstance(getApplicationContext()).getNoteDatabase()
                        .noteDao()
                        .insert(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(AddNote.this, MainActivity.class));
                Toast.makeText(AddNote.this, "Added", Toast.LENGTH_SHORT).show();
            }
        }
        SaveNote saveNote = new SaveNote();
        saveNote.execute();

    }



}