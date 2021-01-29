package com.example.patodotodo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.patodotodo.R
import com.example.patodotodo.activity_mylist
import com.example.patodotodo.model.Model_Task
import kotlinx.android.synthetic.main.list_item_task.view.*

class RecylerTaskAdapter(
    private val context: activity_mylist,
    private val taskList : ArrayList<Model_Task>,
    var clickListener: RecylerTaskAdapter.OnActClickListener
) : RecyclerView.Adapter<RecylerTaskAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecylerTaskAdapter.ViewHolder {
        return  RecylerTaskAdapter.ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item_task, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecylerTaskAdapter.ViewHolder, position: Int) {
        val data = taskList[position]
        holder.bindItems(taskList[position])
        holder.initialize(data, clickListener);
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cb_task = view.cb_task
        val txt_date = view.txt_date

        fun bindItems(data: Model_Task){
            itemView.setOnClickListener {
                val text = data.name_task
                Toast.makeText(itemView.context, text,
                    Toast.LENGTH_LONG).show()
            }
        }

        fun initialize(item: Model_Task, action: RecylerTaskAdapter.OnActClickListener){
            cb_task.text = item.name_task
            txt_date.text = item.date_task

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    interface OnActClickListener {
        fun onItemClick(data: Model_Task, position: Int)
    }
}