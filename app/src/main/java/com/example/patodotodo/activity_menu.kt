package com.example.patodotodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.patodotodo.adapter.RecyclerCategoryAdapter
import com.example.patodotodo.helper.Constant
import com.example.patodotodo.helper.DBHelper
import com.example.patodotodo.helper.PreferencesHelper
import com.example.patodotodo.model.ActivitiesModel
import com.example.patodotodo.model.Model_Category
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_menu.*

class activity_menu : AppCompatActivity(), RecyclerCategoryAdapter.OnActClickListener {

    var categoryList: List<Model_Category> = ArrayList<Model_Category>()
    private lateinit var layoutManager: RecyclerView.LayoutManager
    internal lateinit var sharedpref: PreferencesHelper
    internal lateinit var db: DBHelper
    
    override fun onRestart() {
        super.onRestart()
        refreshCategory()
    }

    fun logout(){
        sharedpref.clear()
        intent = Intent(this,  MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun refreshCategory(){
        db = DBHelper(this)
        sharedpref = PreferencesHelper(this)
        val temp = sharedpref.getString(Constant.PREF_ID)
        if (temp != null)
            categoryList = db.getAllCategory(temp.toInt())

        for (a in categoryList)
            println(a.id_user.toString()+": "+a.id_category+" - "+a.name_category)

        layoutManager = LinearLayoutManager(this)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        rvActivitiesList.layoutManager = layoutManager
        rvActivitiesList.adapter = RecyclerCategoryAdapter(this, categoryList, this)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        refreshCategory()
        button2.setOnClickListener() {
            intent = Intent(this, activity_tambahcategory::class.java)
            startActivity(intent)
        }
    }

    private val mOnNavigationItemSelectedListener=
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_list -> {
                val intent = Intent(this, activity_mylist::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
//                val intent = Intent(this, activity_profile::class.java)
//                startActivity(intent)
                logout()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onItemClick(data: Model_Category, position: Int) {
        logout()
//        val intent = Intent(this, activity_detail::class.java)
//        intent.putExtra("title", item.title)
//        intent.putExtra("content1", item.cb1)
//        intent.putExtra("content2", item.cb2)
//        intent.putExtra("content3", item.cb3)
//
//        startActivity(intent);
    }
}