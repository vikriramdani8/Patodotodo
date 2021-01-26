package com.example.patodotodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.patodotodo.adapter.RecyclerActivitiesAdapter
import com.example.patodotodo.model.ActivitiesModel
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity(), RecyclerActivitiesAdapter.OnActClickListener {

    val ActivitiesList: ArrayList<ActivitiesModel> = ArrayList()
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        button2.setOnClickListener() {
            intent = Intent(this,  Tambah::class.java)
            startActivity(intent);
        }

        ActivitiesList.add(
            ActivitiesModel(
                "Travel",
                "Beli Seblak",
                "Ke Dago",
                "Utara Cafe"
            )
        )

        ActivitiesList.add(
            ActivitiesModel(
                "College",
                "Pem V",
                "SAP",
                "Ac. English"
            )
        )

        ActivitiesList.add(
            ActivitiesModel(
                "Work",
                "Temuan 34",
                "Temuan 23",
                "Explore RMTM"
            )
        )

        layoutManager = LinearLayoutManager(this)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        rvActivitiesList.layoutManager = layoutManager
//        rvActivitiesList.adapter = RecyclerActivitiesAdapter(this, ActivitiesList, RecyclerActivitiesAdapter.OnActClickListener)
        rvActivitiesList.adapter = RecyclerActivitiesAdapter(this, ActivitiesList, this)
    }

    override fun onItemClick(item: ActivitiesModel, position: Int) {
        Toast.makeText(this,
            item.title,
            Toast.LENGTH_SHORT
        ).show()

        val intent = Intent(this, DetailActivities::class.java)
        intent.putExtra("title", item.title)
        intent.putExtra("content1", item.cb1)
        intent.putExtra("content2", item.cb2)
        intent.putExtra("content3", item.cb3)

        startActivity(intent);
    }
}