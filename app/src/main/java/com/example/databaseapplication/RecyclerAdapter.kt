package com.example.databaseapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(var list:ArrayList<NotesEntity>,var clickInterface:onclick):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(var view: View):RecyclerView.ViewHolder(view) {
        var roll_no=view.findViewById<TextView>(R.id.Rollno)
        var studentname=view.findViewById<TextView>(R.id.StuName)
        var stuclass=view.findViewById<TextView>(R.id.tvClass)
        var Updatebtn=view.findViewById<Button>(R.id.Update_btn)
        var Deletebtn=view.findViewById<Button>(R.id.Delete_btn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.base_item_layout, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply{
            roll_no.setText(list[position].Title)
            studentname.setText(list[position].Description)


            Updatebtn.setOnClickListener {
                clickInterface.update(position)
            }
            Deletebtn.setOnClickListener {
                clickInterface.delete(position)
            }


        }

    }

    interface onclick {
        fun update(position: Int)
        fun delete(position: Int)

    }

}
