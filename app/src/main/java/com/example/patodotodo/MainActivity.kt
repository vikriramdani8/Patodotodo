package com.example.patodotodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.patodotodo.helper.Constant
import com.example.patodotodo.helper.DBHelper
import com.example.patodotodo.helper.PreferencesHelper
import com.example.patodotodo.model.Model_User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var db: DBHelper
    internal var listUser:List<Model_User> = ArrayList<Model_User>()
    internal lateinit var sharedpref: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedpref = PreferencesHelper(this)

        db = DBHelper(this)
        button.setOnClickListener() {
            listUser = db.getUserLogin(txt_login_username.text.toString(), txt_login_password.text.toString())
            if (listUser.size != 0){
                sharedpref.put(Constant.PREF_ID, listUser[0].id_user.toString())
                sharedpref.put(Constant.PREF_USERNAME, txt_login_username.text.toString())
                sharedpref.put(Constant.PREF_PASSWORD, txt_login_password.text.toString())
                sharedpref.put(Constant.PREF_IS_LOGIN, true)
                moveIntent()
            } else {
                Toast.makeText(this, "The username or password is incorrect", Toast.LENGTH_SHORT).show()
            }
        }

        txt_register.setOnClickListener {
            intent = Intent(this,  activity_registration::class.java)
            startActivity(intent);
        }
    }

    fun moveIntent(){
        intent = Intent(this,  activity_menu::class.java)
        startActivity(intent);
        finish()
    }

    fun makeToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        if (sharedpref.getBoolean(Constant.PREF_IS_LOGIN)) {
            moveIntent()
        }
    }
}