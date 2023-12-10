package com.tint.fclock

import android.content.Intent
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
        val stopwatchBtn = findViewById<ImageButton>(R.id.stopwatch_image_button)
        val settingsBtn = findViewById<ImageButton>(R.id.settings_image_button)

        timerBtn.setOnClickListener {
            startActivity(Intent(this, TimerActivity::class.java))
        }
        alarmBtn.setOnClickListener {
            startActivity(Intent(this, AlarmsActivity::class.java))
        }
        stopwatchBtn.setOnClickListener {
            startActivity(Intent(this, StopwatchActivity::class.java))
        }
        settingsBtn.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}