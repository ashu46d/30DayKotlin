package com.example.notesappp.view

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappp.R
import com.example.notesappp.model.Note
import java.util.*

class RecyclerAdapter(private val mContext: Context,
private val noteClickListener: NoteClickListener) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>(){

    private var mNotes: List<Note> = mutableListOf()
    fun getNotes() = mNotes
    fun setNotes(notes: List<Note>) {
        mNotes = notes
        notifyDataSetChanged()
    }

    fun getNoteAtPosition(position: Int): Note {
        return mNotes.get(position)
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noteHeading: TextView = itemView.findViewById(R.id.noteHeading)
        var noteBody: TextView = itemView.findViewById(R.id.noteBody)
        var noteColor: CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.note_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mNotes.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.RecyclerViewHolder, position: Int) {
        val currentNote = mNotes[position]
        holder.noteHeading.text = currentNote.noteHeading
        holder.noteBody.text = currentNote.noteBody
        holder.noteColor.setCardBackgroundColor(Color.parseColor(currentNote.noteColor))

        holder.itemView.setOnClickListener {
            noteClickListener.onClick(currentNote)
        }
    }


}