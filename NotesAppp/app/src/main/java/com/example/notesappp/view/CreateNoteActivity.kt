package com.example.notesappp.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.notesappp.R
import com.example.notesappp.model.Note
import com.example.notesappp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_create_note.*

class CreateNoteActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel
    private var noteColor: String? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Save note button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.save) {
            if (noteHeadingET.text.isEmpty()) {
                noteHeadingET.setError("Please Provide a note heading")
            }
            else if (noteColor == null){
                Toast.makeText(this,"Please select a note clor",Toast.LENGTH_LONG).show()
            }
            else {
                val note =
                    Note(noteHeadingET.text.toString(), noteBodyET.text.toString(), noteColor!!)
                noteViewModel.insertNote(note)
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        purple_btn.setOnClickListener {
            noteColor = "#ffaa66cc"
            changeColor(noteColor!!)
        }
        orange_btn.setOnClickListener {
            noteColor = "#ffffbb33"
            changeColor(noteColor!!)
        }
        blue_btn.setOnClickListener {
            noteColor = "#ff0099cc"
            changeColor(noteColor!!)
        }
        green_btn.setOnClickListener {
            noteColor = "#ff99cc00"
            changeColor(noteColor!!)
        }
    }
    private fun changeColor(color:String){
        note_bkg.setBackgroundColor(Color.parseColor(color))
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(color)))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.statusBarColor = Color.parseColor(color);
        }
    }


}
