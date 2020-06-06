package com.example.notesappp.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.notesappp.R
import com.example.notesappp.model.Note
import com.example.notesappp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_create_note.*

class EditNoteActivity : AppCompatActivity() {
    private lateinit var noteColor: String
    private lateinit var noteHeading: String
    private lateinit var noteBody: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)
        val intent = intent
        val note: Note = intent.getSerializableExtra("Note") as Note

        noteHeadingET.setText(note.noteHeading)
        noteBodyET.setText(note.noteBody)

        noteColor = note.noteColor
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(noteColor)))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.parseColor(noteColor));
        }

        note_bkg.setBackgroundColor((Color.parseColor(note.noteColor)))


        purple_btn.setOnClickListener {
            noteColor = "#ffaa66cc"
            changeColor(noteColor)
        }
        orange_btn.setOnClickListener {
            noteColor = "#ffffbb33"
            changeColor(noteColor)
        }
        blue_btn.setOnClickListener {
            noteColor = "#ff0099cc"
            changeColor(noteColor)
        }
        green_btn.setOnClickListener {
            noteColor = "#ff99cc00"
            changeColor(noteColor)
        }


    }


    private fun changeColor(color: String) {
        note_bkg.setBackgroundColor(Color.parseColor(color))
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(color)))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.statusBarColor = Color.parseColor(color);
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = intent
        val note: Note = intent.getSerializableExtra("Note") as Note
        val noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        val id: Int = item.itemId
        if (id == R.id.save) {
            if (noteHeadingET.text.isEmpty()) {
                noteHeadingET.error = "Please Provide a note heading"
            } else {
                noteBody = noteBodyET.text.toString()
                noteHeading = noteHeadingET.text.toString()
                note.noteBody = noteBody
                note.noteHeading = noteHeading
                note.noteColor = noteColor
                noteViewModel.update(note)
                onBackPressed()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}