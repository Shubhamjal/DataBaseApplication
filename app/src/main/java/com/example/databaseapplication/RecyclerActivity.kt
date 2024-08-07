package com.example.databaseapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Update
import com.example.databaseapplication.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() , RecyclerAdapter.onclick{

    lateinit var binding: ActivityRecyclerBinding
    var notesList= arrayListOf<NotesEntity>()
    lateinit var notesDatabase: NotesDatabase
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding=ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notesDatabase=NotesDatabase.getInstance(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        notesDatabase.notesInterface().insertTodo(NotesEntity(Title = "test New", Description = "C++"))
        recyclerAdapter=RecyclerAdapter(notesList,this)
        binding.recycler.layoutManager= LinearLayoutManager(
            this,LinearLayoutManager.VERTICAL,false)
        binding.recycler.adapter=recyclerAdapter
        getList()
    }





    override fun update(position: Int) {
        Toast.makeText(this, "Item Update", Toast.LENGTH_SHORT).show()



    }

    override fun delete(position: Int) {
        Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show()
        notesDatabase.notesInterface().deleteTodoEntity(notesList[position])
        getList()
        recyclerAdapter.notifyDataSetChanged()

    }

    private fun getList() {
        notesList.clear()
        notesList.addAll(notesDatabase.notesInterface().getList())

    }
}