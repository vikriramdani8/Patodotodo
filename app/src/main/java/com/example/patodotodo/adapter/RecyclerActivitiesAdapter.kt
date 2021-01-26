package com.example.patodotodo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.patodotodo.Menu
import com.example.patodotodo.R
import com.example.patodotodo.model.ActivitiesModel
import com.example.patodotodo.model.CarModel
import kotlinx.android.synthetic.main.list_item_activity.view.*
import kotlinx.android.synthetic.main.list_item_view.view.*

class RecyclerActivitiesAdapter(
    private val context: Menu,
    private val activitiesList : ArrayList<ActivitiesModel>,
    var clickListener: OnActClickListener) :
    RecyclerView.Adapter<RecyclerActivitiesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.titleActivities
        val checkBox1 = view.checkBox1
        val checkBox2 = view.checkBox2
        val checkBox3 = view.checkBox3

        fun bindItems(data : ActivitiesModel){
            title?.text = data.title
            checkBox1?.text = data.cb1
            checkBox2?.text = data.cb2
            checkBox3?.text = data.cb3

            itemView.setOnClickListener {
                val text = data.title
                Toast.makeText(itemView.context, text,
                    Toast.LENGTH_LONG).show()
            }
        }

        fun initialize(item: ActivitiesModel, action:OnActClickListener){
            title.text =  item.title
            checkBox1.text = item.cb1
            checkBox2.text = item.cb2
            checkBox3.text = item.cb3

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
               R.layout.list_item_activity, parent, false
           )
       )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = activitiesList[position]
        holder.bindItems(activitiesList[position])

        holder.initialize(data, clickListener);

    }

    override fun getItemCount(): Int {
        return activitiesList.size
    }

    interface OnActClickListener {
        fun onItemClick(data: ActivitiesModel, position: Int)
    }
}