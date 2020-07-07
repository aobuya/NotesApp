package com.example.notesapp.ViewModel;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.DatabaseClient;
import com.example.notesapp.Models.Note;
import com.example.notesapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateNote extends AppCompatActivity implements View.OnClickListener {
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
        setContentView(R.layout.activity_update_note);
        ButterKnife.bind(this);
        final Note note = (Note) getIntent().getSerializableExtra("Note");
        loadNote(note);

        save_btn.setOnClickListener(this);
    }

    private void loadNote(Note note) {
        title_edit.setText(note.getTitle());
        desc_edit.setText(note.getDesc());
    }

    @Override
    public void onClick(View v) {
        if(v == save_btn ){
            update();

        }
    }

    private void update() {
        //Intent intent = getIntent();
        final String uTitle = title_edit.getText().toString();
        final String uDesc = desc_edit.getText().toString();

        class UpdateNotes extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                Note unote = new Note();
                unote.setTitle(uTitle);
                unote.setDesc(uDesc);

                DatabaseClient.getInstance(getApplicationContext())
                        .getNoteDatabase()
                        .noteDao()
                        .update(unote);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(UpdateNote.this, MainActivity.class));
                Toast.makeText(UpdateNote.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        }
        UpdateNotes updated = new UpdateNotes();
        updated.execute();

    }


}