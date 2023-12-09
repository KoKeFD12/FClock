package com.tint.fclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        val loginBtn = findViewById<Button>(R.id.login_button)
        val guestBtn = findViewById<TextView>(R.id.guest_login_clickable_text_view)

        loginBtn.setOnClickListener {
            startActivity(Intent(this, MainClockActivity::class.java))
        }

        guestBtn.setOnClickListener {
            startActivity(Intent(this, MainClockActivity::class.java))
        }
    }
}