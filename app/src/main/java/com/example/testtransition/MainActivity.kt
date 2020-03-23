package com.example.testtransition

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityButton = findViewById<Button>(R.id.second_activity)
        val fragmentButton = findViewById<Button>(R.id.fragment)

        activityButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(
                    Intent(this, Activity2::class.java),
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                )
            } else {
                startActivity(Intent(this, Activity2::class.java))
            }
        }

        fragmentButton.setOnClickListener {

            val fragment1 = Fragment1()
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment1)
                .addToBackStack(null).commit()
            val exitFade = Fade()
            exitFade.duration = 100
            fragment1.exitTransition = exitFade

        }

    }
}
