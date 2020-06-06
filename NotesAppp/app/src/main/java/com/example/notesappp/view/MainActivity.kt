package com.example.notesappp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import com.example.notesappp.R
import com.example.notesappp.model.Note
import com.example.notesappp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.note_item.view.*


class MainActivity : AppCompatActivity(), NoteClickListener {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onClick(note: Note) {
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        var intent = Intent(this, EditNoteActivity::class.java)
        intent.putExtra("Note", note)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNoteFab.setOnClickListener {
            startActivity(Intent(this, CreateNoteActivity::class.java))
        }
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.getAll().observe(this, Observer {
            it?.let {
                if (!it.isEmpty()) {
                    getStartedEt.visibility = View.INVISIBLE
                } else {
                    getStartedEt.visibility = View.VISIBLE
                }
                recyclerAdapter.setNotes(it)
            }
        })
        recyclerView = findViewById(R.id.recyclerView)
        recyclerAdapter = RecyclerAdapter(this, this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        var itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position: Int = viewHolder.adapterPosition
                    val myNote: Note = recyclerAdapter.getNoteAtPosition(position)
                    noteViewModel.delete(myNote)
                    Toast.makeText(
                        this@MainActivity,
                        "Deleted ${viewHolder.itemView.noteHeading.text}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
