package com.example.patodotodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.patodotodo.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivities : AppCompatActivity() {

    lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        titleTask.text = getIntent().getStringExtra("title");
        cb1.text = getIntent().getStringExtra("content1");
        cb2.text = getIntent().getStringExtra("content2");
        cb3.text = getIntent().getStringExtra("content3");
    }
}