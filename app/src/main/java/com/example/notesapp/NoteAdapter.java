package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.Models.Note;
import com.example.notesapp.ViewModel.UpdateNote;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context context;
    private List<Note> addedNote;

    public NoteAdapter(Context context, List<Note> addedNote) {
        this.context = context;
        this.addedNote = addedNote;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = addedNote.get(position);
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDesc());

    }

    @Override
    public int getItemCount() {
        return addedNote.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title, description;
        //public ImageView imageView;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtTitle);
            description = itemView.findViewById(R.id.txtDesc);
            //imageView =  itemView.findViewById(R.id.primage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Note  note = addedNote.get(getAdapterPosition());
            Intent intent = new Intent(context, UpdateNote.class);
            intent.putExtra("Note", note);
            context.startActivity(intent);

        }
    }
}
