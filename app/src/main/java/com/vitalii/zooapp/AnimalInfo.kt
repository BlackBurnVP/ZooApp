package com.vitalii.zooapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class AnimalInfo : AppCompatActivity() {

    private lateinit var mImageView:ImageView
    private lateinit var mNameText:TextView
    private lateinit var mDescText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_info)

        mImageView = findViewById(R.id.imgAnimalImage)
        mNameText = findViewById(R.id.tvName)
        mDescText = findViewById(R.id.tvDesc)

        val intent = intent
        mNameText.text = intent.getStringExtra("name")
        mDescText.text = intent.getStringExtra("description")
        mImageView.setImageResource(intent.getIntExtra("image",0))

    }
}
