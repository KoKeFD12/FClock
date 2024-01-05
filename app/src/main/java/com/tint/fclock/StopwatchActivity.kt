package com.tint.fclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import com.tint.fclock.databinding.ActivityStopwatchBinding
import java.util.Locale

class StopwatchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStopwatchBinding

    private var isRunning = false
    private var timerSeconds = 0

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            timerSeconds++
            val hours = timerSeconds / 3600
            var minutes = (timerSeconds % 3600) / 60
            var seconds = timerSeconds % 60

            var time = String.format("%02d:%02d:%02d", hours, minutes, seconds)

            binding.stopwatchTimeTextView.text = time
            handler.postDelayed(this, 1000)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStopwatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener { startTimer() }
        binding.pauseButton.setOnClickListener { pauseTimer() }
        binding.resetButton.setOnClickListener { resetTimer() }
    }

    private fun startTimer() {
        if (!isRunning) {
            handler.postDelayed(runnable, 1000)
            isRunning = true

            binding.playButton.isEnabled = false
            binding.pauseButton.isEnabled = true
            binding.resetButton.isEnabled = true
        }
    }

    private fun pauseTimer() {
        if (isRunning) {
            handler.removeCallbacks(runnable)
            isRunning = false

            binding.playButton.isEnabled = true
            binding.pauseButton.isEnabled = false
            binding.resetButton.isEnabled = true
        }
    }

    private fun resetTimer() {
        pauseTimer()
        timerSeconds = 0
        binding.stopwatchTimeTextView.text = "00:00:00"
        binding.playButton.isEnabled = true
        binding.pauseButton.isEnabled = false
        binding.resetButton.isEnabled = false
    }

}