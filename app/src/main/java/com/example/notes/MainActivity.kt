package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NoteViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(Con = this, listener = this)
        binding.recyclerview.adapter = adapter

        binding.addbutton.setOnClickListener {
            val noteText = binding.input.text.toString()
            if (noteText.isNotEmpty()){
                viewModel.insertNote(Note(noteText))
                Toast.makeText(applicationContext, "$noteText Inserted", Toast.LENGTH_SHORT).show()
            }else{
                
            }
        }


        viewModel = ViewModelProvider(this /*, ViewModelProvider.AndroidViewModelFactory.getInstance(application)*/).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })
    }

    fun OnItemClick(Note: Note) {
        viewModel.deleteNote(Note)
        Toast.makeText(applicationContext, "${Note.text} Deleted", Toast.LENGTH_SHORT).show()
    }
}