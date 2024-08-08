package com.example.databaseapplication

import android.app.Dialog
import android.os.Bundle
import android.util.EventLogTags.Description
import android.widget.Button
import android.widget.EditText
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



        binding.FabAdd.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.add_dialogbox)
            val enter_rollno = dialog.findViewById<EditText>(R.id.EnterRollNo)
            val enter_name = dialog.findViewById<EditText>(R.id.EnterName)
            val add_btn = dialog.findViewById<Button>(R.id.Addbutton)
            dialog.show()

            add_btn.setOnClickListener {
                val rollno = enter_rollno.text.toString()
                val name_entered = enter_name.text.toString()
                notesDatabase.notesInterface().insertTodo(NotesEntity(Title = rollno, Description = name_entered))
                getList()
                recyclerAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }

    }





    override fun update(position: Int) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.update_dialogbox)
        val Rollno = dialog.findViewById<EditText>(R.id.UpdateRollNo)
        val Name = dialog.findViewById<EditText>(R.id.UpdateName)
        Rollno.setText(notesList[position].Title)
        Name.setText(notesList[position].Description)
        val update_btn = dialog.findViewById<Button>(R.id.Update_btn)
        dialog.show()

        update_btn.setOnClickListener {
            val updated_rollno = Rollno.text.toString()
            val updated_name = Name.text.toString()

           notesDatabase.notesInterface().updateTodoEntity(NotesEntity(id = notesList[position].id, Title = updated_rollno, Description = updated_name))

            getList()
            recyclerAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
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

