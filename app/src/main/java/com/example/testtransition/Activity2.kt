package com.example.testtransition

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        val androidImageView = findViewById<ImageView>(R.id.android)

        androidImageView.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(
                    Intent(this, Activity3::class.java),
                    ActivityOptions.makeSceneTransitionAnimation(
                        this,
                        androidImageView,
                        "android"
                    ).toBundle()
                )
            } else startActivity(Intent(this, Activity3::class.java))
        }
    }
}
