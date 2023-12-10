package com.tint.fclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AlarmsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarms)
        val timePicker = findViewById<Button>(R.id.time_picker_button)

        timePicker.setOnClickListener {
            TimePickerFragment().show(supportFragmentManager, "timePicker")
        }
    }
}