package com.tint.fclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton

class MainClockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_clock)

        val timerBtn = findViewById<ImageButton>(R.id.timer_image_button)
        val alarmBtn = findViewById<ImageButton>(R.id.alarm_image_button)
        val settingsBtn = findViewById<ImageButton>(R.id.settings_image_button)

        timerBtn.setOnClickListener {
            Log.i("Test", "Timer pressed.")
        }
        alarmBtn.setOnClickListener {
            Log.i("Test", "Alarm pressed.")
        }
        settingsBtn.setOnClickListener {
            Log.i("Test", "Settings pressed.")
        }
    }
}