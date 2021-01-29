package com.example.patodotodo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.patodotodo.activity_menu
import com.example.patodotodo.R
import com.example.patodotodo.model.Model_Category
import com.example.patodotodo.model.Model_Task
import kotlinx.android.synthetic.main.list_item_category.view.*

class RecyclerCategoryAdapter(
    private val context: activity_menu,
    private val categoryList : List<Model_Category>,
    var clickListener: OnActClickListener) :
    RecyclerView.Adapter<RecyclerCategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.titleActivities
        val list = view.taskSubItem

        val listTask: ArrayList<Model_Task> = ArrayList()
        private lateinit var layoutManager: RecyclerView.LayoutManager

        fun bindItems(data: Model_Category){
            title?.text = data.name_category
            itemView.setOnClickListener {
                val text = data.name_category
                Toast.makeText(itemView.context, text,
                    Toast.LENGTH_LONG).show()
            }
        }

        fun initialize(item: Model_Category, action: OnActClickListener, cntx: Context){
            title.text =  item.name_category
            listTask.add(Model_Task(
                0, 0, 0, "Test Aja", "01/01/2020", "15:40", 1
            ))

            layoutManager = LinearLayoutManager(cntx)
            layoutManager = LinearLayoutManager(cntx, LinearLayoutManager.VERTICAL, false)
            list.layoutManager = layoutManager
            list.adapter = ReyclerSubItemCategoryAdapter(cntx as activity_menu, listTask)
            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): ViewHolder {

       return ViewHolder(
           LayoutInflater.from(context).inflate(
               R.layout.list_item_category, parent, false
           )
       )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = categoryList[position]
        holder.bindItems(categoryList[position])
        holder.initialize(data, clickListener, context);
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    interface OnActClickListener {
        fun onItemClick(data: Model_Category, position: Int)
    }
}