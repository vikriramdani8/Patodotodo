package com.example.patodotodo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patodotodo.R
import com.example.patodotodo.activity_menu
import com.example.patodotodo.model.Model_Task
import kotlinx.android.synthetic.main.list_subitem_category.view.*

class ReyclerSubItemCategoryAdapter(
    private val context: activity_menu,
    private val listTask: ArrayList<Model_Task>
): RecyclerView.Adapter<ReyclerSubItemCategoryAdapter.ViewHolder>() {

    interface OnActClickListener {
        fun onItemClick(item: Model_Task, adapterPosition: Int)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subItem = view.cb_subitem
        fun bindItems(data: Model_Task){
            subItem.setOnCheckedChangeListener{ buttonView, isChecked ->
                println("Tol");
            }
        }

        fun initialize(item: Model_Task){
            subItem.text = item.name_task
//            itemView.setOnClickListener {
//                action.onItemClick(item, adapterPosition)
//            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReyclerSubItemCategoryAdapter.ViewHolder {
        return ReyclerSubItemCategoryAdapter.ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_subitem_category, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ReyclerSubItemCategoryAdapter.ViewHolder, position: Int) {
        val data = listTask[position]
        holder.bindItems(listTask[position])
        holder.initialize(data);
    }

    override fun getItemCount(): Int {
        return listTask.size
    }
}