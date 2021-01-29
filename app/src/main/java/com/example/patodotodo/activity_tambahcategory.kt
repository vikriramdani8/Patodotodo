package com.example.patodotodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.patodotodo.helper.Constant
import com.example.patodotodo.helper.DBHelper
import com.example.patodotodo.helper.PreferencesHelper
import com.example.patodotodo.model.Model_Category
import kotlinx.android.synthetic.main.activity_tambahcategory.*

class activity_tambahcategory : AppCompatActivity() {

    internal lateinit var db: DBHelper
    internal lateinit var sharedpref: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambahcategory)

        db = DBHelper(this)
        sharedpref = PreferencesHelper(this)

        btn_save.setOnClickListener {
            var temp = sharedpref.getString(Constant.PREF_ID)
            if (temp != null) {
                db.addCategory(Model_Category(0, temp.toInt(), txt_task.text.toString()))
            }

            onBackPressed()
        }
    }
}