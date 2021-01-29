package com.example.patodotodo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.patodotodo.helper.DBHelper
import com.example.patodotodo.model.Model_User
import kotlinx.android.synthetic.main.activity_registration.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class activity_registration : AppCompatActivity() {
    internal lateinit var db : DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        db = DBHelper(this)
        button_signup.setOnClickListener {

            if (txt_name.text.toString() == "" || txt_email.text.toString() == "" || txt_username.text.toString() == "" || txt_password.text.toString() == ""){
                Toast.makeText(this, "Data Cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                val pattern: Pattern = Pattern.compile(".+@.+\\.[a-z]+")
                val matcher: Matcher = pattern.matcher(txt_email.text.toString())
                if (matcher.matches()){
                    if (txt_password.text.toString().equals(txt_verify_password.text.toString())){
                        if (db.userValidation(txt_email.text.toString(), txt_username.text.toString())){
                            var user = Model_User(
                            0,
                                txt_name.text.toString(),
                                txt_email.text.toString(),
                                txt_username.text.toString(),
                                txt_password.text.toString()
                            )
                            
                            db.addUser(user)
                            finish()
                        } else {
                            Toast.makeText(this, "This user already exists", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Email is no valid", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}